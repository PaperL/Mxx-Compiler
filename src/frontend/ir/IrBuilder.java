package frontend.ir;

import frontend.ast.AstBuilder;
import frontend.ast.node.*;
import frontend.ir.node.*;
import org.antlr.v4.runtime.misc.Pair;
import utility.CmdArgument;
import utility.error.InternalError;
import utility.error.SemanticError;

import java.util.*;

/**
 * Build sequential structure LLVM IR from AST
 */
public class IrBuilder {
    public static final IrTop irRoot = new IrTop();
    // region BASIC
    public static CmdArgument cmdArgs = null;
    // Internal naming prefix
    public final String GLOBAL_VARIABLE_PREFIX = "__VAR__";
    public final String CONSTANT_STRING_PREFIX = "__CONSTANT_STR__";
    public final String FUNCTION_PREFIX = "__FUNC__";
    public final String CLASS_CONSTRUCTOR_PREFIX = "__CONSTRUCTOR__";
    public final String CLASS_PREFIX = "__CLAS__", METHOD_PREFIX = "__MTHD__";
    //    public final String INITIAL_FUNCTION = "__INIT";
//    // Built-in basic functions
//    public final String NEW_FUNCTION = "__NEW_ON_HEAP";
//    public final String NEW_ARRAY_FUNCTION = "__NEW_ARRAY";
//    // Built-in system functions
//    public final String PRINT_FUNCTION = "__PRINT";
//    public final String PRINTLN_FUNCTION = "__PRINTLN";
//    public final String PRINT_INT_FUNCTION = "__PRINT_INT";
//    public final String PRINTLN_INT_FUNCTION = "__PRINTLN_INT";
//    public final String GET_STRING_FUNCTION = "__GET_STRING";
//    public final String GET_INT_FUNCTION = "__GET_INT";
//    public final String TO_STRING_FUNCTION = "__TO_STRING";
//    // Bulit-in string functions
//    public final String STRING_ADD_FUNCTION = "__STRING_ADD";
//    public final String STRING_EQUAL_FUNCTION = "__STRING_EQUAL";
//    public final String STRING_NOT_EQUAL_FUNCTION = "__STRING_NOT_EQUAL";
//    public final String STRING_LESS_FUNCTION = "__STRING_LESS";
//    public final String STRING_GREATER_FUNCTION = "__STRING_GREATER";
//    public final String STRING_LESS_OR_EQUAL_FUNCTION = "__STRING_LESS_OR_EQUAL";
//    public final String STRING_GREATER_OR_EQUAL_FUNCTION = "__STRING_GREATER_OR_EQUAL";
    // Internal Functions
    public final Map<String, String> internalFuncName = Map.ofEntries(
            // Built-in basic functions
            Map.entry("_init", "__INIT"),
            Map.entry("_new", "malloc"),
            Map.entry("_new_array", "__NEW_ARRAY"),
            // Built-in system functions
            Map.entry("print", "__PRINT"),
            Map.entry("println", "__PRINTLN"),
            Map.entry("printInt", "__PRINT_INT"),
            Map.entry("printlnInt", "__PRINTLN_INT"),
            Map.entry("getString", "__GET_STRING"),
            Map.entry("getInt", "__GET_INT"),
            Map.entry("toString", "__TO_STRING"),
            // Built-in string functions
            Map.entry("_string_add", "__STRING_ADD"),
            Map.entry("_string_equal", "__STRING_EQUAL"),
            Map.entry("_string_not_equal", "__STRING_NOT_EQUAL"),
            Map.entry("_string_less", "__STRING_LESS"),
            Map.entry("_string_greater", "__STRING_GREATER"),
            Map.entry("_string_less_or_equal", "__STRING_LESS_OR_EQUAL"),
            Map.entry("_string_greater_or_equal", "__STRING_GREATER_OR_EQUAL"),
            Map.entry("substring", "__STRING_SUBSTRING"),
            Map.entry("parseInt", "__STRING_PARSE_INT"),
            Map.entry("ord", "__STRING_ORD")

    );
    // scopeStack 中变量名无 GLOBAL_VARIABLE_PREFIX 前缀
    // 但对应的 IrId 名称含有前缀
    public final LinkedList<HashMap<String, IrId>> scopeStack = new LinkedList<>();
    // For Continue and Break, is 'null' when out of loop
    public final LinkedList<IrBlock> loopCondBlockStack = new LinkedList<>();
    public final LinkedList<IrBlock> loopNextBlockStack = new LinkedList<>();
    public IrFunction currentFunction = null;
    public IrBlock currentBlock = null;
    // 常量字符串计数器
    public int constantStringCnt = 0;

    public IrBuilder(CmdArgument cmdArgs_) {
        cmdArgs = cmdArgs_;
        scopeStack.push(new HashMap<>());   // Global scope
        init();
    }

    // region TOOL
    public static void throwTodoError(String pos) throws InternalError {
        // ! This function SHOULD NOT be used at any finished program.
        throw new InternalError("IR_DEBUG", "TODO: " + pos);
    }

    public static void throwUnexpectedError() throws InternalError {
        throw new InternalError("IR_UNEXPECTED", "");
    }

    public String print() {
        irRoot.genIndex();
        return irRoot.toString();
    }

