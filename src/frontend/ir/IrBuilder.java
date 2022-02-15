package frontend.ir;

import java.util.LinkedList;
import java.util.HashMap;
import java.util.Objects;

import org.antlr.v4.runtime.misc.Pair;  // Not Java STL
import utility.CmdArgument;
import utility.error.InternalError;
import frontend.ast.node.*;
import frontend.ir.node.*;

/**
 * Build sequential structure LLVM IR from AST
 */
public class IrBuilder {
    // region BASIC
    public static CmdArgument cmdArgs = null;

    public final String GLOBAL_VARIABLE_PREFIX = "__VAR__";
    public final String FUNCTION_PREFIX = "__FUNC__";
    public final String INITIAL_FUNCTION = "__INIT";
    public final String THIS_VARIABLE_NAME = "__THIS";

    public final IrTop irRoot = new IrTop();
    public IrFunction currentFunction = null;
    public IrBlock currentBlock = null;
    // scopeStack 中变量名无 GLOBAL_VARIABLE_PREFIX 前缀
    // 但对应的 IrId 名称含有前缀
    public final LinkedList<HashMap<String, IrId>> scopeStack = new LinkedList<>();

    // For Continue and Break, is 'null' when out of loop
    public final LinkedList<IrBlock> loopCondBlockStack = new LinkedList<>();
    public final LinkedList<IrBlock> loopNextBlockStack = new LinkedList<>();

    public IrBuilder(CmdArgument cmdArgs_) {
        cmdArgs = cmdArgs_;
        scopeStack.push(new HashMap<>());
    }

    public String print() {
        irRoot.genIndex();
        return irRoot.toString();
    }

    // endregion

    // region TOOL
    public static void throwTodoError(String pos) throws InternalError {
        // ! This function SHOULD NOT be used at any finished program.
        throw new InternalError("IR_DEBUG", "TODO: " + pos);
    }

    public static void throwUnexpectedError() throws InternalError {
        throw new InternalError("IR_UNEXPECTED", "");
    }

    public void buildComment(String commentInfo) {
        if (cmdArgs.contains(CmdArgument.ArgumentType.IR_SOURCE_CODE)) {
            var ins = new IrInstruction(IrInstruction.Genre.COMMENT);
            ins.commentInfo = commentInfo;
            currentBlock.instructions.add(ins);
            if (cmdArgs.contains(CmdArgument.ArgumentType.DEBUG))
                System.out.println("# " + commentInfo);
        }
    }

    /**
     * Generate comment of corresponding source code.
     * Just show the first line of multiline statement, e.g. IF, FOR.
     */
    public void buildSourceCodeComment(AstNode astNode) {
        if (cmdArgs.contains(CmdArgument.ArgumentType.IR_SOURCE_CODE)) {
            var ins = new IrInstruction(IrInstruction.Genre.COMMENT);
            var str = astNode.position.rawText;
            var end = str.indexOf('\n');
            ins.commentInfo = (end == -1) ? str : str.substring(0, str.indexOf('\n'));
            currentBlock.instructions.add(ins);
            if (cmdArgs.contains(CmdArgument.ArgumentType.DEBUG))
                System.out.println("# " + ins.commentInfo);
        }
    }

    /**
     * Get value in memory by pointer
     */
    public IrId buildGetFromMem(IrId pointer) {
        var ins = new IrInstruction(IrInstruction.Genre.LOAD,
                pointer.type.getNotPointer());
        ins.loadAddress = pointer;
        currentBlock.instructions.add(ins);
        return ins.insId;
    }

    /**
     * Assign right value in register to left value in mem
     */
    public void buildAssignToMem(IrId value, IrId pointer) {
        var ins = new IrInstruction(IrInstruction.Genre.STORE, value.type);
        ins.storeData = value;
        ins.storeAddress = pointer;
        currentBlock.instructions.add(ins);
    }

