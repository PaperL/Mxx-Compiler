package frontend;

import ast.*;
import ir.*;
import utility.IrId;
import utility.IrType;
import utility.error.InternalError;

import java.util.HashMap;
import java.util.LinkedList;

// Build tree structure LLVM IR from AST
public class IrBuilder {
    // region BASIC
    public final String GLOBAL_VARIABLE_PREFIX = "__VAR__";
    public final String FUNCTION_PREFIX = "__FUNC__";
    public final String INITIAL_FUNCTION = "__INIT";
    public final String THIS_VARIABLE_NAME = "__THIS";

    public IrTop irRoot = new IrTop();
    public IrFunction currentFunction = null;
    public IrBlock currentBlock = null;
    public LinkedList<HashMap<String, IrId>> scopeStack;

    // For Continue and Break, is 'null' when out of loop
    public LinkedList<IrBlock> loopCondBlockStack = new LinkedList<>();
    public LinkedList<IrBlock> loopNextBlockStack = new LinkedList<>();

    public IrBuilder() {
        scopeStack = new LinkedList<>();
        scopeStack.push(new HashMap<>());
    }

    public String print() {
        return irRoot.toString();
    }

    // endregion

    // region TOOL
    public static void throwTodoError(String pos) throws InternalError {
        // ! This function SHOULD NOT be used at any finished program.
        throw new InternalError("IR_DEBUG", "TODO: " + pos);
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
    public void buildJumpToBlock(IrBlock block) {
        var ins = new IrInstruction(IrInstruction.Genre.JUMP);
        ins.jumpLabel = block.label;
        currentBlock.jumpInstruction = ins;
        currentBlock = block;
    }

    /**
     * Finish currentBlock, jump to one of two blocks depending on
     * condition result, and set 'currentBlock' as 'null'
     */
    public void buildBranch(IrId condId, IrBlock trueBlock, IrBlock falseBlock) {
        var ins = new IrInstruction(IrInstruction.Genre.BRANCH);
        ins.branchCondId = condId;
        ins.branchTrueBlock = trueBlock;
        ins.branchFalseBlock = falseBlock;
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
        func.name = astNode.name;
        var arguments = astNode.argumentList;
        if (arguments != null) {
            for (var astType : arguments.types)
                func.arguments.add(new IrId(new IrType(astType)));
        }

        irRoot.functions.put(func.name, func);
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
            for (int i = 0; i < len; i++)
                currentScope.put(
                        arguments.identifiers.get(i),
                        currentFunction.arguments.get(i));
        }
        scopeStack.push(currentScope);

        // Declare return variable
        currentFunction.retValPtr = buildAlloca(currentFunction.returnType);

        // Build return block
        var retBlock = new IrBlock();
        currentFunction.returnBlock = retBlock;
        var loadRetValIns = new IrInstruction(
                IrInstruction.Genre.LOAD,
                currentFunction.returnType);
        loadRetValIns.loadAddress = currentFunction.retValPtr;
        var retIns = new IrInstruction(
                IrInstruction.Genre.RETURN,
                currentFunction.returnType);
        retIns.returnValue = loadRetValIns.insId;
        retBlock.instructions.add(loadRetValIns);
        retBlock.jumpInstruction = retIns;

        // Build statements of function body
        buildSuite(astNode.suite);

        // Jump to return block
        /* todo
        if(currentBlock.instructions.isEmpty()){
            // Set empty current block as return block
        } */
        buildJumpToBlock(retBlock);
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
        for (var term : astNode.variableTerms)
            buildVariableTerm(astNode.type, term, isGlobal);
    }

