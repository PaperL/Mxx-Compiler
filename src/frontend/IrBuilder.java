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

    public IrTop irRoot = null;
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
    // endregion

    // region TOOL
    public void buildAssignToMem(IrId from, IrId to) {
        var ins = new IrInstruction(IrInstruction.Genre.STORE);
        ins.storeType = from.type;
        ins.storeData = from;
        ins.storeAddress = to;
        currentBlock.instructions.add(ins);
    }

    /**
     * @param type Type to alloca
     * @return Pointer IrId
     */
    public IrId buildAllca(IrType type) {
        var ins = new IrInstruction(IrInstruction.Genre.ALLOCA);
        ins.allocaType = type;
        currentBlock.instructions.add(ins);
        return ins.rstId;
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
        var initFuncRetIns = new IrInstruction(IrInstruction.Genre.RETURN);
        initFuncRetIns.returnType = initFunc.returnType;    // VOID
        initFuncBlock.jumpInstruction = initFuncRetIns;
        initFunc.blocks.add(initFuncBlock);
        irRoot.functions.put(initFunc.name, initFunc);

        // Depth-first traversal AST tree
        // 函数和类支持前向引用, 需要先收集符号
        for (var section : astNode.programSections) {
            switch (section.genre) {
                case CLASS_DEFINE -> declareClass(section.classDefineNode);
                case FUNCTION_DEFINE -> declareFunction(section.functionDefine);
            }
        }
        // 全局变量不支持前向引用
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
            for (var astType : arguments.types) {
                func.argumentTypes.add(new IrType(astType));
            }
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
                        new IrId(currentFunction.argumentTypes.get(i)));
        }
        scopeStack.push(currentScope);

        // Declare return variable
        currentFunction.returnId = buildAllca(currentFunction.returnType);

        // Build return block
        var retBlock = new IrBlock();
        var retIns = new IrInstruction(IrInstruction.Genre.RETURN);
        retIns.returnType = currentFunction.returnType;
        retIns.returnValue = currentFunction.returnId;
        retBlock.jumpInstruction = retIns;
        currentFunction.returnBlock = retBlock;

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
            ins.globalVariableType = type;
            irRoot.variableDefines.add(ins);
            scopeStack.getLast().put(astTerm.name, ins.rstId);    // 栈底
            if (initExpr != null) {
                // * Switch to initial function
                currentFunction = irRoot.functions.get(INITIAL_FUNCTION);
                currentBlock = currentFunction.blocks.get(0);
                // ? 此处可能需要恢复之前的 current*
                buildExpression(initExpr);
            }
        } else {
            ins = new IrInstruction(IrInstruction.Genre.ALLOCA);
            ins.allocaType = new IrType(astType);
            currentBlock.instructions.add(ins);
            scopeStack.peek().put(astTerm.name, ins.rstId);
            if (initExpr != null) {
                var rstId = buildExpression(initExpr);
                buildAssignToMem(rstId, ins.rstId);
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
                var condRstId = buildExpression(astNode.ifCondExpr);
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
                var ins = new IrInstruction(IrInstruction.Genre.RETURN);
                ins.returnType = currentFunction.returnType;
                ins.returnValue = buildExpression(astNode.returnExpr);
                currentBlock.instructions.add(ins);
            }
            case SINGLE_EXPRESSION -> {
                buildExpression(astNode.singleExpr);
            }
            case VARIABLE_DEFINE -> {
                visitVariableDefine(astNode.variableDefine, false);
            }
            // case EMPTY
        }
    }

    public IrId buildExpression(NodeExpression astNode) {
        switch (astNode.genre) {
            case PAREN -> {
                return buildExpression(astNode.parenExpr);
            }
            case ATOM -> {
                var atomNode = astNode.atom;
                switch (atomNode.genre) {
                    case THIS -> {
                    }
                    case IDENTIFIER -> {
                    }
                    case STRING_CONSTANT -> {
                    }
                    case DECIMAL_INTEGER -> {
                    }
                    case BOOLEAN -> {
                    }
                    case NULL -> {
                    }
                }
            }
            case MEMBER -> {
                // todo
            }
            case ARRAY -> {
                // todo
            }
            case FUNCTION -> {
                // todo call
            }
            case ASSIGN -> {

            }
            case NEW -> {

            }
            case SELF -> {

            }
            case UNARY -> {

            }
            case BINARY -> {

            }
            // No Lambda
        }
        throw new InternalError(
                "IR",
                "Uncatched situation in buildExpression()");
    }
    // endregion
}