    /**
     * @param type Type to alloca
     * @return Pointer IrId
     */
    public IrId buildAlloca(IrType type) {
        var ins = new IrInstruction(IrInstruction.Genre.ALLOCA, type);
        currentBlock.instructions.add(ins);
        return ins.insId;
    }

    /**
     * Only available for integer type
     *
     * @param type Type to alloca
     * @return Pointer IrId
     */
//    public IrId buildAllocaAndZeroInit(IrType type) {
//        switch (type.genre) {
//            case I1, I8, I32 -> {
//            }
//            default -> throw new InternalError(
//                    "IR",
//                    "Wrong type in buildAllocaAndZeroInit()");
//        }
//        var allocaId = buildAllca(type);
//        var insStore = new IrInstruction(IrInstruction.Genre.STORE);
//        insStore.storeType = type;
//        insStore.storeData = new IrId(0);
//        insStore.storeAddress = allocaId;
//        currentBlock.instructions.add(insStore);
//        return allocaId;
//    }

    /**
     * Finish current block, build jump to another block
     * and set 'currentBlock' as target block
     */
    public void buildJumpToBlock(IrBlock block, boolean setCurrentBlock) {
        var ins = new IrInstruction(IrInstruction.Genre.JUMP);
        ins.jumpLabel = block.label;
        currentBlock.jumpInstruction = ins;
        if (setCurrentBlock) currentBlock = block;
    }

    /**
     * Finish currentBlock, jump to one of two blocks depending on
     * condition result, and set 'currentBlock' as 'null'
     */
    public void buildBranch(IrId condId, IrBlock trueBlock, IrBlock falseBlock) {
        var ins = new IrInstruction(IrInstruction.Genre.BRANCH);
        ins.branchCondId = condId;
        ins.branchTrueLabel = trueBlock.label;
        ins.branchFalseLabel = falseBlock.label;
        currentBlock.jumpInstruction = ins;
    }

    // endregion

    // region Top Structure
    // Build* functions add sth to IR
    // Visit* functions just traverse AST
    public void buildRoot(NodeRoot astNode) {
        // Basic information
        var ins = new IrInstruction(IrInstruction.Genre.DECLARE);
        ins.declareInfo = "target datalayout = \"e-m:e-p:32:32-i64:64-n32-S128\"";
        irRoot.declares.add(ins);
        ins = new IrInstruction(IrInstruction.Genre.DECLARE);
        ins.declareInfo = "target triple = \"riscv32\"";
        irRoot.declares.add(ins);

        // Add initialization function
        // * 初始化函数只有一个 Block
        IrFunction initFunc = new IrFunction();
        initFunc.returnType = new IrType(IrType.Genre.VOID);
        initFunc.name = INITIAL_FUNCTION;
        var initFuncBlock = new IrBlock();
        initFuncBlock.jumpInstruction = new IrInstruction(
                IrInstruction.Genre.RETURN,
                initFunc.returnType);
        initFunc.blocks.add(initFuncBlock);
        irRoot.functions.put(initFunc.name, initFunc);

        // Depth-first traversal AST tree
        // 函数和类支持前向引用, 需要先收集符号
        // 而全局变量不支持前向引用
        for (var section : astNode.programSections) {
            switch (section.genre) {
                case CLASS_DEFINE -> declareClass(section.classDefineNode);
                case FUNCTION_DEFINE -> declareFunction(section.functionDefine);
            }
        }

        for (var section : astNode.programSections) {
            switch (section.genre) {
                case VARIABLE_DEFINE -> visitVariableDefine(section.globalVariableDefine, true);
                case CLASS_DEFINE -> buildClass(section.classDefineNode);
                case FUNCTION_DEFINE -> buildFunction(section.functionDefine);
            }
        }
    }

    public void declareClass(NodeClassDefine astNode) {
        // todo
    }

    public void declareFunction(NodeFunctionDefine astNode) {
        var func = new IrFunction();
        func.returnType = new IrType(astNode.type);
        func.name = ((Objects.equals(astNode.name, "main")) ? "" : FUNCTION_PREFIX)
                + astNode.name;
        var arguments = astNode.argumentList;
        if (arguments != null) {
            for (var astType : arguments.types)
                func.arguments.add(new IrId(new IrType(astType)));
        }

        irRoot.functions.put(astNode.name, func);
    }