    public void buildVariableTerm(NodeType astType, NodeVariableTerm astTerm, boolean isGlobal) {
        var initExpr = astTerm.initialExpression;
        IrInstruction ins;
        if (isGlobal) {
            var type = new IrType(astType);
            ins = new IrInstruction(
                    IrInstruction.Genre.GLOBAL_VARIABLE,
                    new IrId(type, GLOBAL_VARIABLE_PREFIX + astTerm.name)
            );
            irRoot.variableDefines.add(ins);
            scopeStack.getLast().put(astTerm.name, ins.insId);    // 栈底
            if (initExpr != null) {
                throwTodoError("buildVariableTerm");
                // * Switch to initial function
                currentFunction = irRoot.functions.get(INITIAL_FUNCTION);
                currentBlock = currentFunction.blocks.get(0);
                // ? 此处可能需要恢复之前的 current*
                buildExpression(initExpr, false);
            }
        } else {
            ins = new IrInstruction(IrInstruction.Genre.ALLOCA, new IrType(astType));
            currentBlock.instructions.add(ins);
            scopeStack.peek().put(astTerm.name, ins.insId);
            if (initExpr != null) {
                var rstId = buildExpression(initExpr, false);
                buildAssignToMem(rstId, ins.insId);
            }
        }
    }

