package frontend.ast;

import java.util.Objects;
import java.util.LinkedList;
import java.util.ArrayList;
import utility.error.SemanticError;
import frontend.ast.node.*;
import frontend.ast.scope.*;


public class SemanticChecker {
    // ? 应该优先使用 private 变量和方法

    public final String builtInStringClassName = "__STRING__";
    public final String builtInArrayClassName = "__ARRAY__";

    public enum ScopeGenre {CLASS, FUNCTION, LOOP}

    public String currentClassName = null;
    // Class has "this", function has "return"
    // and loop has "continue" and "break".

    public LinkedList<VariableScope> scopeStack;
    public final BroadScope globalScope;
    public final LinkedList<ScopeGenre> scopeGenreStack;

    public SemanticChecker(BroadScope globalScope_) {
        globalScope = globalScope_;
        scopeStack = new LinkedList<>();
        scopeStack.push(globalScope_);
        scopeGenreStack = new LinkedList<>();

        // region Built-in function
        // void print(string str);
        globalScope.defineFunction(createFunction(
                new AstType(AstType.Genre.VOID, false),
                "print",
                new AstType(AstType.Genre.STRING, false),
                "str"
        ));
        // void println(string str);
        globalScope.defineFunction(createFunction(
                new AstType(AstType.Genre.VOID, false),
                "println",
                new AstType(AstType.Genre.STRING, false),
                "str"
        ));
        // void printInt(int n);
        globalScope.defineFunction(createFunction(
                new AstType(AstType.Genre.VOID, false),
                "printInt",
                new AstType(AstType.Genre.INTEGER, false),
                "n"
        ));
        // void printlnInt(int n);
        globalScope.defineFunction(createFunction(
                new AstType(AstType.Genre.VOID, false),
                "printlnInt",
                new AstType(AstType.Genre.INTEGER, false),
                "n"
        ));
        // string getString();
        globalScope.defineFunction(createFunction(
                new AstType(AstType.Genre.STRING, false),
                "getString",
                null,
                null
        ));
        // int getInt();
        globalScope.defineFunction(createFunction(
                new AstType(AstType.Genre.INTEGER, false),
                "getInt",
                null,
                null
        ));
        // string toString(int i);
        globalScope.defineFunction(createFunction(
                new AstType(AstType.Genre.STRING, false),
                "toString",
                new AstType(AstType.Genre.INTEGER, false),
                "i"
        ));
        // endregion

        // region Built-in method
        var nullPos = new AstPosition(-1, -1);

        globalScope.defineClass(builtInStringClassName, nullPos);
        var stringClass = globalScope.getClass(builtInStringClassName, nullPos);
        // int length();
        stringClass.defineMethod(createFunction(
                new AstType(AstType.Genre.INTEGER, false),
                "length",
                null,
                null
        ));
        // string substring(int left, int right);
        stringClass.defineMethod(createFunction(
                new AstType(AstType.Genre.STRING, false),
                "substring",
                new AstType(AstType.Genre.INTEGER, false), new AstType(AstType.Genre.INTEGER, false),
                "left", "right"
        ));
        // int parseInt();
        stringClass.defineMethod(createFunction(
                new AstType(AstType.Genre.INTEGER, false),
                "parseInt",
                null,
                null
        ));
        // int ord(int pos);
        stringClass.defineMethod(createFunction(
                new AstType(AstType.Genre.INTEGER, false),
                "ord",
                new AstType(AstType.Genre.INTEGER, false),
                "pos"
        ));

        globalScope.defineClass(builtInArrayClassName, nullPos);
        var arrayClass = globalScope.getClass(builtInArrayClassName, nullPos);
        // int size();
        arrayClass.defineMethod(createFunction(
                new AstType(AstType.Genre.INTEGER, false),
                "size",
                null,
                null
        ));
        // endregion
    }

    // region Tools
    // Built-in function with single or none argument
    public NodeFunctionDefine createFunction(
            AstType returnType,
            String name,
            AstType argumentType,      // Built-in functions have no more
            String argumentName     // than one argument.
    ) {
        var pos = new AstPosition(-1, -1);  // null position
        var node = new NodeFunctionDefine(pos);
        node.type = new NodeType(pos);
        node.type.type = returnType;
        node.name = name;
        node.argumentList = new NodeArgumentList(pos);
        if (argumentType != null) {
            var typeNode = new NodeType(pos);
            typeNode.type = argumentType;
            node.argumentList.types.add(typeNode);
            node.argumentList.identifiers.add(argumentName);
        }
        node.suite = new NodeSuite(pos);
        return node;
    }