    public void buildClass(NodeClassDefine astNode) {
        // todo
    }

    public void buildFunction(NodeFunctionDefine astNode) {
        currentFunction = irRoot.functions.get(astNode.name);
        var firstBlock = new IrBlock();
        currentFunction.blocks.add(firstBlock);
        currentBlock = firstBlock;

        // Set new scope
        var currentScope = new HashMap<String, IrId>();
        var arguments = astNode.argumentList;
        if (arguments != null) {
            var len = arguments.types.size();
            for (int i = 0; i < len; i++) {
                var argVal = currentFunction.arguments.get(i);
                var insAlloca = new IrInstruction(
                        IrInstruction.Genre.ALLOCA,
                        argVal.type);
                var insStore = new IrInstruction(
                        IrInstruction.Genre.STORE,
                        argVal.type);
                insStore.storeAddress = insAlloca.insId;
                insStore.storeData = argVal;
                currentBlock.instructions.add(insAlloca);
                currentBlock.instructions.add(insStore);
                currentScope.put(
                        arguments.identifiers.get(i),
                        insAlloca.insId);
            }
        }
        scopeStack.push(currentScope);

        // Declare return variable
        boolean returnVoid = (currentFunction.returnType.genre == IrType.Genre.VOID);
        if (!returnVoid)
            currentFunction.retValPtr = buildAlloca(currentFunction.returnType);

        // Build return block
        var retBlock = new IrBlock();
        currentFunction.returnBlock = retBlock;
        var loadRetValIns = returnVoid ? null
                : new IrInstruction(
                IrInstruction.Genre.LOAD,
                currentFunction.returnType);
        if (!returnVoid) {
            loadRetValIns.loadAddress = currentFunction.retValPtr;
            retBlock.instructions.add(loadRetValIns);
        }
        var retIns = new IrInstruction(
                IrInstruction.Genre.RETURN,
                currentFunction.returnType);
        retIns.returnValue = returnVoid ?
                new IrId(currentFunction.returnType)
                : loadRetValIns.insId;
        retBlock.jumpInstruction = retIns;

        // Build statements of function body
        buildSuite(astNode.suite);

        // Jump to return block
        /* todo
        if(currentBlock.instructions.isEmpty()){
            // Set empty current block as return block
        } */
        buildJumpToBlock(retBlock, true);
        currentFunction.blocks.add(retBlock);

        // Pop scope
        scopeStack.pop();        // ? 此处可能需要恢复之前的 current*
    }

    /**
     * Build statements in a new scope
     */
    void buildSuite(NodeSuite astNode) {
        scopeStack.push(new HashMap<>());
        for (var stmt : astNode.statements)
            buildStatement(stmt);
        scopeStack.pop();
    }
    // endregion

    // region Statement
    public void visitVariableDefine(NodeVariableDefine astNode, boolean isGlobal) {
        if (isGlobal) {
            currentFunction = irRoot.functions.get(INITIAL_FUNCTION);
            currentBlock = currentFunction.blocks.getLast();
        }
        buildSourceCodeComment(astNode);
        for (var term : astNode.variableTerms)
            buildVariableTerm(astNode.type, term, isGlobal);
    }