    public void buildStatement(NodeStatement astNode) {
        // * 需要保证添加 Statement 后, currentBlock 与后续 Block 的关系不变
        switch (astNode.genre) {
            case SUITE -> buildSuite(astNode.suite);
            case IF -> {
                var curJumpIns = currentBlock.jumpInstruction;
                // Add 3 blocks: Condition, CondTrue, CondFalse
                var condBlock = new IrBlock();
                var nextBlock = new IrBlock();
                var trueBlock = new IrBlock();
                var falseBlock = (astNode.falseBranchStmt == null)
                        ? nextBlock : (new IrBlock());

                currentFunction.blocks.add(condBlock);
                currentFunction.blocks.add(trueBlock);
                if (astNode.falseBranchStmt != null)
                    currentFunction.blocks.add(falseBlock);
                currentFunction.blocks.add(nextBlock);

                loopCondBlockStack.push(condBlock);
                loopNextBlockStack.push(nextBlock);

                // Build condition block
                buildJumpToBlock(condBlock);
                var condRstId = buildExpression(astNode.ifCondExpr, false);
                buildBranch(condRstId, trueBlock, falseBlock);

                // Build true block
                currentBlock = trueBlock;
                buildStatement(astNode.trueBranchStmt);
                buildJumpToBlock(nextBlock);
                if (astNode.falseBranchStmt != null) {
                    currentBlock = falseBlock;
                    buildStatement(astNode.falseBranchStmt);
                    buildJumpToBlock(nextBlock);
                }

                // Set 'nextBlock'
                currentBlock.jumpInstruction = curJumpIns;

                loopCondBlockStack.pop();
                loopNextBlockStack.pop();
            }
            case FOR -> {
                // Block: ForInit, ForCond, ForBody
            }
            case WHILE -> {
                // Block: WhileCond, WhileBody
            }
            case CONTINUE -> {
                // 跳至上一个 Block (Body -> Cond)
                buildJumpToBlock(loopCondBlockStack.peek());
            }
            case BREAK -> {
                // 跳至下一个 Block (Body -> Out of Loop)
                buildJumpToBlock(loopNextBlockStack.peek());
            }
            case RETURN -> {
                // 将返回值存至内存
                var ins = new IrInstruction(
                        IrInstruction.Genre.STORE,
                        currentFunction.returnType);
                ins.storeAddress = currentFunction.retValPtr;
                ins.storeData = buildExpression(astNode.returnExpr, false);
                currentBlock.instructions.add(ins);
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
        // todo 每个 case 应以 add ins & ret insId 结尾，且调用 buildExpression 应该优先使用 isLeftValue 而非指定布尔值
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
                String funcName;
                switch (astNode.genre) {
                    case ATOM -> funcName = astNode.atom.identifier;
                    case MEMBER -> funcName = astNode.memberName;
                    default -> throw new InternalError(
                            "IR",
                            "Unexpected functionExpr of NodeExpression in buildExpression()");
                }
                var func = irRoot.functions.get(funcName);
                var ins = new IrInstruction(IrInstruction.Genre.CALL, func.returnType);
                ins.callName = func.name;
                // NodeExpressionList is only used in Function Call
                for (var expr : astNode.arguments.expressions)
                    ins.callArguments.add(buildExpression(expr, false));
                currentBlock.instructions.add(ins);
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
                // todo 打标记，在当前 Statement 完成后执行自增自减运算
                switch (astNode.operator) {
                    case INC, DEC -> {
                        var operand = buildExpression(astNode.termExpr, true);
                        throwTodoError("buildExpression_5");
                    }
                    default -> throw new InternalError(
                            "IR",
                            "Unexpected operator type of NodeExpression(SELF) in buildExpression()");
                }
            }
            case UNARY -> { // 前缀 ++/--/!/~/+/-
                var operand = buildExpression(astNode.termExpr, false);
                var ins = new IrInstruction(IrInstruction.Genre.ARITH, operand.type);
                ins.arithOperandLeft = operand;
                switch (astNode.operator) {
                    case INC -> {   // Prefix ++
                        ins.opGenre = IrInstruction.operatorGenre.ADD;
                        ins.arithOperandRight = new IrId(operand.type, 1);
                        if (operand.type.genre != IrType.Genre.I32) throw new InternalError(
                                "IR",
                                "Unexpected operator type in UNARY::INC calculation in buildExpression()");
                    }
                    case DEC -> {   // Prefix --
                        ins.opGenre = IrInstruction.operatorGenre.SUB;
                        ins.arithOperandRight = new IrId(operand.type, 1);
                        if (operand.type.genre != IrType.Genre.I32) throw new InternalError(
                                "IR",
                                "Unexpected operator type in UNARY::DEC calculation in buildExpression()");
                    }
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
                        // * 此处直接返回
                        return operand;
                    }
                    case SUB -> {   // Negative
                        ins.opGenre = IrInstruction.operatorGenre.SUB;
                        ins.arithOperandLeft = new IrId(operand.type, 0);
                        ins.arithOperandRight = operand;
                        if (operand.type.genre != IrType.Genre.I32) throw new InternalError(
                                "IR",
                                "Unexpected operator type in UNARY::SUB calculation in buildExpression()");
                    }
                    default -> throw new InternalError(
                            "IR",
                            "Unexpected operator type of NodeExpression(UNARY) in buildExpression()");
                }
            }
            case BINARY -> {
                var lOperand = buildExpression(astNode.lTermExpr, false);
                var rOperand = buildExpression(astNode.rTermExpr, false);
                if (!lOperand.type.equals(rOperand.type)) throw new InternalError(
                        "IR",
                        "Left and Right Operand of NodeExpression(BINARY) have Different Type in buildExpression()");
                var ins = new IrInstruction(IrInstruction.Genre.ARITH, lOperand.type);
                ins.arithOperandLeft = lOperand;
                ins.arithOperandRight = rOperand;
                switch (astNode.operator) {
                    case ADD -> {
                        ins.opGenre = IrInstruction.operatorGenre.ADD;
                    }
                    case SUB -> {
                    }
                    case MUL -> {
                    }
                    case DIV -> {
                    }
                    case MOD -> {
                    }
                    case SHIFT_L -> {
                    }
                    case SHIFT_R -> {
                    }
                    case GT -> {
                    }
                    case LT -> {
                    }
                    case GE -> {
                    }
                    case LE -> {
                    }
                    case EQ -> {
                    }
                    case NOT_EQ -> {
                    }
                    case BIT_AND -> {
                    }
                    case BIT_OR -> {
                    }
                    case CARET -> {
                    }
                    case AND -> {
                    }
                    case OR -> {
                    }
                    default -> throw new InternalError(
                            "IR",
                            "Unexpected operator type of NodeExpression(BINARY) in buildExpression()");
                }
                currentBlock.instructions.add(ins);
                return ins.insId;
            }
            // No Lambda
            default -> throw new InternalError(
                    "IR",
                    "Unexpected genre of NodeExpression in buildExpression()");
        }
        throw new InternalError("IR", "Missing return value of buildExpression() " + astNode.genre);
    }
    // endregion
}