    // Built-in function with two argument
    public NodeFunctionDefine createFunction(
            AstType returnType,
            String name,
            AstType argumentType1, AstType argumentType2,
            String argumentName1, String argumentName2
    ) {
        var pos = new AstPosition(-1, -1);  // null position
        var node = new NodeFunctionDefine(pos);
        node.type = new NodeType(pos);
        node.type.type = returnType;
        node.name = name;
        node.argumentList = new NodeArgumentList(pos);
        var typeNode1 = new NodeType(pos);
        var typeNode2 = new NodeType(pos);
        typeNode1.type = argumentType1;
        typeNode2.type = argumentType2;
        node.argumentList.types.add(typeNode1);
        node.argumentList.types.add(typeNode2);
        node.argumentList.identifiers.add(argumentName1);
        node.argumentList.identifiers.add(argumentName2);
        node.suite = new NodeSuite(pos);
        return node;
    }

    // Jump check
    public boolean in_loop() {
        for (var genre : scopeGenreStack)
            if (genre == ScopeGenre.LOOP) return true;
        return false;
    }

    public boolean in_function() {
        for (var genre : scopeGenreStack)
            if (genre == ScopeGenre.FUNCTION) return true;
        return false;
    }

    public boolean in_class() {
        for (var genre : scopeGenreStack)
            if (genre == ScopeGenre.CLASS) return true;
        return false;
    }
    // endregion

    // region Check_Node

    public void checkRoot(NodeRoot node) {
        for (var section : node.programSections) checkProgramSection(section);
        // Ensure that main function exists
        if (!globalScope.functions.containsKey("main"))
            throw new SemanticError("Missing 'main' function", node.position);
    }

    public void checkProgramSection(NodeProgramSection node) {
        switch (node.genre) {
            case CLASS_DEFINE -> checkClassDefine(node.classDefineNode);
            case FUNCTION_DEFINE -> checkFunctionDefine(node.functionDefine);
            case VARIABLE_DEFINE -> checkVariableDefine(node.globalVariableDefine);
        }
    }

    public void checkClassDefine(NodeClassDefine node) {
        // Class member and function definition has been
        // collected because of forward reference,
        // so just need to visit function body.
        // And the rule that class member variable cannot
        // be initialized when defining is also managed
        // in class ForwardCollector.
        currentClassName = node.name;
        var classScope = globalScope.getClass(currentClassName, node.position);
        var currentScope = new VariableScope(globalScope);
        currentScope.variables.putAll(classScope.variables);

        scopeGenreStack.push(ScopeGenre.CLASS);
        scopeStack.push(currentScope);
        for (var functionDefine : node.methodDefines)
            checkFunctionDefine(functionDefine);
        scopeStack.pop();
        scopeGenreStack.pop();
        currentClassName = null;
    }

    public void checkFunctionDefine(NodeFunctionDefine node) {
        // Same as comment of NodeClassDefine
        var currentScope = new VariableScope(globalScope);
        if (in_class()) {
            currentScope.variables.putAll(
                    globalScope.getClass(currentClassName, node.position)
                            .getMethod(node.name, node.position, true).variables);
        } else currentScope.variables.putAll(
                globalScope.getFunction(node.name, node.position, true).variables);
        // 若使用 clone() 需要在 globalScope 及其基类中实现深克隆
        scopeGenreStack.push(ScopeGenre.FUNCTION);
        scopeStack.push(currentScope);
        var returnType = checkSuite(node.suite);
        scopeStack.pop();
        scopeGenreStack.pop();

        if (node.name.equals("main")) {
            boolean correct;
            if ((node.type == null)
                    || (node.type.type.genre != AstType.Genre.INTEGER)
                    || (node.argumentList != null))
                correct = false;
            else {
                correct = (returnType == null)
                        || (returnType.genre == AstType.Genre.INTEGER);
            }
            if (correct) return;
            else throw new SemanticError(
                    "Get wrong definition of 'main' function",
                    node.position
            );
        }
        if (returnType == null) {// constructor
            if (node.type == null)
                if (in_class() && Objects.equals(currentClassName, node.name)) return;
                else throw new SemanticError(
                        "Wrong type of constructor",
                        node.position
                );
            if (node.type.type.genre == AstType.Genre.VOID) return;
            else throw new SemanticError(
                    "Missing return value of function '"
                            + node.name + "'",
                    node.position
            );
        }
        if (node.type == null) {
            throw new SemanticError(
                    "Get return value in class constructor",
                    node.position
            );
        } else if (!node.type.type.equals(returnType))
            throw new SemanticError(
                    "Get wrong type of function return value",
                    node.position
            );
    }