    /**
     * Build variable define instructions and put variable pointer into scopeStack.
     */
    public void buildVariableTerm(NodeType astType, NodeVariableTerm astTerm, boolean isGlobal) {
        var initExpr = astTerm.initialExpression;
        IrInstruction ins;
        if (isGlobal) {
            var type = new IrType(astType);
            ins = new IrInstruction(
                    IrInstruction.Genre.GLOBAL_VARIABLE,
                    new IrId(type.getPointer(), GLOBAL_VARIABLE_PREFIX + astTerm.name));
            irRoot.variableDefines.add(ins);
            // 将新定义的全局变量 (ins.insId) 放至作用域栈底 (全局变量区域)
            scopeStack.getLast().put(astTerm.name, ins.insId);
            if (initExpr != null) {
                // 在 Initial Function 中生成初始化指令
                var initVal = buildExpression(initExpr, false);
                buildAssignToMem(initVal, ins.insId);
            }
        } else {
            ins = new IrInstruction(IrInstruction.Genre.ALLOCA, new IrType(astType));
            currentBlock.instructions.add(ins);
            // 将新定义的局部变量 (ins.insId) 放至作用域栈顶 (当前局部变量区域)
            scopeStack.peek().put(astTerm.name, ins.insId);
            if (initExpr != null) {
                var rstId = buildExpression(initExpr, false);
                buildAssignToMem(rstId, ins.insId);
            }
        }
    }

    public void buildStatement(NodeStatement astNode) {
        // Generate source code comment
        switch (astNode.genre) {
            case SUITE, EMPTY, VARIABLE_DEFINE -> {
            }   // Some single expression which isn't statement,
            // like IF condition, need to be build in addition.
            default -> buildSourceCodeComment(astNode);
        }
        // * 需要保证添加 Statement 后, currentBlock 与后续 Block 的关系不变
        switch (astNode.genre) {
            case SUITE -> buildSuite(astNode.suite);
            case IF -> {
                // 假设当前有若干块: B1, {B2}, B3..., currentBlock == B2
                // 构造 IF 语句后将 B2 扩展为 4 个块 (CondFalse 可缺省)
                // B1, {B2(Cond), CondTrue, CondFalse, Next}, B3...
                // currentBlock == Next
                var originalJumpIns = currentBlock.jumpInstruction;
                // Add 2 blocks: CondTrue, CondFalse
                // Calculate condition at current block
                var nextBlock = new IrBlock();
                var trueBlock = new IrBlock();
                var falseBlock = (astNode.falseBranchStmt == null)
                        ? nextBlock : (new IrBlock());

                // Build condition expression, whose return value must be 'i1'
                buildSourceCodeComment(astNode.ifCondExpr);
                var condRstId = buildExpression(astNode.ifCondExpr, false);
                buildBranch(condRstId, trueBlock, falseBlock);
                // Build true block
                currentFunction.blocks.add(trueBlock);
                currentBlock = trueBlock;
                buildJumpToBlock(nextBlock, false);
                buildStatement(astNode.trueBranchStmt);
                if (astNode.falseBranchStmt != null) {
                    currentFunction.blocks.add(falseBlock);
                    currentBlock = falseBlock;
                    buildJumpToBlock(nextBlock, false);
                    buildStatement(astNode.falseBranchStmt);
                }
                // Set 'nextBlock'
                currentFunction.blocks.add(nextBlock);
                currentBlock = nextBlock;
                currentBlock.jumpInstruction = originalJumpIns;
                buildComment("end if");
            }
            case FOR -> {
                var originalJumpIns = currentBlock.jumpInstruction;
                // 3 New Block: (ForInit), ForCond, ForBodyAndStep, NextBlock
                var condBlock = new IrBlock();
                var bodyAndStepBlock = new IrBlock();
                var nextBlock = new IrBlock();

                scopeStack.push(new HashMap<>());
                if (astNode.initialWithVarDef)
                    visitVariableDefine(astNode.initialVarDef, false);
                else {
                    buildSourceCodeComment(astNode.initialExpr);
                    buildExpression(astNode.initialExpr, false);
                }
                buildJumpToBlock(condBlock, true);
                // Condition
                currentFunction.blocks.add(condBlock);
                buildSourceCodeComment(astNode.forCondExpr);
                var condId = buildExpression(astNode.forCondExpr, false);
                buildBranch(condId, bodyAndStepBlock, nextBlock);
                // Body and Step
                currentFunction.blocks.add(bodyAndStepBlock);
                currentBlock = bodyAndStepBlock;
                buildJumpToBlock(condBlock, false);
                loopCondBlockStack.add(condBlock);
                loopNextBlockStack.add(nextBlock);
                buildStatement(astNode.forBodyStmt);
                loopCondBlockStack.pop();
                loopNextBlockStack.pop();
                buildSourceCodeComment(astNode.stepExpr);
                buildExpression(astNode.stepExpr, false);
                // Next
                currentFunction.blocks.add(nextBlock);
                scopeStack.pop();
                currentBlock = nextBlock;
                nextBlock.jumpInstruction = originalJumpIns;
                buildComment("end for");
            }
            case WHILE -> {
                var originalJumpIns = currentBlock.jumpInstruction;
                // 2 new block: WhileCond, WhileBody
                var condBlock = new IrBlock();
                var bodyBlock = new IrBlock();
                var nextBlock = new IrBlock();

                buildJumpToBlock(condBlock, true);
                // Condition
                currentFunction.blocks.add(condBlock);
                buildSourceCodeComment(astNode.whileCondExpr);
                var condId = buildExpression(astNode.whileCondExpr, false);
                buildBranch(condId, bodyBlock, nextBlock);
                // Body
                currentFunction.blocks.add(bodyBlock);
                currentBlock = bodyBlock;
                buildJumpToBlock(condBlock, false);
                loopCondBlockStack.add(condBlock);
                loopNextBlockStack.add(nextBlock);
                buildStatement(astNode.whileBodyStmt);
                loopCondBlockStack.pop();
                loopNextBlockStack.pop();
                // Next
                currentFunction.blocks.add(nextBlock);
                currentBlock = nextBlock;
                nextBlock.jumpInstruction = originalJumpIns;
                buildComment("end while");
            }
            case CONTINUE -> {
                // 跳至上一个 Block (Body -> Cond)
                buildJumpToBlock(loopCondBlockStack.peek(), false);
            }
            case BREAK -> {
                // 跳至下一个 Block (Body -> Out of Loop)
                buildJumpToBlock(loopNextBlockStack.peek(), false);
            }
            case RETURN -> {
                // 将返回值存至内存
                if (currentFunction.returnType.genre != IrType.Genre.VOID) {
                    var ins = new IrInstruction(
                            IrInstruction.Genre.STORE,
                            currentFunction.returnType);
                    ins.storeAddress = currentFunction.retValPtr;
                    ins.storeData = buildExpression(astNode.returnExpr, false);
                    currentBlock.instructions.add(ins);
                }
                buildJumpToBlock(currentFunction.returnBlock, false);
            }
            case SINGLE_EXPRESSION -> {
                buildExpression(astNode.singleExpr, false);
            }
            case VARIABLE_DEFINE -> {
                visitVariableDefine(astNode.variableDefine, false);
            }
            // case EMPTY
        }
    }