    public void setFunction(String callName, String insideName, IrType returnType,
                            LinkedHashMap<String, IrFunction> funcMap) {
        var func = new IrFunction();
        func.name = insideName;
        func.returnType = returnType;
        funcMap.put(callName, func);
    }

    // endregion

    public void setFunction(String funcName, IrType returnType,
                            LinkedHashMap<String, IrFunction> funcMap) {
        var func = new IrFunction();
        func.name = funcName;
        func.returnType = returnType;
        funcMap.put(funcName, func);
    }

    // * Built-in
    public void init() {
        // Basic information
        buildDeclare(String.format("""
                        target datalayout = "e-m:e-p:32:32-i64:64-n32-S128"
                        target triple = "riscv32"
                                        
                        declare i8*  @%s(i32)
                        declare i8*  @%s(i32, i32, i32*)
                                                
                        declare void @%s(i8*)
                        declare void @%s(i8*)
                        declare void @%s(i32)
                        declare void @%s(i32)
                        declare i8*  @%s()
                        declare i32  @%s()
                        declare i8*  @%s(i32)
                                                
                        declare i8*  @%s(i8*, i8*)
                        declare i8   @%s(i8*, i8*)
                        declare i8   @%s(i8*, i8*)
                        declare i8   @%s(i8*, i8*)
                        declare i8   @%s(i8*, i8*)
                        declare i8   @%s(i8*, i8*)
                        declare i8   @%s(i8*, i8*)
                                                
                        declare i8*  @%s(i8*, i32, i32)
                        declare i32  @%s(i8*)
                        declare i32  @%s(i8*, i32)
                                                
                        """,
                internalFuncName.get("_new"),
                internalFuncName.get("_new_array"),

                internalFuncName.get("print"),
                internalFuncName.get("println"),
                internalFuncName.get("printInt"),
                internalFuncName.get("printlnInt"),
                internalFuncName.get("getString"),
                internalFuncName.get("getInt"),
                internalFuncName.get("toString"),

                internalFuncName.get("_string_add"),
                internalFuncName.get("_string_equal"),
                internalFuncName.get("_string_not_equal"),
                internalFuncName.get("_string_less"),
                internalFuncName.get("_string_greater"),
                internalFuncName.get("_string_less_or_equal"),
                internalFuncName.get("_string_greater_or_equal"),

                internalFuncName.get("substring"),
                internalFuncName.get("parseInt"),
                internalFuncName.get("ord")
        ));


//        // Set built-in system functions
//        var funcMap = irRoot.functions;
//        // * 'string' type equals to 'i8*'
//        var voidType = new IrType(IrType.Genre.VOID);
//        var strType = (new IrType(IrType.Genre.I8)).getPointer();
//        // void print(string str);
//        setFunction("print", PRINT_FUNCTION, voidType, funcMap);
//        // void println(string str);
//        setFunction("println", PRINTLN_FUNCTION, voidType, funcMap);
//        // void printInt(int n);
//        setFunction("printInt", PRINT_INT_FUNCTION, voidType, funcMap);
//        // void printlnInt(int n);
//        setFunction("printlnInt", PRINTLN_INT_FUNCTION, voidType, funcMap);
//        // string getString();
//        setFunction("getString", GET_STRING_FUNCTION, strType, funcMap);
//        // int getInt();
//        setFunction("getInt", GET_INT_FUNCTION, new IrType(IrType.Genre.I32), funcMap);
//        // string toString(int i);
//        setFunction("toString", TO_STRING_FUNCTION, strType, funcMap);
//
//        // String functions
//        var boolType = new IrType(IrType.Genre.I1);
//        setFunction(STRING_ADD_FUNCTION, strType, funcMap);
//        setFunction(STRING_EQUAL_FUNCTION, boolType, funcMap);
//        setFunction(STRING_NOT_EQUAL_FUNCTION, boolType, funcMap);
//        setFunction(STRING_LESS_FUNCTION, boolType, funcMap);
//        setFunction(STRING_GREATER_FUNCTION, boolType, funcMap);
//        setFunction(STRING_LESS_OR_EQUAL_FUNCTION, boolType, funcMap);
//        setFunction(STRING_GREATER_OR_EQUAL_FUNCTION, boolType, funcMap);


        // Add initialization function
        // * 初始化函数只有一个 Block
        IrFunction initFunc = new IrFunction();
        initFunc.returnType = new IrType(IrType.Genre.VOID);
        initFunc.name = internalFuncName.get("_init");
        var initFuncBlock = new IrBlock();
        initFuncBlock.jumpInstruction = new IrInstruction(
                IrInstruction.Genre.RETURN,
                initFunc.returnType);
        initFunc.blocks.add(initFuncBlock);
        irRoot.functions.put(initFunc.name, initFunc);
    }

    public IrId createI8Constant(int k) {
        return (new IrId(new IrType(IrType.Genre.I8), k));
    }

    public IrId createI32Constant(int k) {
        return (new IrId(new IrType(IrType.Genre.I32), k));
    }

    public void buildDeclare(String declareInfo) {
        var declare = new IrInstruction(IrInstruction.Genre.DECLARE);
        declare.declareInfo = declareInfo;
        irRoot.declares.add(declare);
    }