    // Only for lambda
    public ArrayList<AstType> checkArgumentList(NodeArgumentList node) {
        var currentScope = new VariableScope(globalScope);
        var returnTypes = new ArrayList<AstType>();
        if (node != null) {
            final var len = node.types.size();
            for (int i = 0; i < len; i++) {
                currentScope.defineVariable(
                        node.identifiers.get(i),
                        checkType(node.types.get(i), true, false),
                        node.position
                );
                returnTypes.add(node.types.get(i).type);
            }
        }
        scopeStack.push(currentScope);
        return returnTypes;
    }

    public void checkVariableDefine(NodeVariableDefine node) {
        final var type = checkType(node.type, false, false);
        final var position = node.position;
        var termList = node.variableTerms;
        for (var term : termList) {
            var name = checkVariableTerm(term, type);
            assert scopeStack.peek() != null;
            scopeStack.peek().defineVariable(name, type, position);
        }
    }

    public String checkVariableTerm(NodeVariableTerm node, AstType type) {
        if (node.initialExpression != null) {
            var exprType = checkExpression(node.initialExpression);
            if (!((((type.genre == AstType.Genre.CLASS_NAME) || (type.dimension != 0))
                    && exprType.genre == AstType.Genre.NULL)
                    || type.equals(exprType)))
                throw new SemanticError("Type of initial expression cannot match variable '"
                        + node.name + "'", node.position);
        }
        return node.name;
    }

    //  * The argument "noDimension" is used for checking array type.
    // Type with no specified dimension exists at "argumentList", "variableDefine" and
    // ("functionDefine"). Type with prefix specified dimension exists at "newExpr".
    // * The argument "allowVoid" is used for checking type of function's return value,
    // which is the only case that type can be "void" and all other cases will consider
    // "void" type illegal.
    // * "null" is always an illegal definition type.
    // "atomExpr" handles "null" without calling "checkType()".
    public AstType checkType(NodeType node, boolean noDimension, boolean allowVoid) {
        if (node.type.dimension != 0) {
            if (noDimension) {  // "argumentList", "variableDefine" and ("functionDefine")
                for (var bracket : node.brackets) {
                    if (bracket != null)
                        throw new SemanticError(
                                "Get unexpected fixed-length dimension in array definition"
                                , node.position
                        );
                }
            } else {
                // "newExpr" examples:
                // Legal:   a[1][2][3][4],  a[1][2][ ][ ]
                // Illegal: a[1][ ][3][ ],  a[ ][ ][3][4],  a[ ][ ][ ][ ]
                if (node.brackets.get(0) == null)
                    throw new SemanticError(
                            "Get unfixed-length array in array definition"
                            , node.position
                    );
                boolean emptyBracket = false;
                for (var bracket : node.brackets) {
                    if (!checkBracket(bracket)) emptyBracket = true;
                    else if (emptyBracket) throw new SemanticError(
                            "Get unexpected fixed-length dimension after " +
                                    "unfixed-length dimension when creating an array"
                            , node.position
                    );
                }
            }
        }
        if (node.type.genre == AstType.Genre.VOID && !allowVoid)
            throw new SemanticError(
                    "Cannot use void type here"
                    , node.position
            );
        globalScope.checkTypeExist(node.type, node.position);
        return node.type;
    }

    // Returns whether the bracket contains argument
    public boolean checkBracket(NodeBracket node) {
        if (node.expression != null) {
            var type = checkExpression(node.expression);
            if (type.genre != AstType.Genre.INTEGER || type.dimension != 0)
                throw new SemanticError(
                        "Get array element with index of non-integer type",
                        node.position
                );
            else return true;
        }
        return false;
    }