    /**
     * @return Return a register stores the value of expression
     */
    public IrId buildExpression(NodeExpression astNode, boolean isLeftValue) {
        // ? 每个 case 应以 add ins & ret insId 结尾，且调用 buildExpression 应该优先使用 isLeftValue 而非指定布尔值
        // 检查错误的的左值情况
        /* switch (astNode.genre) {
//            case PAREN,ATOM,MEMBER,ASSIGN
            case FUNCTION, NEW, SELF, UNARY, BINARY, LAMBDA -> {
                // 前缀 ++/-- 可作为左值
                if (isLeftValue && (astNode.genre != NodeExpression.Genre.UNARY ||
                        (astNode.operator != NodeExpression.OpEnum.INC
                                && astNode.operator != NodeExpression.OpEnum.DEC)))
                    throwUnexpectedError();
            }
        } 我希望 Semantic Check 是靠谱的。*/
        switch (astNode.genre) {
            case PAREN -> {
                return buildExpression(astNode.parenExpr, isLeftValue);
            }
            case ATOM -> {
                var atomNode = astNode.atom;
                IrId atomId = null;
                switch (atomNode.genre) {
                    case THIS, IDENTIFIER -> {
                        var variableName = (atomNode.genre == NodeAtom.Genre.THIS)
                                ? THIS_VARIABLE_NAME : atomNode.identifier;
                        for (var scope : scopeStack) {
                            atomId = scope.get(variableName);
                            if (atomId != null) break;
                        }
                        if (atomId == null) throw new InternalError(
                                "IR",
                                "Cannot find identifier definition in buildExpression()");
                        if (!isLeftValue) {
                            // Get variable value when variable at right value expression.
                            atomId = buildGetFromMem(atomId);
                        }
                    }
                    case DECIMAL_INTEGER -> {
                        atomId = new IrId(new IrType(IrType.Genre.I32),
                                atomNode.decimalInteger);
                    }
                    case BOOLEAN -> {
                        atomId = new IrId(new IrType(IrType.Genre.I1),
                                (atomNode.booleanValue) ? 1 : 0);   // Trans Boolean to I1
                    }
                    default -> throwTodoError("buildExpression_1");
                }
                return atomId;
            }
            case MEMBER -> {
                throwTodoError("buildExpression_2");
                // todo
            }
            case ARRAY -> {
                throwTodoError("buildExpression_3");
                // todo
            }
            case FUNCTION -> {
                String funcName = null;
                switch (astNode.functionExpr.genre) {
                    case ATOM -> funcName = astNode.functionExpr.atom.identifier;
                    case MEMBER -> throwTodoError("Call method");
                    default -> throwUnexpectedError();
                }
                var func = irRoot.functions.get(funcName);
                var ins = new IrInstruction(IrInstruction.Genre.CALL, func.returnType);
                ins.callName = func.name;
                // NodeExpressionList is only used in Function Call
                ins.callArguments = new LinkedList<>();
                for (var expr : astNode.arguments.expressions)
                    ins.callArguments.add(buildExpression(expr, false));
                currentBlock.instructions.add(ins);
                return ins.insId;
            }
            case ASSIGN -> {
                var rValue = buildExpression(astNode.rValue, false);
                var lValuePointer = buildExpression(astNode.lValue, true);
                buildAssignToMem(rValue, lValuePointer);
                return lValuePointer;
            }
            case NEW -> {
                var ins = new IrInstruction(IrInstruction.Genre.CALL);
                throwTodoError("buildExpression_4");
                // todo Call function 'malloc'
            }
            // * Arithmetic
            case SELF -> { // 后缀++/--
                switch (astNode.operator) {
                    case INC, DEC -> {
                        var operandPtr = buildExpression(astNode.termExpr, true);
                        var operandVal = buildGetFromMem(operandPtr);
                        var ins = new IrInstruction(IrInstruction.Genre.ARITH, operandVal.type);
                        ins.arithOperandLeft = operandVal;
                        switch (astNode.operator) {
                            case INC -> // Suffix ++
                                    ins.opGenre = IrInstruction.operatorGenre.ADD;
                            case DEC -> // Suffix --
                                    ins.opGenre = IrInstruction.operatorGenre.SUB;
                            default -> throwUnexpectedError();
                        }
                        ins.arithOperandRight = new IrId(operandVal.type, 1);
                        currentBlock.instructions.add(ins);
                        buildAssignToMem(ins.insId, operandPtr);
                        return operandVal;
                    }
                    default -> throwUnexpectedError();
                }
            }
            case UNARY -> { // 前缀 ++/--/!/~/+/-
                switch (astNode.operator) {
                    case INC, DEC -> {
                        var operandPtr = buildExpression(astNode.termExpr, true);
                        var operandVal = buildGetFromMem(operandPtr);
                        var ins = new IrInstruction(IrInstruction.Genre.ARITH, operandVal.type);
                        ins.arithOperandLeft = operandVal;
                        switch (astNode.operator) {
                            case INC -> // Prefix ++
                                    ins.opGenre = IrInstruction.operatorGenre.ADD;
                            case DEC -> // Prefix --
                                    ins.opGenre = IrInstruction.operatorGenre.SUB;
                            default -> throwUnexpectedError();
                        }
                        ins.arithOperandRight = new IrId(operandVal.type, 1);
                        currentBlock.instructions.add(ins);
                        buildAssignToMem(ins.insId, operandPtr);
                        // 前缀 ++/-- 可以作为左值
                        if (isLeftValue) return operandPtr;
                        else return ins.insId;
                    }
                    default -> {
                        var operand = buildExpression(astNode.termExpr, false);
                        var ins = new IrInstruction(IrInstruction.Genre.ARITH, operand.type);
                        ins.arithOperandLeft = operand;
                        switch (astNode.operator) {
                            case BANG -> {  // !
                                ins.opGenre = IrInstruction.operatorGenre.XOR;
                                ins.arithOperandRight = new IrId(operand.type, 1);
                                if (operand.type.genre != IrType.Genre.I1) throw new InternalError(
                                        "IR",
                                        "Unexpected operator type in UNARY::BANG calculation in buildExpression()");
                            }
                            case TILDE -> { // ~
                                ins.opGenre = IrInstruction.operatorGenre.XOR;
                                ins.arithOperandRight = new IrId(operand.type, -1);
                                if (operand.type.genre != IrType.Genre.I32) throw new InternalError(
                                        "IR",
                                        "Unexpected operator type in UNARY::TILDE calculation in buildExpression()");
                            }
                            case ADD -> {   // Positive
                                return operand; // 此处直接返回
                            }
                            case SUB -> {   // Negative
                                ins.opGenre = IrInstruction.operatorGenre.SUB;
                                ins.arithOperandLeft = new IrId(operand.type, 0);
                                ins.arithOperandRight = operand;
                                if (operand.type.genre != IrType.Genre.I32) throw new InternalError(
                                        "IR",
                                        "Unexpected operator type in UNARY::SUB calculation in buildExpression()");
                            }
                            default -> throwUnexpectedError();
                        }
                        currentBlock.instructions.add(ins);
                        return ins.insId;
                    }
                }
            }
            case BINARY -> {
                switch (astNode.operator) {
                    case AND, OR -> {
                        // 短路求值
                        // todo
                    }
                    default -> {
                        var lOperand = buildExpression(astNode.lTermExpr, false);
                        var rOperand = buildExpression(astNode.rTermExpr, false);
                        if (!lOperand.type.equals(rOperand.type)) throw new InternalError(
                                "IR",
                                "Left and Right Operand of NodeExpression(BINARY) have Different Type in buildExpression()");
                        IrType insType;
                        switch (astNode.operator) {
                            case GT, LT, GE, LE, EQ, NOT_EQ -> insType = new IrType(IrType.Genre.I1);
                            default -> insType = lOperand.type;
                        }
                        var ins = new IrInstruction(IrInstruction.Genre.ARITH, insType);
                        ins.arithOperandLeft = lOperand;
                        ins.arithOperandRight = rOperand;
                        switch (astNode.operator) {
                            case ADD, SUB, MUL, DIV, MOD, SHIFT_L, SHIFT_R, GT, LT, GE, LE,
                                    EQ -> ins.opGenre = IrInstruction.operatorGenre.valueOf(
                                    astNode.operator.toString());   // 同名可直转
                            case NOT_EQ -> ins.opGenre = IrInstruction.operatorGenre.NEQ;
                            case BIT_AND -> ins.opGenre = IrInstruction.operatorGenre.AND;
                            case BIT_OR -> ins.opGenre = IrInstruction.operatorGenre.OR;
                            case CARET -> ins.opGenre = IrInstruction.operatorGenre.XOR;
                            default -> throwUnexpectedError();
                        }
                        currentBlock.instructions.add(ins);
                        return ins.insId;
                    }
                }
            }
            // No Lambda
        }
        throw new InternalError("IR", "Missing return value of buildExpression(), genre: " + astNode.genre);
    }
    // endregion
}
