package frontend;

import ast.*;
import utility.Type;
import utility.error.SemanticError;
import utility.scope.BroadScope;
import utility.scope.FunctionScope;
import utility.scope.VariableScope;

import java.util.ArrayList;
import java.util.LinkedList;

public class SemanticChecker {
    // ? 应该优先使用 private 变量和方法
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
        // todo
        // 添加内建函数, 以及 array.size()
    }

    // region Jump_Check_Tools
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
        currentScope.variables.putAll(
                globalScope.getFunction(node.name, node.position).variables);
        // 若使用 clone() 需要在 globalScope 及其基类中实现深克隆
        scopeGenreStack.push(ScopeGenre.FUNCTION);
        scopeStack.push(currentScope);
        var returnType = checkSuite(node.suite);
        scopeStack.pop();
        scopeGenreStack.pop();
        if (!node.type.type.equals(returnType))
            throw new SemanticError(
                    "Get wrong type of function return value",
                    node.position
            );
    }

    // Only for lambda
    public ArrayList<Type> checkArgumentList(NodeArgumentList node) {
        var currentScope = new VariableScope(globalScope);
        final var len = node.types.size();
        var returnTypes = new ArrayList<Type>();
        for (int i = 0; i < len; i++) {
            currentScope.defineVariable(
                    node.identifiers.get(i),
                    checkType(node.types.get(i), true, false),
                    node.position
            );
            returnTypes.add(node.types.get(i).type);
        }
        scopeStack.push(currentScope);
        return returnTypes;
    }

    public void checkVariableDefine(NodeVariableDefine node) {
        final var type = checkType(node.type, true, false);
        final var position = node.position;
        var termList = node.variableTerms;
        for (var term : termList) {
            var name = checkVariableTerm(term, type);
            assert scopeStack.peek() != null;
            scopeStack.peek().defineVariable(name, type, position);
        }
    }

    public String checkVariableTerm(NodeVariableTerm node, Type type) {
        if (node.initialExpression != null) {
            var exprType = checkExpression(node.initialExpression);
            if (type != exprType)
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
    public Type checkType(NodeType node, boolean noDimension, boolean allowVoid) {
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
                    if (bracket == null) emptyBracket = true;
                    else if (emptyBracket) throw new SemanticError(
                            "Get unexpected fixed-length dimension after " +
                                    "unfixed-length dimension when creating an array"
                            , node.position
                    );
                }
            }
        }
        if (node.type.genre == Type.Genre.VOID && !allowVoid)
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
            if (type.genre != Type.Genre.INTEGER)
                throw new SemanticError(
                        "Get array element with index of non-integer type",
                        node.position
                );
            else return true;
        }
        return false;
    }

    public Type checkSuite(NodeSuite node) {
        scopeStack.push(new VariableScope(globalScope));
        var statementList = node.statements;
        var returnType = new Type(Type.Genre.VOID);
        for (var statement : statementList) {
            var statementType = checkStatement(statement);
            if (statementType.genre != Type.Genre.VOID) {
                if (returnType.genre == Type.Genre.VOID)
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
    public Type checkStatement(NodeStatement node) {
        switch (node.genre) {
            case SUITE -> {
                return checkSuite(node.suite);
            }
            case IF -> {
                if (checkExpression(node.ifCondExpr).genre != Type.Genre.BOOLEAN)
                    throw new SemanticError(
                            "Type of condition expression of 'if' statement is not Boolean",
                            node.position
                    );
                var returnType1 = checkStatement(node.trueBranchStmt);
                var returnType2 = checkStatement(node.falseBranchStmt);
                if (!returnType1.equals(returnType2))
                    throw new SemanticError(
                            "Wrong type of return value",
                            node.position
                    );
                else return returnType1;
            }
            case FOR -> {
                scopeStack.push(new VariableScope(globalScope));

                if (node.initialWithVarDef != null) {
                    if (node.initialWithVarDef) checkVariableDefine(node.initialVarDef);
                    else checkExpression(node.initialExpr);
                }
                if (node.forCondExpr != null) {
                    var exprType = checkExpression(node.forCondExpr);
                    if (exprType.genre != Type.Genre.BOOLEAN)
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
                if (checkExpression(node.whileCondExpr).genre != Type.Genre.BOOLEAN)
                    throw new SemanticError(
                            "Type of condition expression of 'while' statement is not Boolean",
                            node.position
                    );
                scopeGenreStack.push(ScopeGenre.LOOP);
                var returnType = checkStatement(node.whileBodyStmt);
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
                return checkExpression(node.returnExpr);
            }
            case SINGLE_EXPRESSION -> {
                return checkExpression(node.singleExpr);
            }
            case VARIABLE_DEFINE -> checkVariableDefine(node.variableDefine);
            case EMPTY -> {
                // just touch fish
            }
        }
        return new Type(Type.Genre.VOID);
    }

    public Type checkExpression(NodeExpression node) {
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
                } else if (objectType.genre == Type.Genre.STRING
                        || objectType.genre == Type.Genre.CLASS_NAME) {
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
                if (arrayType.dimension != node.brackets.size())
                    throw new SemanticError(
                            "Number of index cannot match the dimension of array",
                            node.position
                    );
                for (var bracket : node.brackets) {
                    if (!checkBracket(bracket))
                        throw new SemanticError(
                                "Get non-integer type or missing index of array",
                                node.position
                        );
                }
                return new Type(arrayType.className);
            }
            case FUNCTION -> {
                // * 在此处解决成员方法调用可以避免很多麻烦
                // Only string has built-in method
                // * Call string type constant's built-in method is undefined behavior

                String functionName = node.memberName;
                FunctionScope functionScope;
                if (node.functionExpr.genre == NodeExpression.Genre.MEMBER) {
                    // Method
                    var objectType = checkExpression(node.functionExpr.objectExpr);
                    var classScope = globalScope.getClass(objectType.className, node.position);
                    functionScope = classScope.getMethod(functionName, node.position);
                } else if (node.functionExpr.genre == NodeExpression.Genre.ATOM) {
                    // Function
                    functionScope = globalScope.getFunction(functionName, node.position);
                } else throw new SemanticError("Wrong function call", node.position);
                var arguments = checkExpressionList(node.arguments);
                var argumentTypes = functionScope.argumentsType;

                var len = argumentTypes.size();
                if (arguments.size() != len)
                    throw new SemanticError(
                            "Function call gets wrong number of arguments",
                            node.position
                    );
                for (int i = 0; i < len; i++) {
                    if (!argumentTypes.get(i).equals(arguments.get(i)))
                        throw new SemanticError(
                                "Function call gets a wrong type argument",
                                node.lambdaExpressionList.expressions.get(i).position
                        );
                }
                return functionScope.returnType;
            }
            // * Command
            case ASSIGN -> {
                // 根据题面 `15.左值`, 无规定的左值情况一律为 undefined behavior.
                var lValueType = checkExpression(node.lValue);
                var rValueType = checkExpression(node.rValue);
                if (lValueType.genre != rValueType.genre
                        || !lValueType.className.equals(rValueType.className)
                        || lValueType.dimension != rValueType.dimension) {
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
                if (termType.genre != Type.Genre.INTEGER
                        || termType.dimension != 0)
                    throw new SemanticError(
                            "Use postfix operator '++' or '--' with "
                                    + "non-integer type object",
                            node.position
                    );
                return termType;
            }
            case UNARY -> {
                var termType = checkExpression(node.termExpr);
                if (node.operator == NodeExpression.OpEnum.BANG) {
                    if (termType.genre != Type.Genre.BOOLEAN
                            || termType.dimension != 0)
                        throw new SemanticError(
                                "Use prefix operator '!' " +
                                        "with non-boolean type object",
                                node.position
                        );
                } else {
                    if (termType.genre != Type.Genre.INTEGER
                            || termType.dimension != 0)
                        throw new SemanticError(
                                "Use prefix operator '" + node.operator.toString()
                                        + "' with non-integer type object",
                                node.position
                        );
                }
            }
            case BINARY -> {
                var lTermType = checkExpression(node.lTermExpr);
                var rTermType = checkExpression(node.rTermExpr);
                switch (node.operator) {
                    // For a "string" object, some operators act on its text content.
                    case EQ, NOT_EQ -> {
                        // 由题面 `10.2` 可知, 除 int 和 bool 外, 不同 class 可以比较地址
                        if ((lTermType.genre == Type.Genre.INTEGER
                                || rTermType.genre == Type.Genre.INTEGER)
                                && lTermType.genre != rTermType.genre) {
                            throw new SemanticError(
                                    "Try judging equivalence between integer type " +
                                            "and non-integer type object",
                                    node.position
                            );
                        } else if ((lTermType.genre == Type.Genre.BOOLEAN
                                || rTermType.genre == Type.Genre.BOOLEAN)
                                && lTermType.genre != rTermType.genre) {
                            throw new SemanticError(
                                    "Try judging equivalence between boolean type " +
                                            "and non-boolean type object",
                                    node.position
                            );
                        } else return new Type(Type.Genre.BOOLEAN);
                    }
                    case AND, OR -> {
                        if (lTermType.genre != Type.Genre.BOOLEAN
                                || rTermType.genre != Type.Genre.BOOLEAN) {
                            throw new SemanticError(
                                    "Try using binary operator '&&' or '||' " +
                                            "with non-boolean type object",
                                    node.position
                            );
                        }
                        return new Type(Type.Genre.BOOLEAN);
                    }
                    case ADD -> {
                        if (lTermType.genre == Type.Genre.INTEGER
                                && rTermType.genre == Type.Genre.INTEGER)
                            return new Type(Type.Genre.INTEGER);
                        else if (lTermType.genre == Type.Genre.STRING
                                && rTermType.genre == Type.Genre.STRING)
                            return new Type(Type.Genre.STRING);
                        else throw new SemanticError(
                                    "Try adding two objects of different types," +
                                            "or the same type but not integer or string",
                                    node.position
                            );
                    }
                    case GT, LT, GE, LE -> {
                        if (lTermType.genre == Type.Genre.INTEGER
                                && rTermType.genre == Type.Genre.INTEGER)
                            return new Type(Type.Genre.BOOLEAN);
                        else if (lTermType.genre == Type.Genre.STRING
                                && rTermType.genre == Type.Genre.STRING)
                            return new Type(Type.Genre.BOOLEAN);
                        else throw new SemanticError(
                                    "Try comparing two objects of different types," +
                                            "or the same type but not integer or string",
                                    node.position
                            );
                    }
                    default -> {
                        if (lTermType.genre != Type.Genre.INTEGER
                                || rTermType.genre != Type.Genre.INTEGER)
                            throw new SemanticError(
                                    "Try  numerically calculating two objects " +
                                            "of non-integer types",
                                    node.position
                            );
                        else return new Type(Type.Genre.INTEGER);
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

    public ArrayList<Type> checkExpressionList(NodeExpressionList node) {
        var returnTypes = new ArrayList<Type>();
        for (var expression : node.expressions)
            returnTypes.add(checkExpression(expression));
        return returnTypes;
    }

    public Type checkAtom(NodeAtom node) {
        // "null" 不能参与运算(除 "==" & "!="), 能作为右值进行赋值(除字符串)
        switch (node.genre) {
            case THIS -> {
                if (in_class()) {
                    return new Type(currentClassName);
                } else throw new SemanticError(
                        "Use 'this' outside of class definition",
                        node.position
                );
            }
            case IDENTIFIER -> {
                assert scopeStack.peek() != null;
                System.out.println("here~~ 1");
                for (var scope : scopeStack) {
                    Type returnType = scope.getVariableType(node.identifier, node.position);
                    if (returnType != null) return returnType;
                }
                System.out.println("here~~ 2");
                throw new SemanticError(
                        "Variable/object '" + node.identifier + "' doesn't exist",
                        node.position);
            }
            case STRING_CONSTANT -> {
                return new Type(Type.Genre.STRING);
            }
            case DECIMAL_INTEGER -> {
                return new Type(Type.Genre.INTEGER);
            }
            case BOOLEAN -> {
                return new Type(Type.Genre.BOOLEAN);
            }
            case NULL -> {
                return new Type(Type.Genre.NULL);
            }
            default -> throw new SemanticError(
                    "Met unexpected error in checkAtom()",
                    node.position
            );
        }
    }

    // endregion
}