    public AstType checkSuite(NodeSuite node) {
        scopeStack.push(new VariableScope(globalScope));
        var statementList = node.statements;
        AstType returnType = null;
        for (var statement : statementList) {
            var statementType = checkStatement(statement);
            if (statementType != null) {
                if (returnType == null || returnType.genre == AstType.Genre.NULL)
                    returnType = statementType;
                else if (statementType.genre != returnType.genre)
                    throw new SemanticError(
                            "Wrong type of return value",
                            statement.position
                    );
            }
        }
        scopeStack.pop();
        return returnType;
    }

    // Returns 'Type' for return expression or 'null' for the other situations.
    public AstType checkStatement(NodeStatement node) {
        switch (node.genre) {
            case SUITE -> {
                return checkSuite(node.suite);
            }
            case IF -> {
                if (checkExpression(node.ifCondExpr).genre != AstType.Genre.BOOLEAN)
                    throw new SemanticError(
                            "Type of condition expression of 'if' statement is not Boolean",
                            node.position
                    );
                scopeStack.push(new VariableScope(globalScope));
                var returnType1 = checkStatement(node.trueBranchStmt);
                scopeStack.pop();
                if (node.falseBranchStmt != null) {
                    scopeStack.push(new VariableScope(globalScope));
                    var returnType2 = checkStatement(node.falseBranchStmt);
                    scopeStack.pop();
                    if (!(returnType1 == null && returnType2 == null))
                        if ((returnType1 == null || returnType2 == null)
                                || !returnType1.equals(returnType2))
                            throw new SemanticError(
                                    "Wrong type of branch return value",
                                    node.position
                            );
                }
                return returnType1;
            }
            case FOR -> {
                scopeStack.push(new VariableScope(globalScope));

                if (node.initialWithVarDef != null) {
                    if (node.initialWithVarDef) checkVariableDefine(node.initialVarDef);
                    else checkExpression(node.initialExpr);
                }
                if (node.forCondExpr != null) {
                    var exprType = checkExpression(node.forCondExpr);
                    if (exprType.genre != AstType.Genre.BOOLEAN)
                        throw new SemanticError(
                                "Type of condition expression of 'for' statement is not Boolean",
                                node.position
                        );
                }
                if (node.stepExpr != null) checkExpression(node.stepExpr);

                scopeGenreStack.push(ScopeGenre.LOOP);
                var returnType = checkStatement(node.forBodyStmt);
                scopeGenreStack.pop();
                scopeStack.pop();
                return returnType;
            }
            case WHILE -> {
                if (checkExpression(node.whileCondExpr).genre != AstType.Genre.BOOLEAN)
                    throw new SemanticError(
                            "Type of condition expression of 'while' statement is not Boolean",
                            node.position
                    );
                scopeGenreStack.push(ScopeGenre.LOOP);
                scopeStack.push(new VariableScope(globalScope));
                var returnType = checkStatement(node.whileBodyStmt);
                scopeStack.pop();
                scopeGenreStack.pop();
                return returnType;
            }
            case CONTINUE -> {
                if (!in_loop())
                    throw new SemanticError(
                            "Try using 'continue' in non-loop suite",
                            node.position
                    );
            }
            case BREAK -> {
                if (!in_loop())
                    throw new SemanticError(
                            "Try using 'break' in non-loop suite",
                            node.position
                    );
            }
            case RETURN -> {
                if (!in_function())
                    throw new SemanticError(
                            "Try using 'return' in non-loop suite",
                            node.position
                    );
                if (node.returnExpr == null) return null;
                else return checkExpression(node.returnExpr);
            }
            case SINGLE_EXPRESSION -> checkExpression(node.singleExpr);
            case VARIABLE_DEFINE -> checkVariableDefine(node.variableDefine);
            case EMPTY -> {
                // just touch fish
            }
        }
        return null;
    }