    public void buildComment(String commentInfo) {
        if (cmdArgs.contains(CmdArgument.ArgumentType.IR_SOURCE_CODE)) {
            var ins = new IrInstruction(IrInstruction.Genre.COMMENT);
            ins.commentInfo = commentInfo;
            currentBlock.instructions.add(ins);
        }
        if (cmdArgs.contains(CmdArgument.ArgumentType.DEBUG))
            System.out.println("# " + commentInfo);
    }

    /**
     * Generate comment of corresponding source code.
     * Just show the first line of multiline statement, e.g. IF, FOR.
     */
    public void buildSourceCodeComment(AstNode astNode) {
        var ins = new IrInstruction(IrInstruction.Genre.COMMENT);
        var str = astNode.position.rawText;
        var end = str.indexOf('\n');
        ins.commentInfo = (end == -1) ? str : str.substring(0, str.indexOf('\n'));
        if (cmdArgs.contains(CmdArgument.ArgumentType.IR_SOURCE_CODE))
            currentBlock.instructions.add(ins);
        if (cmdArgs.contains(CmdArgument.ArgumentType.DEBUG))
            System.out.println("# " + ins.commentInfo);
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
        // Depth-first traversal AST tree
        // 函数和类支持前向引用, 需要先收集符号
        // 而全局变量不支持前向引用
        for (var section : astNode.programSections) {
            switch (section.genre) {
                case CLASS_DEFINE -> declareClass(section.classDefineNode);
                case FUNCTION_DEFINE -> declareFunction(section.functionDefine, null);
            }
        }

        for (var section : astNode.programSections) {
            switch (section.genre) {
                case VARIABLE_DEFINE -> visitVariableDefine(
                        section.globalVariableDefine, true);
                case CLASS_DEFINE -> buildClass(section.classDefineNode);
                case FUNCTION_DEFINE -> buildFunction(section.functionDefine, null);
            }
        }
    }

    public void declareClass(NodeClassDefine astNode) {
        // DO NOT ignore built-in classes
        var clas = new IrClass();
        clas.builtIn = astNode.builtIn;
        clas.name = astNode.name;
        // 题面 #14-命名空间:"类不可以和变量、函数重名". 故类名无需前缀
        irRoot.classes.put(clas.name, clas);

        var fieldCnt = 0;
        for (var fieldDefine : astNode.variableDefines) {
            var type = new IrType(fieldDefine.type);
            for (int i = 0, len = fieldDefine.variableTerms.size(); i < len; i++) {
                clas.fields.put(
                        fieldDefine.variableTerms.get(i).name,
                        new Pair<>(fieldCnt++, type));
            }
//            IntStream.range(0, fieldDefine.variableTerms.size())
//                    .forEach(i -> /* sth */);
        }
        if (cmdArgs.contains(CmdArgument.ArgumentType.DEBUG)) {
            // ? 这里省略 String.format 会报错
            System.out.printf("\nClass '%s' field:\n%s\n%n",
                    clas.name, clas.fields);
        }

        clas.constructor = new IrFunction();
        clas.constructor.returnType = new IrType(IrType.Genre.VOID);
        clas.constructor.name = CLASS_CONSTRUCTOR_PREFIX + clas.name;
        clas.constructor.clas = clas;
        NodeFunctionDefine constructor = null;
        for (var methodDefine : astNode.methodDefines) {
            if (methodDefine.type != null)
                declareFunction(methodDefine, clas);
            else constructor = methodDefine;    // Constructor
        }
        clas.constructor.arguments.add(new IrId(new IrType(clas)));
        if (constructor != null) {  // Explicit constructor
            var arguments = constructor.argumentList;
            if (arguments != null) {
                for (var astType : arguments.types)
                    clas.constructor.arguments.add(new IrId(new IrType(astType)));
            }
        }
    }

    public void declareFunction(NodeFunctionDefine astNode, IrClass clas) {
        // DO NOT ignore built-in functions

        var isMethod = (clas != null);
        var func = new IrFunction();

        func.builtIn = astNode.builtIn;
        func.returnType = new IrType(astNode.type);
        if (astNode.builtIn)
            func.name = internalFuncName.get(astNode.name);
        else if (isMethod)
            func.name = CLASS_PREFIX + clas.name + METHOD_PREFIX
                    + astNode.name;
        else if (Objects.equals(astNode.name, "main"))
            func.name = "main";
        else func.name = FUNCTION_PREFIX + astNode.name;

        if (isMethod) {
            func.clas = clas;
            // Add 'this'
            func.arguments.add(new IrId(new IrType(clas)));
        }
        var arguments = astNode.argumentList;
        if (arguments != null) {
            for (var astType : arguments.types)
                func.arguments.add(new IrId(new IrType(astType)));
        }

        if (isMethod) clas.methods.put(astNode.name, func);
        else irRoot.functions.put(astNode.name, func);
    }