    public AstType checkExpression(NodeExpression node) {
        // NodeExpression 子节点中仅有 newExpr 和 lambdaExpr 可能产生 void 类型返回值
        // expression 必为实际值
        switch (node.genre) {
            // * Term
            case PAREN -> {
                return checkExpression(node.parenExpr);
            }
            case ATOM -> {
                return checkAtom(node.atom);
            }
            case MEMBER -> {
                var objectType = checkExpression(node.objectExpr);
                if (objectType.dimension != 0) {
                    throw new SemanticError(
                            "Try getting member of array type",
                            node.position
                    );
                } else if (objectType.genre == AstType.Genre.STRING
                        || objectType.genre == AstType.Genre.CLASS_NAME) {
                    // Calling method is managed in case FUNCTION.
                    var classType = globalScope.getClass(
                            objectType.className,
                            node.position
                    );
                    return classType.getVariableType(node.memberName, node.position);
                } else throw new SemanticError(
                        "Get member of type '" + objectType.genre.toString() + "'",
                        node.position
                );
            }
            case ARRAY -> {
                var arrayType = checkExpression(node.arrayNameExpr);
                if (arrayType.dimension < node.brackets.size())
                    throw new SemanticError(
                            "Get too many indexes of array",
                            node.position
                    );
                for (var bracket : node.brackets) {
                    if (!checkBracket(bracket))
                        throw new SemanticError(
                                "Get non-integer type or missing index of array",
                                node.position
                        );
                }
                var returnType = new AstType(arrayType.genre, true);
                if (returnType.genre == AstType.Genre.CLASS_NAME) returnType.className = arrayType.className;
                returnType.dimension = arrayType.dimension - node.brackets.size();
                return returnType;

            }
            case FUNCTION -> {
                // * 在此处解决成员方法调用可以避免很多麻烦
                // Only string has built-in method
                // * Call string type constant's built-in method is undefined behavior

                FunctionScope functionScope = null;
                if (node.functionExpr.genre == NodeExpression.Genre.MEMBER) {
                    // Method
                    String functionName = node.functionExpr.memberName;
                    var objectType = checkExpression(node.functionExpr.objectExpr);
                    if (objectType.dimension != 0) {
                        // 此处直接修改 objectType.className 值会导致修改 scope 中对应值 Q^Q
                        objectType = new AstType(builtInArrayClassName);
                    } else if (objectType.genre == AstType.Genre.STRING) objectType.className = builtInStringClassName;
                    var classScope = globalScope.getClass(objectType.className, node.position);
                    functionScope = classScope.getMethod(functionName, node.position, true);
                } else if (node.functionExpr.genre == NodeExpression.Genre.ATOM) {
                    // Function
                    String functionName = node.functionExpr.atom.identifier;
                    if (in_class()) functionScope =
                            globalScope.getClass(currentClassName, node.position)
                                    .getMethod(functionName, node.position, false);
                    if (functionScope == null)
                        functionScope = globalScope.getFunction(functionName, node.position, false);
                } else throw new SemanticError("Wrong function call", node.position);
                var arguments = (node.arguments == null)
                        ? (new ArrayList<AstType>()) : (checkExpressionList(node.arguments));
                var argumentTypes = functionScope.argumentsType;

                var len = argumentTypes.size();
                if (arguments.size() != len)
                    throw new SemanticError(
                            "Function call gets wrong number of arguments",
                            node.position
                    );
                for (int i = 0; i < len; i++) {
                    if (!argumentTypes.get(i).equals(arguments.get(i))
                            && !((argumentTypes.get(i).genre == AstType.Genre.CLASS_NAME
                            || argumentTypes.get(i).dimension != 0)
                            && arguments.get(i).genre == AstType.Genre.NULL))
                        throw new SemanticError(
                                "Function call gets a wrong type argument",
                                node.arguments.expressions.get(i).position
                        );
                }
                if (functionScope.returnType.isVariable) {
                    // ! just shit
                    var retType = new AstType(AstType.Genre.NULL, false);
                    retType.genre = functionScope.returnType.genre;
                    retType.dimension = functionScope.returnType.dimension;
                    retType.className = functionScope.returnType.className;
                    return retType;
                }
                return functionScope.returnType;
            }
            // * Command
            case ASSIGN -> {
                // 根据题面 `15.左值`, 无规定的左值情况一律为 undefined behavior.
                var lValueType = checkExpression(node.lValue);
                if (!lValueType.isVariable) throw new SemanticError(
                        "Illegal left value",
                        node.position
                );
                var rValueType = checkExpression(node.rValue);
                if (lValueType.genre != rValueType.genre
                        || (lValueType.genre == AstType.Genre.CLASS_NAME
                        && !lValueType.className.equals(rValueType.className))
                        || lValueType.dimension != rValueType.dimension) {
                    if (!((lValueType.genre == AstType.Genre.CLASS_NAME || lValueType.dimension != 0)
                            && rValueType.genre == AstType.Genre.NULL))
                        throw new SemanticError(
                                "Right value has different type from left value " +
                                        "in assignment expression",
                                node.position
                        );
                }
                return lValueType;
            }
            case NEW -> {
                return checkType(
                        node.type,
                        false,
                        false
                );
            }
            // * Arithmetic
            case SELF -> {
                var termType = checkExpression(node.termExpr);
                if (!termType.isVariable) throw new SemanticError(
                        "Illegal term to use postfix '++' or '--' operator",
                        node.position
                );
                if (termType.genre != AstType.Genre.INTEGER
                        || termType.dimension != 0)
                    throw new SemanticError(
                            "Use postfix operator '++' or '--' with "
                                    + "non-integer type object",
                            node.position
                    );
                // ! shit
                var retType = new AstType(AstType.Genre.NULL, false);
                retType.genre = termType.genre;
                retType.className = termType.className;
                retType.dimension = termType.dimension;
                return retType;
            }
            case UNARY -> {
                var termType = checkExpression(node.termExpr);
                if (node.operator == NodeExpression.OpEnum.BANG) {
                    if (termType.genre != AstType.Genre.BOOLEAN
                            || termType.dimension != 0)
                        throw new SemanticError(
                                "Use prefix operator '!' " +
                                        "with non-boolean type object",
                                node.position
                        );
                } else {
                    if (termType.genre != AstType.Genre.INTEGER
                            || termType.dimension != 0)
                        throw new SemanticError(
                                "Use prefix operator '" + node.operator.toString()
                                        + "' with non-integer type object",
                                node.position
                        );
                }
                // ! shit!
                if (node.operator == NodeExpression.OpEnum.INC || node.operator == NodeExpression.OpEnum.DEC) {
                    if (!termType.isVariable) {
                        var retType = new AstType(AstType.Genre.NULL, true);
                        retType.genre = termType.genre;
                        retType.className = termType.className;
                        retType.dimension = termType.dimension;
                        return retType;
                    }
                } else if (termType.isVariable) {
                    var retType = new AstType(AstType.Genre.NULL, false);
                    retType.genre = termType.genre;
                    retType.className = termType.className;
                    retType.dimension = termType.dimension;
                    return retType;
                }
                return termType;
            }
            case BINARY -> {
                var lTermType = checkExpression(node.lTermExpr);
                var rTermType = checkExpression(node.rTermExpr);
                switch (node.operator) {
                    // For a "string" object, some operators act on its text content.
                    case EQ, NOT_EQ -> {
                        // 由题面 `10.2` 可知, 除 int 和 bool 外, 不同 class 可以比较地址
                        if (lTermType.dimension != 0 && rTermType.genre == AstType.Genre.NULL)
                            return new AstType(AstType.Genre.BOOLEAN, false);
                        if ((lTermType.genre == AstType.Genre.INTEGER
                                || rTermType.genre == AstType.Genre.INTEGER)
                                && lTermType.genre != rTermType.genre) {
                            throw new SemanticError(
                                    "Try judging equivalence between integer type " +
                                            "and non-integer type object",
                                    node.position
                            );
                        } else if ((lTermType.genre == AstType.Genre.BOOLEAN
                                || rTermType.genre == AstType.Genre.BOOLEAN)
                                && lTermType.genre != rTermType.genre) {
                            throw new SemanticError(
                                    "Try judging equivalence between boolean type " +
                                            "and non-boolean type object",
                                    node.position
                            );
                        } else return new AstType(AstType.Genre.BOOLEAN, false);
                    }
                    case AND, OR -> {
                        if (lTermType.genre != AstType.Genre.BOOLEAN
                                || rTermType.genre != AstType.Genre.BOOLEAN) {
                            throw new SemanticError(
                                    "Try using binary operator '&&' or '||' " +
                                            "with non-boolean type object",
                                    node.position
                            );
                        }
                        return new AstType(AstType.Genre.BOOLEAN, false);
                    }
                    case ADD -> {
                        if (lTermType.genre == AstType.Genre.INTEGER
                                && rTermType.genre == AstType.Genre.INTEGER)
                            return new AstType(AstType.Genre.INTEGER, false);
                        else if (lTermType.genre == AstType.Genre.STRING
                                && rTermType.genre == AstType.Genre.STRING)
                            return new AstType(AstType.Genre.STRING, false);
                        else throw new SemanticError(
                                    "Try adding two objects of different types," +
                                            "or the same type but not integer or string",
                                    node.position
                            );
                    }
                    case GT, LT, GE, LE -> {
                        if (lTermType.genre == AstType.Genre.INTEGER
                                && rTermType.genre == AstType.Genre.INTEGER)
                            return new AstType(AstType.Genre.BOOLEAN, false);
                        else if (lTermType.genre == AstType.Genre.STRING
                                && rTermType.genre == AstType.Genre.STRING)
                            return new AstType(AstType.Genre.BOOLEAN, false);
                        else throw new SemanticError(
                                    "Try comparing two objects of different types," +
                                            "or the same type but not integer or string",
                                    node.position
                            );
                    }
                    default -> {
                        if (lTermType.genre != AstType.Genre.INTEGER
                                || rTermType.genre != AstType.Genre.INTEGER)
                            throw new SemanticError(
                                    "Try numerically calculating two objects " +
                                            "of non-integer types",
                                    node.position
                            );
                        else return new AstType(AstType.Genre.INTEGER, false);
                    }
                }
            }
            // * Lambda
            case LAMBDA -> {
                var argumentTypes = checkArgumentList(node.lambdaArgumentList);
                // checkArgumentList(): scopeStack.push()
                var expressionTypes = checkExpressionList(node.lambdaExpressionList);
                final var len = argumentTypes.size();
                if (len != expressionTypes.size())
                    throw new SemanticError(
                            "Lambda expression gets wrong number of arguments",
                            node.position
                    );
                for (int i = 0; i < len; i++) {
                    if (!argumentTypes.get(i).equals(expressionTypes.get(i)))
                        throw new SemanticError(
                                "Lambda expression gets a wrong type argument",
                                node.lambdaExpressionList.expressions.get(i).position
                        );
                }
                scopeGenreStack.push(ScopeGenre.FUNCTION);
                var returnType = checkSuite(node.lambdaSuite);
                scopeGenreStack.pop();
                scopeStack.pop();
                return returnType;
            }
        }
        throw new SemanticError(
                "Unexpected error in checkExpression()",
                node.position
        );
    }