    public void buildClass(NodeClassDefine astNode) {
        if (astNode.builtIn) return;  // Just ignore built-in classes

        var clas = irRoot.classes.get(astNode.name);
        for (var methodDefine : astNode.methodDefines)
            buildFunction(methodDefine, clas);
        if (clas.constructor.blocks.isEmpty()) {
            // Generate default empty constructor
            var onlyBlock = new IrBlock();
            clas.constructor.returnBlock = onlyBlock;
            onlyBlock.jumpInstruction = new IrInstruction(
                    IrInstruction.Genre.RETURN,
                    new IrType(IrType.Genre.VOID));
            clas.constructor.blocks.add(onlyBlock);
        }

        // ? 这一段或许有些冗长且混乱
        var thisPtr = clas.constructor.arguments.getFirst();
        currentBlock = new IrBlock();
        for (var fieldName : clas.fields.keySet()) {
//        for (var fieldPair : clas.fields.values()) {
            var fieldPair = clas.fields.get(fieldName);
            if (fieldPair.b.genre != IrType.Genre.COMPOSITE)
                continue;
            buildComment(String.format("Initialize '%s %s'",
                    fieldPair.b.clas.name, fieldName));
            var objIns = new IrInstruction(IrInstruction.Genre.GET_ELEMENT_PTR);
            currentBlock.instructions.add(objIns);
            objIns.objectPtr = thisPtr;
            objIns.eleIndexes = new LinkedList<>();
            objIns.eleIndexes.add(createI32Constant(0));
            objIns.eleIndexes.add(createI32Constant(fieldPair.a));
            objIns.insId = new IrId(fieldPair.b.getPointer());
            var objPtr = buildGetFromMem(objIns.insId);
            var callIns = new IrInstruction(IrInstruction.Genre.CALL,
                    clas.constructor.returnType);
            currentBlock.instructions.add(callIns);
            callIns.callName = fieldPair.b.clas.constructor.name;
            callIns.callArguments = new LinkedList<>();
            callIns.callArguments.add(objPtr);
            // Add member object constructor to the beginning of constructor
        }
        buildComment("Constructor content:");

        for (int i = currentBlock.instructions.size() - 1; i >= 0; i--) {
            clas.constructor.blocks.getFirst().instructions
                    .addFirst(currentBlock.instructions.get(i));
        }
    }