    public ArrayList<AstType> checkExpressionList(NodeExpressionList node) {
        var returnTypes = new ArrayList<AstType>();
        if (node != null) {
            for (var expression : node.expressions)
                returnTypes.add(checkExpression(expression));
        }
        return returnTypes;
    }

    public AstType checkAtom(NodeAtom node) {
        // "null" 不能参与运算(除 "==" & "!="), 能作为右值进行赋值(除字符串)
        switch (node.genre) {
            case THIS -> {
                if (in_class()) {
                    return new AstType(currentClassName);
                } else throw new SemanticError(
                        "Use 'this' outside of class definition",
                        node.position
                );
            }
            case IDENTIFIER -> {
                assert scopeStack.peek() != null;
                for (var scope : scopeStack) {
                    AstType variableType = scope.getVariableType(node.identifier, node.position);
                    if (variableType != null) {
                        var returnType = new AstType(variableType.genre, true);
                        returnType.dimension = variableType.dimension;
                        returnType.className = variableType.className;
                        return returnType;
                    }
                }
                throw new SemanticError(
                        "Variable/object '" + node.identifier + "' doesn't exist",
                        node.position);
            }
            case STRING_CONSTANT -> {
                return new AstType(AstType.Genre.STRING, false);
            }
            case DECIMAL_INTEGER -> {
                return new AstType(AstType.Genre.INTEGER, false);
            }
            case BOOLEAN -> {
                return new AstType(AstType.Genre.BOOLEAN, false);
            }
            case NULL -> {
                return new AstType(AstType.Genre.NULL, false);
            }
            default -> throw new SemanticError(
                    "Met unexpected error in checkAtom()",
                    node.position
            );
        }
    }

    // endregion
}