    public void buildFunction(NodeFunctionDefine astNode, IrClass clas) {
        if (astNode.builtIn) return; // Just skip built-in functions

        var isMethod = (clas != null);
        currentFunction = isMethod
                ? (astNode.type == null ? clas.constructor : clas.methods.get(astNode.name))
                : irRoot.functions.get(astNode.name);
        var firstBlock = new IrBlock();
        currentFunction.blocks.add(firstBlock);
        currentBlock = firstBlock;

        // Declare return variable
        if (cmdArgs.contains(CmdArgument.ArgumentType.DEBUG)) {
            var str = new StringBuilder();
            str.append(String.format("\nFunction '%s %s(...)', arguments:\n",
                    currentFunction.returnType, currentFunction.name));
            if (astNode.argumentList == null)
                str.append("No argument\n");
            else {
                for (int i = 0, len = astNode.argumentList.identifiers.size();
                     i < len; i++) {
                    str.append(currentFunction.arguments.get(i).type)
                            .append(' ')
                            .append(astNode.argumentList.identifiers.get(i));
                    if (i < len - 1) str.append(", ");
                    else str.append('\n');
                }
            }
            System.out.println(str);
        }
        boolean returnVoid = (currentFunction.returnType.genre == IrType.Genre.VOID);
        if (!returnVoid) {
            buildComment("Function return value");
            currentFunction.retValPtr = buildAlloca(currentFunction.returnType);
        }

        // Set new scope
        var currentScope = new HashMap<String, IrId>();
        if (isMethod) {
            currentScope.put( // Set 'this'
                    "this", currentFunction.arguments.getFirst());
        }
        var arguments = astNode.argumentList;
        if (arguments != null) {
            var len = arguments.types.size();
            for (int i = 0; i < len; i++) {
                var argVal = currentFunction.arguments.get(
                        isMethod ? (i + 1) : i);
                buildComment(String.format("Argument '%s %s'",
                        argVal.type,
                        astNode.argumentList.identifiers.get(i)));
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

        // Call void __INIT() in main()
        if (Objects.equals(currentFunction.name, "main")) {
            buildComment("Global variables initialization");
            var callIns = new IrInstruction(
                    IrInstruction.Genre.CALL,
                    new IrType(IrType.Genre.VOID));
            callIns.callName = internalFuncName.get("_init");
            callIns.callArguments = new LinkedList<>();
            currentBlock.instructions.add(callIns);
        }

        // Build statements of function body
        buildSuite(astNode.suite);

        // Jump to return block
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
            currentFunction = irRoot.functions.get(internalFuncName.get("_init"));
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
        var type = new IrType(astType);
        var initExpr = astTerm.initialExpression;

        IrInstruction ins;
        if (isGlobal) {
            // currentFunction is set to INITIAL_FUNCTION at visitVariableDefine()
            ins = new IrInstruction(
                    IrInstruction.Genre.GLOBAL_VARIABLE,
                    new IrId(type.getPointer(), GLOBAL_VARIABLE_PREFIX + astTerm.name));
            irRoot.variableDefines.add(ins);
            // 将新定义的全局变量 (ins.insId) 放至作用域栈底 (全局变量区域)
            scopeStack.getLast().put(astTerm.name, ins.insId);
        } else {
            ins = new IrInstruction(IrInstruction.Genre.ALLOCA, type);
            currentBlock.instructions.add(ins);
            // 将新定义的局部变量 (ins.insId) 放至作用域栈顶 (当前局部变量区域)
            scopeStack.peek().put(astTerm.name, ins.insId);
        }
        // Initialize variable
        if (initExpr != null) {
            var initVal = buildExpression(initExpr, false);
            buildAssignToMem(initVal, ins.insId);
        }
//        else if (type.genre == IrType.Genre.COMPOSITE) {  // Object
//            var clas = type.clas;
//            var func = clas.constructor;
//            var callIns = new IrInstruction(IrInstruction.Genre.CALL, func.returnType);
//            callIns.callName = func.name;
//            callIns.callArguments = new LinkedList<>();
//            callIns.callArguments.add(ins.insId);   // Add 'this'
//            if (astTerm.constructorArguments != null) {
//                for (var exprNode : astTerm.constructorArguments.expressions) {
//                    callIns.callArguments.add(
//                            buildExpression(exprNode, false));
//                }
//            }
//            currentBlock.instructions.add(callIns);
//        }
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
            case SINGLE_EXPRESSION -> buildExpression(astNode.singleExpr, false);
            case VARIABLE_DEFINE -> visitVariableDefine(astNode.variableDefine, false);
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
                    case THIS -> atomId = currentFunction.arguments.getFirst();
                    case IDENTIFIER -> {
                        var varName = atomNode.identifier;
                        // Local variable
                        for (int i = 0, len = scopeStack.size();
                             (i < len - 1) && (atomId == null); i++) {
                            atomId = scopeStack.get(i).get(varName);
                        }
                        // Class field
                        var clas = currentFunction.clas;
                        if (atomId == null && clas != null) {
                            var entry = clas.fields
                                    .get(atomNode.identifier);
                            if (entry != null) {
                                var ins = new IrInstruction(IrInstruction.Genre.GET_ELEMENT_PTR);
                                // Get 'this'
                                ins.objectPtr = currentFunction.arguments.getFirst();
                                ins.eleIndexes = new LinkedList<>();
                                // Get class content
                                ins.eleIndexes.add(createI32Constant(0));
                                // Get pointer of field by its index
                                ins.eleIndexes.add(createI32Constant(entry.a));
                                // Set instruction type
                                ins.insId = new IrId(entry.b.getPointer());
                                currentBlock.instructions.add(ins);
                                atomId = ins.insId;
                            }
                        }
                        if (atomId == null)
                            atomId = scopeStack.getLast().get(varName);
                        if (atomId == null) throw new InternalError(
                                "IR",
                                String.format("Cannot find definition of identifier '%s'" +
                                        " in buildExpression()", atomNode.identifier));
                        if (!isLeftValue) {
                            // Get variable value when variable at right value expression.
                            atomId = buildGetFromMem(atomId);
                        }
                    }
                    case DECIMAL_INTEGER -> atomId
                            = createI32Constant(atomNode.decimalInteger);
                    case BOOLEAN -> {
                        atomId = new IrId(new IrType(IrType.Genre.I1),
                                (atomNode.booleanValue) ? 1 : 0);   // Trans Boolean to I1
                    }
                    case STRING_CONSTANT -> {
                        var strName = CONSTANT_STRING_PREFIX + constantStringCnt++;
                        // 同全局变量定义
                        var globalIns = new IrInstruction(
                                IrInstruction.Genre.GLOBAL_VARIABLE,
                                new IrId((new IrType(IrType.Genre.I8,
                                        atomNode.stringConstant.length() + 1)
                                        .getPointer()),
                                        strName));
                        globalIns.globalConstantString = atomNode.stringConstant + "\\00";
                        irRoot.variableDefines.add(globalIns);
                        scopeStack.getLast().put(strName, globalIns.insId);

                        var i8PtrType = new IrType(IrType.Genre.I8);
                        i8PtrType.dimension = 1;
                        var getPtrIns = new IrInstruction(IrInstruction.Genre.GET_ELEMENT_PTR);
                        getPtrIns.insId = new IrId(i8PtrType);
                        getPtrIns.objectPtr = globalIns.insId;
                        getPtrIns.eleIndexes = new LinkedList<>(
                                Collections.nCopies(2, createI32Constant(0)));
                        currentBlock.instructions.add(getPtrIns);

                        atomId = getPtrIns.insId; // return i8*
                    }
                    case NULL -> atomId = new IrId(IrId.Genre.NULL);
                }
                return atomId;
            }
            case MEMBER -> {
                IrId objPtr = buildExpression(astNode.objectExpr, false);
                IrInstruction ins = new IrInstruction(IrInstruction.Genre.GET_ELEMENT_PTR);
                ins.objectPtr = objPtr;
                ins.eleIndexes = new LinkedList<>();
                ins.eleIndexes.add(createI32Constant(0));
                currentBlock.instructions.add(ins);

                var fieldPair = objPtr.type.clas.fields
                        .get(astNode.memberName);
                ins.eleIndexes.add(createI32Constant(fieldPair.a));
                ins.insId = new IrId(fieldPair.b.getPointer());

                return isLeftValue ? ins.insId : buildGetFromMem(ins.insId);
            }
            case ARRAY -> {
                var ptr = buildExpression(astNode.arrayNameExpr, true);
                for (var bracket : astNode.brackets) {
                    ptr = buildGetFromMem(ptr);
                    var ins = new IrInstruction(IrInstruction.Genre.GET_ELEMENT_PTR);
                    ins.insId = new IrId(ptr.type);
                    ins.objectPtr = ptr;
                    ins.eleIndexes = new LinkedList<>();
                    var index = buildExpression(bracket.expression, false);
                    ins.eleIndexes.add(index);
                    currentBlock.instructions.add(ins);
                    ptr = ins.insId;
                }
                return isLeftValue ? ptr : buildGetFromMem(ptr);
            }
            case FUNCTION -> {
                var isMethod = (astNode.functionExpr.genre == NodeExpression.Genre.MEMBER);
                IrFunction func = null;
                final var funcName = isMethod
                        ? astNode.functionExpr.memberName
                        : astNode.functionExpr.atom.identifier;
                IrId objPtr = null;

                if (isMethod) {
                    objPtr = buildExpression(astNode.functionExpr.objectExpr, false);
                    if (objPtr.type.isArray()) {
                        if (funcName.equals("size") || funcName.equals("length")) {
                            if (astNode.arguments != null) throwUnexpectedError();
                            else {
                                // * INLINE
                                // * Array/String Size/Length
                                var castIns = new IrInstruction(IrInstruction.Genre.BITCAST,
                                        (new IrType(IrType.Genre.I32)).getPointer());
                                castIns.castPtr = objPtr;
                                currentBlock.instructions.add(castIns);
                                var getPtrIns = new IrInstruction(IrInstruction.Genre.GET_ELEMENT_PTR);
                                getPtrIns.insId = new IrId(castIns.insId.type);
                                getPtrIns.objectPtr = castIns.insId;
                                getPtrIns.eleIndexes = new LinkedList<>();
                                getPtrIns.eleIndexes.add(createI32Constant(-1));
                                currentBlock.instructions.add(getPtrIns);
                                return buildGetFromMem(getPtrIns.insId);
                            }
                        } else  // * String built-in method
                            func = irRoot.classes.get(AstBuilder.builtInStringClassName)
                                    .methods.get(funcName);
                        //irRoot.functions.get(internalFuncName.get(funcName));
                    } else func = objPtr.type.clas.methods.get(funcName);
                } else func = irRoot.functions.get(funcName);

                var ins = new IrInstruction(IrInstruction.Genre.CALL, func.returnType);
                ins.callName = func.name;
                // NodeExpressionList is only used in Function Call
                ins.callArguments = new LinkedList<>();
                if (objPtr != null) ins.callArguments.add(objPtr);  // Add 'this'
                if (astNode.arguments != null) {
                    for (var expr : astNode.arguments.expressions)
                        ins.callArguments.add(buildExpression(expr, false));
                }
                currentBlock.instructions.add(ins);
                return ins.insId;
            }
            case ASSIGN -> {
                var rValue = buildExpression(astNode.rValue, false);
                var lValuePointer = buildExpression(astNode.lValue, true);
                if (rValue.genre == IrId.Genre.NULL)
                    rValue.type = lValuePointer.type.getNotPointer();
                buildAssignToMem(rValue, lValuePointer);
                return lValuePointer;

            }
            case NEW -> {
                // In LLVM IR, 'VOID*' should be 'i8*'
                var i8PtrType = new IrType(IrType.Genre.I8);
                i8PtrType.dimension = 1;
                var i32Type = new IrType(IrType.Genre.I32);
                var newType = new IrType(astNode.type);
                var arrayDimension = newType.dimension;
                // For class, newType is class pointer
                if (newType.genre == IrType.Genre.COMPOSITE
                        || newType.genre == IrType.Genre.I8) arrayDimension--;
                // New array
                if (arrayDimension > 0) {
                    IrId newArrayPtr;
                    if (arrayDimension == 1) {
                        buildComment("One-dimensional array");
                        // Calculate (array_size + 4) first
                        var arraySize = buildExpression(
                                astNode.type.brackets.get(0).expression, false);
                        // ? arraySzie.type must be I32
                        var mulIns = new IrInstruction(
                                IrInstruction.Genre.ARITH, i32Type);
                        mulIns.opGenre = IrInstruction.operatorGenre.MUL;
                        mulIns.arithOperandLeft = createI32Constant(newType.sizeof());
                        mulIns.arithOperandRight = arraySize;
                        currentBlock.instructions.add(mulIns);
                        var addIns = new IrInstruction(
                                IrInstruction.Genre.ARITH, i32Type);
                        addIns.opGenre = IrInstruction.operatorGenre.ADD;
                        addIns.arithOperandLeft = mulIns.insId;
                        addIns.arithOperandRight = createI32Constant(4);
                        currentBlock.instructions.add(addIns);
                        // Allocate memory of (size+4) size
                        var callIns = new IrInstruction(
                                IrInstruction.Genre.CALL, i8PtrType);
                        callIns.callName = internalFuncName.get("_new");
                        callIns.callArguments = new LinkedList<>();
                        callIns.callArguments.add(addIns.insId);
                        currentBlock.instructions.add(callIns);
                        // Cast i8* to i32* type and store size of array
                        var castIns1 = new IrInstruction(
                                IrInstruction.Genre.BITCAST, i32Type.getPointer());
                        castIns1.castPtr = callIns.insId;
                        currentBlock.instructions.add(castIns1);
                        var storeIns = new IrInstruction(
                                IrInstruction.Genre.STORE, i32Type);
                        storeIns.storeAddress = castIns1.insId;
                        storeIns.storeData = arraySize;
                        currentBlock.instructions.add(storeIns);
                        // Get array pointer and cast to correct type
                        var getPtrIns = new IrInstruction(
                                IrInstruction.Genre.GET_ELEMENT_PTR);
                        getPtrIns.insId = new IrId(castIns1.insId.type); // i32*
                        getPtrIns.objectPtr = castIns1.insId;
                        getPtrIns.eleIndexes = new LinkedList<>();
                        getPtrIns.eleIndexes.add(createI32Constant(1));
                        currentBlock.instructions.add(getPtrIns);
                        var castIns2 = new IrInstruction(
                                IrInstruction.Genre.BITCAST, newType);
                        castIns2.castPtr = getPtrIns.insId;
                        currentBlock.instructions.add(castIns2);
                        // Result
                        newArrayPtr = castIns2.insId;
                    } else {
                        buildComment("Multidimensional array");
                        var callIns = new IrInstruction(
                                IrInstruction.Genre.CALL,
                                i8PtrType);
                        callIns.callName = internalFuncName.get("_new_array");
                        callIns.callArguments = new LinkedList<>();
                        callIns.callArguments.add(
                                createI32Constant(newType.sizeof()));
                        callIns.callArguments.add(
                                createI32Constant(arrayDimension));
                        // 生成存储每一维数组大小的数组
                        var allocaIns = new IrInstruction(
                                IrInstruction.Genre.ALLOCA, i32Type);
                        allocaIns.allocaQuantity = createI32Constant(arrayDimension);
                        currentBlock.instructions.add(allocaIns);
                        var arrayPtr = allocaIns.insId;
                        var storePtr = arrayPtr;
                        int i;
                        for (i = 0; i < arrayDimension; i++) {
                            if (i != 0) {
                                var offsetIns = new IrInstruction(
                                        IrInstruction.Genre.GET_ELEMENT_PTR);
                                offsetIns.insId = new IrId(arrayPtr.type);
                                offsetIns.objectPtr = arrayPtr;
                                offsetIns.eleIndexes = new LinkedList<>();
                                offsetIns.eleIndexes.add(createI32Constant(i));
                                currentBlock.instructions.add(offsetIns);
                                storePtr = offsetIns.insId;
                            }
                            var storeIns = new IrInstruction(
                                    IrInstruction.Genre.STORE,
                                    i32Type);
                            storeIns.storeAddress = storePtr;
                            currentBlock.instructions.add(storeIns);

                            // ! bug
                            // todo
                            if (i >= astNode.type.brackets.size())
                                throw new SemanticError("brackets", astNode.position);
                            var indexExprNode = astNode.type
                                    .brackets.get(i).expression;
                            if (indexExprNode == null) {
                                storeIns.storeData = createI32Constant(0);
                                break;
                            } else storeIns.storeData = buildExpression(
                                    indexExprNode, false);
                        }
                        callIns.callArguments.add(arrayPtr);
                        currentBlock.instructions.add(callIns);

                        var castIns = new IrInstruction(
                                IrInstruction.Genre.BITCAST,
                                newType);
                        castIns.castPtr = callIns.insId;
                        currentBlock.instructions.add(castIns);
                        newArrayPtr = castIns.insId;
                    }
                    return newArrayPtr;
                }
                // New class
                else if (newType.genre == IrType.Genre.COMPOSITE) {
                    var callIns = new IrInstruction(
                            IrInstruction.Genre.CALL,
                            i8PtrType);
                    callIns.callName = internalFuncName.get("_new");
                    callIns.callArguments = new LinkedList<>();
                    callIns.callArguments.add(
                            createI32Constant(
                                    newType.getNotPointer().sizeof()));
                    currentBlock.instructions.add(callIns);

                    var castIns = new IrInstruction(
                            IrInstruction.Genre.BITCAST,
                            newType);
                    castIns.castPtr = callIns.insId;
                    currentBlock.instructions.add(castIns);

                    return castIns.insId;
                }
                throwUnexpectedError();
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
                    case AND, OR -> { // phi 指令实现短路求值
                        var isAND = (astNode.operator == NodeExpression.OpEnum.AND);
                        var originalJumpIns = currentBlock.jumpInstruction;
                        var nextBlock = new IrBlock();
                        var nextExprBlock = new IrBlock();

                        final var boolType = new IrType(IrType.Genre.I1);
                        var phiIns = new IrInstruction(
                                IrInstruction.Genre.PHI,
                                boolType);
                        phiIns.phiArgs = new LinkedList<>();
                        // Collect all expression
                        // 为方便优化, 需要将多个连续 &&/|| 以一个 phi 指令完成
                        // 连续的 &&/|| 结点在 AST 中是左偏的二叉树
                        var exprs = new LinkedList<NodeExpression>();
                        var exprNode = astNode;
                        while (exprNode.genre == NodeExpression.Genre.BINARY &&
                                exprNode.operator == (isAND
                                        ? NodeExpression.OpEnum.AND : NodeExpression.OpEnum.OR)) {
                            exprs.addFirst(exprNode.rTermExpr);
                            exprNode = exprNode.lTermExpr;
                        }
                        exprs.addFirst(exprNode);

                        for (int i = 0, len = exprs.size(); i < len; i++) {
                            if (i == 0) {   // First expression
                                phiIns.phiArgs.add(new Pair<>(
                                        new IrId(boolType, (isAND ? 0 : 1)),
                                        currentBlock.label));
                                buildBranch(
                                        buildExpression(exprs.get(i), false),
                                        isAND ? nextExprBlock : nextBlock,
                                        isAND ? nextBlock : nextExprBlock);
                            } else if (i < len - 1) {
                                currentFunction.blocks.add(nextExprBlock);
                                currentBlock = nextExprBlock;
                                nextExprBlock = new IrBlock();
                                phiIns.phiArgs.add(new Pair<>(
                                        new IrId(boolType, (isAND ? 0 : 1)),
                                        currentBlock.label));
                                buildBranch(
                                        buildExpression(exprs.get(i), false),
                                        isAND ? nextExprBlock : nextBlock,
                                        isAND ? nextBlock : nextExprBlock);
                            } else {    // Last expression
                                currentFunction.blocks.add(nextExprBlock);
                                currentBlock = nextExprBlock;
                                phiIns.phiArgs.add(new Pair<>(
                                        buildExpression(exprs.get(i), false),
                                        currentBlock.label));
                                buildJumpToBlock(nextBlock, false);
                            }
                        }
                        currentFunction.blocks.add(nextBlock);
                        currentBlock = nextBlock;
                        currentBlock.jumpInstruction = originalJumpIns;

                        currentBlock.instructions.add(phiIns);
                        return phiIns.insId;
                    }
                    default -> {
                        var lOperand = buildExpression(astNode.lTermExpr, false);
                        var rOperand = buildExpression(astNode.rTermExpr, false);
                        if (astNode.operator == NodeExpression.OpEnum.EQ
                                || astNode.operator == NodeExpression.OpEnum.NOT_EQ) {
                            var isEq = (astNode.operator == NodeExpression.OpEnum.EQ);
                            if (lOperand.genre == IrId.Genre.NULL || rOperand.genre == IrId.Genre.NULL) {
                                if (lOperand.genre == rOperand.genre) {
                                    // * 这里对于 null == null 的判断直接优化掉
                                    return (new IrId(new IrType(IrType.Genre.I1),
                                            (isEq ? 1 : 0)));
                                } else if (lOperand.genre == IrId.Genre.NULL) {
                                    // null == obj
                                    lOperand.type = rOperand.type;
                                } else {
                                    // 实际上该分支可以删除, 因为 IrInstruction 输出
                                    // 比较类型时使用左操作数的类型
                                    rOperand.type = lOperand.type;
                                }
                            }
                        }
                        if (!lOperand.type.equals(rOperand.type)) throw new InternalError(
                                "IR",
                                "Left and Right Operand of NodeExpression(BINARY) " +
                                        "have Different Type in buildExpression()");
                        if (!lOperand.type.isString()) {
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
                        } else {
                            // * string built-in function
                            IrType callType = (astNode.operator == NodeExpression.OpEnum.ADD)
                                    ? (new IrType(IrType.Genre.I8).getPointer())
                                    : (new IrType(IrType.Genre.I8));
                            var callIns = new IrInstruction(IrInstruction.Genre.CALL, callType);
                            switch (astNode.operator) {
                                case ADD -> callIns.callName = internalFuncName.get("_string_add");
                                case EQ -> callIns.callName = internalFuncName.get("_string_equal");
                                case NOT_EQ -> callIns.callName = internalFuncName.get("_string_not_equal");
                                case LT -> callIns.callName = internalFuncName.get("_string_less");
                                case GT -> callIns.callName = internalFuncName.get("_string_greater");
                                case LE -> callIns.callName = internalFuncName.get("_string_less_or_equal");
                                case GE -> callIns.callName = internalFuncName.get("_string_greater_or_equal");
                            }
                            callIns.callArguments = new LinkedList<>();
                            callIns.callArguments.add(lOperand);
                            callIns.callArguments.add(rOperand);
                            currentBlock.instructions.add(callIns);
                            if (astNode.operator == NodeExpression.OpEnum.ADD)
                                return callIns.insId;   // i8*
                            var cmpIns = new IrInstruction(
                                    IrInstruction.Genre.ARITH,
                                    new IrType(IrType.Genre.I1));
                            cmpIns.opGenre = IrInstruction.operatorGenre.NEQ;
                            cmpIns.arithOperandLeft = callIns.insId;
                            cmpIns.arithOperandRight = createI8Constant(0);
                            currentBlock.instructions.add(cmpIns);
                            return cmpIns.insId;
                        }
                    }
                }
            }
            // No Lambda
        }
        throw new InternalError("IR", "Missing return value of buildExpression(), genre: " + astNode.genre);
    }
    // endregion
}
