package frontend.ast;

import frontend.ast.node.*;
import frontend.parser.MxxParser;
import frontend.parser.MxxParserBaseVisitor;
import org.antlr.v4.runtime.ParserRuleContext;
import utility.Constant;
import utility.error.SemanticError;

public class AstBuilder extends MxxParserBaseVisitor<AstNode> {
    public NodeRoot astRoot = null;
    final MxxParser.ProgramContext parseTreeRoot;

    public AstBuilder(MxxParser.ProgramContext parseTreeRoot_) {
        parseTreeRoot = parseTreeRoot_;
    }

    public void build() {
        astRoot = (NodeRoot) visit(parseTreeRoot);
    }

    // region tools
    String checkIdentifier(String text, ParserRuleContext ctx) {
        if (text.charAt(0) == '_')
            throw new SemanticError("Get identifier begins with '_'", new AstPosition(ctx));
        return text;
    }
    // endregion

    // region ast
    @Override
    public AstNode visitProgram(MxxParser.ProgramContext ctx) {
        var node = new NodeRoot(new AstPosition(ctx));
        var sonList = ctx.programSection();
        for (var son : sonList) node.programSections.add((NodeProgramSection) visit(son));
        return node;
    }

    @Override
    public AstNode visitProgramSection(MxxParser.ProgramSectionContext ctx) {
        var node = new NodeProgramSection(new AstPosition(ctx));
        if (ctx.classDefine() != null) {
            node.genre = NodeProgramSection.Genre.CLASS_DEFINE;
            node.classDefineNode = (NodeClassDefine) visit(ctx.classDefine());
        } else if (ctx.functionDefine() != null) {
            node.genre = NodeProgramSection.Genre.FUNCTION_DEFINE;
            node.functionDefine = (NodeFunctionDefine) visit(ctx.functionDefine());
        } else if (ctx.variableDefine() != null) {
            node.genre = NodeProgramSection.Genre.VARIABLE_DEFINE;
            node.globalVariableDefine = (NodeVariableDefine) visit(ctx.variableDefine());
        } else throw new SemanticError(
                "Unexpected error in visitProgramSection()", node.position);
        return node;
    }

    @Override
    public AstNode visitClassDefine(MxxParser.ClassDefineContext ctx) {
        var node = new NodeClassDefine(new AstPosition(ctx));
        node.name = checkIdentifier(ctx.IDENTIFIER().getText(), ctx);
        var funcList = ctx.functionDefine();
        for (var son : funcList) node.methodDefines.add((NodeFunctionDefine) visit(son));
        var varList = ctx.variableDefine();
        for (var son : varList) node.variableDefines.add((NodeVariableDefine) visit(son));
        return node;
    }

    @Override
    public AstNode visitFunctionDefine(MxxParser.FunctionDefineContext ctx) {
        var node = new NodeFunctionDefine(new AstPosition(ctx));
        var type = ctx.type();
        var argumentList = ctx.argumentList();
        if (type != null) node.type = (NodeType) visit(type);
        node.name = checkIdentifier(ctx.IDENTIFIER().getText(), ctx);
        if (argumentList != null) node.argumentList = (NodeArgumentList) visit(argumentList);
        node.suite = (NodeSuite) visit(ctx.suite());
        return node;
    }

    @Override
    public AstNode visitArgumentList(MxxParser.ArgumentListContext ctx) {
        var node = new NodeArgumentList(new AstPosition(ctx));
        var typeList = ctx.type();
        for (var son : typeList) node.types.add((NodeType) visit(son));
        var identifierList = ctx.IDENTIFIER();
        for (var son : identifierList) node.identifiers.add(checkIdentifier(son.getText(), ctx));
        return node;
    }

    @Override
    public AstNode visitVariableDefine(MxxParser.VariableDefineContext ctx) {
        var node = new NodeVariableDefine(new AstPosition(ctx));
        node.type = (NodeType) visit(ctx.type());
        var termList = ctx.variableTerm();
        for (var son : termList) node.variableTerms.add((NodeVariableTerm) visit(son));
        return node;
    }

    @Override
    public AstNode visitVariableTerm(MxxParser.VariableTermContext ctx) {
        var node = new NodeVariableTerm(new AstPosition(ctx));
        node.name = checkIdentifier(ctx.IDENTIFIER().getText(), ctx);
        var expression = ctx.expression();
        if (expression != null) node.initialExpression = (NodeExpression) visit(expression);

        if (ctx.expressionList() != null)
            node.constructorArguments = (NodeExpressionList) visit(ctx.expressionList());
        return node;
    }

    @Override
    public AstNode visitType(MxxParser.TypeContext ctx) {
        var node = new NodeType(new AstPosition(ctx));
        AstType type;
        if (ctx.VOID() != null) type = new AstType(AstType.Genre.VOID, false);
        else if (ctx.BOOL() != null) type = new AstType(AstType.Genre.BOOLEAN, false);
        else if (ctx.INT() != null) type = new AstType(AstType.Genre.INTEGER, false);
        else if (ctx.STRING() != null) type = new AstType(AstType.Genre.STRING, false);
        else if (ctx.IDENTIFIER() != null)
            type = new AstType(checkIdentifier(ctx.IDENTIFIER().getText(), ctx));
        else throw new SemanticError("Unexpected error in visitType()", node.position);

        var bracketList = ctx.bracket();
        type.dimension = bracketList.size();
        for (var son : bracketList) node.brackets.add((NodeBracket) visit(son));

        node.type = type;

        return node;
    }

    @Override
    public AstNode visitBracket(MxxParser.BracketContext ctx) {
        var node = new NodeBracket(new AstPosition(ctx));
        var expr = ctx.expression();
        if (expr != null) node.expression = (NodeExpression) visit(expr);
        return node;
    }

    @Override
    public AstNode visitSuite(MxxParser.SuiteContext ctx) {
        var node = new NodeSuite(new AstPosition(ctx));
        var statementList = ctx.statement();
        for (var son : statementList) node.statements.add((NodeStatement) visit(son));
        return node;
    }

    // * Statement
    // region statement
    @Override
    public AstNode visitSuiteStmt(MxxParser.SuiteStmtContext ctx) {
        var node = new NodeStatement(new AstPosition(ctx));
        node.genre = NodeStatement.Genre.SUITE;

        node.suite = (NodeSuite) visit(ctx.suite());

        return node;
    }

    @Override
    public AstNode visitIfStmt(MxxParser.IfStmtContext ctx) {
        var node = new NodeStatement(new AstPosition(ctx));
        node.genre = NodeStatement.Genre.IF;

        node.ifCondExpr = (NodeExpression) visit(ctx.expression());
        node.trueBranchStmt = (NodeStatement) visit(ctx.trueBranch);
        var falseBranch = ctx.falseBranch;
        if (falseBranch != null) node.falseBranchStmt = (NodeStatement) visit(ctx.falseBranch);

        return node;
    }

    @Override
    public AstNode visitForStmt(MxxParser.ForStmtContext ctx) {
        var node = new NodeStatement(new AstPosition(ctx));
        node.genre = NodeStatement.Genre.FOR;

        var variableDefine = ctx.variableDefine();
        var initialExpr = ctx.initialExpr;
        var forCondExpr = ctx.forCondExpr;
        var stepExpr = ctx.stepExpr;
        if (variableDefine != null) {
            node.initialWithVarDef = true;
            node.initialVarDef = (NodeVariableDefine) visit(variableDefine);
        } else if (initialExpr != null) {
            node.initialWithVarDef = false;
            node.initialExpr = (NodeExpression) visit(initialExpr);
        } // else initialWithVarDef = null;
        if (forCondExpr != null) node.forCondExpr = (NodeExpression) visit(ctx.forCondExpr);
        if (stepExpr != null) node.stepExpr = (NodeExpression) visit(ctx.stepExpr);
        node.forBodyStmt = (NodeStatement) visit(ctx.statement());

        return node;
    }

    @Override
    public AstNode visitWhileStmt(MxxParser.WhileStmtContext ctx) {
        var node = new NodeStatement(new AstPosition(ctx));
        node.genre = NodeStatement.Genre.WHILE;

        node.whileCondExpr = (NodeExpression) visit(ctx.expression());
        node.whileBodyStmt = (NodeStatement) visit(ctx.statement());

        return node;
    }

    @Override
    public AstNode visitContinueStmt(MxxParser.ContinueStmtContext ctx) {
        var node = new NodeStatement(new AstPosition(ctx));
        node.genre = NodeStatement.Genre.CONTINUE;
        return node;
    }

    @Override
    public AstNode visitBreakStmt(MxxParser.BreakStmtContext ctx) {
        var node = new NodeStatement(new AstPosition(ctx));
        node.genre = NodeStatement.Genre.BREAK;
        return node;
    }

    @Override
    public AstNode visitReturnStmt(MxxParser.ReturnStmtContext ctx) {
        var node = new NodeStatement(new AstPosition(ctx));
        node.genre = NodeStatement.Genre.RETURN;

        var returnExpr = ctx.expression();
        if (returnExpr != null) node.returnExpr = (NodeExpression) visit(returnExpr);

        return node;
    }

    @Override
    public AstNode visitSingleExprStmt(MxxParser.SingleExprStmtContext ctx) {
        var node = new NodeStatement(new AstPosition(ctx));
        node.genre = NodeStatement.Genre.SINGLE_EXPRESSION;

        node.singleExpr = (NodeExpression) visit(ctx.expression());

        return node;
    }

    @Override
    public AstNode visitVariableStmt(MxxParser.VariableStmtContext ctx) {
        var node = new NodeStatement(new AstPosition(ctx));
        node.genre = NodeStatement.Genre.VARIABLE_DEFINE;

        node.variableDefine = (NodeVariableDefine) visit(ctx.variableDefine());

        return node;
    }

    @Override
    public AstNode visitEmptyStmt(MxxParser.EmptyStmtContext ctx) {
        var node = new NodeStatement(new AstPosition(ctx));
        node.genre = NodeStatement.Genre.EMPTY;
        return node;
    }

    // endregion

    // * Expression
    // region expression

    @Override
    public AstNode visitParenExpr(MxxParser.ParenExprContext ctx) {
        var node = new NodeExpression(new AstPosition(ctx));
        node.genre = NodeExpression.Genre.PAREN;
        node.parenExpr = (NodeExpression) visit(ctx.expression());
        return node;
    }

    @Override
    public AstNode visitAtomExpr(MxxParser.AtomExprContext ctx) {
        var node = new NodeExpression(new AstPosition(ctx));
        node.genre = NodeExpression.Genre.ATOM;
        node.atom = (NodeAtom) visit(ctx.atom());
        return node;
    }

    @Override
    public AstNode visitMemberExpr(MxxParser.MemberExprContext ctx) {
        var node = new NodeExpression(new AstPosition(ctx));
        node.genre = NodeExpression.Genre.MEMBER;
        node.objectExpr = (NodeExpression) visit(ctx.expression());
        node.memberName = checkIdentifier(ctx.IDENTIFIER().getText(), ctx);
        return node;
    }

    @Override
    public AstNode visitArrayExpr(MxxParser.ArrayExprContext ctx) {
        var node = new NodeExpression(new AstPosition(ctx));
        node.genre = NodeExpression.Genre.ARRAY;
        node.arrayNameExpr = (NodeExpression) visit(ctx.expression());
        var bracketList = ctx.bracket();
        for (var son : bracketList) node.brackets.add((NodeBracket) visit(son));
        return node;
    }

    @Override
    public AstNode visitFunctionExpr(MxxParser.FunctionExprContext ctx) {
        var node = new NodeExpression(new AstPosition(ctx));
        node.genre = NodeExpression.Genre.FUNCTION;
        node.functionExpr = (NodeExpression) visit(ctx.expression());
        var arguments = ctx.expressionList();
        if (arguments != null) node.arguments = (NodeExpressionList) visit(arguments);
        return node;
    }

    @Override
    public AstNode visitAssignExpr(MxxParser.AssignExprContext ctx) {
        var node = new NodeExpression(new AstPosition(ctx));
        node.genre = NodeExpression.Genre.ASSIGN;
        node.lValue = (NodeExpression) visit(ctx.lValue);
        node.rValue = (NodeExpression) visit(ctx.rValue);
        return node;
    }

    @Override
    public AstNode visitNewExpr(MxxParser.NewExprContext ctx) {
        var node = new NodeExpression(new AstPosition(ctx));
        node.genre = NodeExpression.Genre.NEW;
        node.type = (NodeType) visit(ctx.type());
        return node;
    }

    @Override
    public AstNode visitSelfExpr(MxxParser.SelfExprContext ctx) {
        var node = new NodeExpression(new AstPosition(ctx));
        node.genre = NodeExpression.Genre.SELF;

        if (ctx.INC() != null) node.operator = NodeExpression.OpEnum.INC;
        else if (ctx.DEC() != null) node.operator = NodeExpression.OpEnum.DEC;
        else throw new SemanticError("Unexpected error in visitSelfExpr()", node.position);
        node.termExpr = (NodeExpression) visit(ctx.expression());

        return node;
    }

    @Override
    public AstNode visitUnaryExpr(MxxParser.UnaryExprContext ctx) {
        var node = new NodeExpression(new AstPosition(ctx));
        node.genre = NodeExpression.Genre.UNARY;

        if (ctx.INC() != null) node.operator = NodeExpression.OpEnum.INC;
        else if (ctx.DEC() != null) node.operator = NodeExpression.OpEnum.DEC;
        else if (ctx.BANG() != null) node.operator = NodeExpression.OpEnum.BANG;
        else if (ctx.TILDE() != null) node.operator = NodeExpression.OpEnum.TILDE;
        else if (ctx.ADD() != null) node.operator = NodeExpression.OpEnum.ADD;
        else if (ctx.SUB() != null) node.operator = NodeExpression.OpEnum.SUB;
        else throw new SemanticError("Unexpected error in visitUnaryExpr()", node.position);
        node.termExpr = (NodeExpression) visit(ctx.expression());

        return node;
    }

    @Override
    public AstNode visitBinaryExpr(MxxParser.BinaryExprContext ctx) {
        var node = new NodeExpression(new AstPosition(ctx));
        node.genre = NodeExpression.Genre.BINARY;

        if (ctx.MUL() != null) node.operator = NodeExpression.OpEnum.MUL;
        else if (ctx.DIV() != null) node.operator = NodeExpression.OpEnum.DIV;
        else if (ctx.MOD() != null) node.operator = NodeExpression.OpEnum.MOD;

        else if (ctx.ADD() != null) node.operator = NodeExpression.OpEnum.ADD;
        else if (ctx.SUB() != null) node.operator = NodeExpression.OpEnum.SUB;

        else if (ctx.SHIFT_L() != null) node.operator = NodeExpression.OpEnum.SHIFT_L;
        else if (ctx.SHIFT_R() != null) node.operator = NodeExpression.OpEnum.SHIFT_R;

        else if (ctx.GT() != null) node.operator = NodeExpression.OpEnum.GT;
        else if (ctx.LT() != null) node.operator = NodeExpression.OpEnum.LT;
        else if (ctx.GE() != null) node.operator = NodeExpression.OpEnum.GE;
        else if (ctx.LE() != null) node.operator = NodeExpression.OpEnum.LE;
        else if (ctx.EQ() != null) node.operator = NodeExpression.OpEnum.EQ;
        else if (ctx.NOT_EQ() != null) node.operator = NodeExpression.OpEnum.NOT_EQ;

        else if (ctx.BIT_AND() != null) node.operator = NodeExpression.OpEnum.BIT_AND;
        else if (ctx.BIT_OR() != null) node.operator = NodeExpression.OpEnum.BIT_OR;
        else if (ctx.CARET() != null) node.operator = NodeExpression.OpEnum.CARET;
        else if (ctx.AND() != null) node.operator = NodeExpression.OpEnum.AND;
        else if (ctx.OR() != null) node.operator = NodeExpression.OpEnum.OR;
        else throw new SemanticError("Unexpected error in visitBinaryExpr()", node.position);

        node.lTermExpr = (NodeExpression) visit(ctx.lTerm);
        node.rTermExpr = (NodeExpression) visit(ctx.rTerm);

        return node;
    }

    @Override
    public AstNode visitLambdaExpr(MxxParser.LambdaExprContext ctx) {
        var node = new NodeExpression(new AstPosition(ctx));
        node.genre = NodeExpression.Genre.LAMBDA;

        var argList = ctx.argumentList();
        var exprList = ctx.expressionList();
        if (argList != null) node.lambdaArgumentList = (NodeArgumentList) visit(argList);
        node.lambdaSuite = (NodeSuite) visit(ctx.suite());
        if (exprList != null) node.lambdaExpressionList = (NodeExpressionList) visit(exprList);

        return node;
    }

    // endregion

    @Override
    public AstNode visitExpressionList(MxxParser.ExpressionListContext ctx) {
        var node = new NodeExpressionList(new AstPosition(ctx));
        var expressionList = ctx.expression();
        for (var son : expressionList) node.expressions.add((NodeExpression) visit(son));
        return node;
    }

    @Override
    public AstNode visitAtom(MxxParser.AtomContext ctx) {
        var node = new NodeAtom(new AstPosition(ctx));
        if (ctx.THIS() != null) node.genre = NodeAtom.Genre.THIS;
        var identifier = ctx.IDENTIFIER();
        if (identifier != null) {
            node.genre = NodeAtom.Genre.IDENTIFIER;
            node.identifier = checkIdentifier(identifier.getText(), ctx);
        }
        var string = ctx.STRING_CONSTANT();
        if (string != null) {
            node.genre = NodeAtom.Genre.STRING_CONSTANT;
            node.stringConstant = checkIdentifier(string.getText(), ctx);
            node.stringConstant = node.stringConstant
                    .substring(1, node.stringConstant.length() - 1);
        }
        var decimalInteger = ctx.DECIMAL_INTEGER();
        if (decimalInteger != null) {
            node.genre = NodeAtom.Genre.DECIMAL_INTEGER;
            node.decimalInteger = Integer.parseInt(checkIdentifier(decimalInteger.getText(), ctx));
        }
        if (ctx.TRUE() != null) {
            node.genre = NodeAtom.Genre.BOOLEAN;
            node.booleanValue = true;
        } else if (ctx.FALSE() != null) {
            node.genre = NodeAtom.Genre.BOOLEAN;
            node.booleanValue = false;
        }
        if (ctx.NULL() != null) node.genre = NodeAtom.Genre.NULL;

        if (node.genre == null) throw new SemanticError("Unexpected error in visitAtom()", node.position);

        return node;
    }
    // endregion

    // region built-in

    NodeProgramSection generateProgramSection(NodeFunctionDefine node) {
        var section = new NodeProgramSection(null);
        section.genre = NodeProgramSection.Genre.FUNCTION_DEFINE;
        section.functionDefine = node;
        return section;
    }

    NodeProgramSection generateProgramSection(NodeClassDefine node) {
        var section = new NodeProgramSection(null);
        section.genre = NodeProgramSection.Genre.CLASS_DEFINE;
        section.classDefineNode = node;
        return section;
    }

    // * Built-in
    public void generateBuiltIn() {
        var typeVoid = new AstType(AstType.Genre.VOID, false);
        var typeString = new AstType(AstType.Genre.STRING, false);
        var typeInt = new AstType(AstType.Genre.INTEGER, false);
        var typeBool = new AstType(AstType.Genre.BOOLEAN, false);
        // region Built-in function
        // void print(string str);
        astRoot.programSections.add(generateProgramSection(createBuiltInFunction(
                typeVoid, "print",
                typeString, "str"
        )));
        // void println(string str);
        astRoot.programSections.add(generateProgramSection(createBuiltInFunction(
                typeVoid, "println",
                typeString, "str"
        )));
        // void printInt(int n);
        astRoot.programSections.add(generateProgramSection(createBuiltInFunction(
                typeVoid, "printInt",
                typeInt, "n"
        )));
        // void printlnInt(int n);
        astRoot.programSections.add(generateProgramSection(createBuiltInFunction(
                typeVoid, "printlnInt",
                typeInt, "n"
        )));
        // string getString();
        astRoot.programSections.add(generateProgramSection(createBuiltInFunction(
                typeString, "getString"
        )));
        // int getInt();
        astRoot.programSections.add(generateProgramSection(createBuiltInFunction(
                typeInt, "getInt"
        )));
        // string toString(int i);
        astRoot.programSections.add(generateProgramSection(createBuiltInFunction(
                typeString, "toString",
                typeInt, "i"
        )));
        // * Built-in string arithmetic function
        astRoot.programSections.add(generateProgramSection(createBuiltInFunction(
                typeString, "_string_add",
                typeString, "lhs",
                typeString, "rhs"
        )));
        astRoot.programSections.add(generateProgramSection(createBuiltInFunction(
                typeBool, "_string_equal",
                typeString, "lhs",
                typeString, "rhs"
        )));
        astRoot.programSections.add(generateProgramSection(createBuiltInFunction(
                typeBool, "_string_not_equal",
                typeString, "lhs",
                typeString, "rhs"
        )));
        astRoot.programSections.add(generateProgramSection(createBuiltInFunction(
                typeBool, "_string_less",
                typeString, "lhs",
                typeString, "rhs"
        )));
        astRoot.programSections.add(generateProgramSection(createBuiltInFunction(
                typeBool, "_string_greater",
                typeString, "lhs",
                typeString, "rhs"
        )));
        astRoot.programSections.add(generateProgramSection(createBuiltInFunction(
                typeBool, "_string_less_or_equal",
                typeString, "lhs",
                typeString, "rhs"
        )));
        astRoot.programSections.add(generateProgramSection(createBuiltInFunction(
                typeBool, "_string_greater_or_equal",
                typeString, "lhs",
                typeString, "rhs"
        )));
        // endregion

        // region Built-in method
        var stringClass = new NodeClassDefine(null);
        stringClass.builtIn = true;
        stringClass.name = Constant.builtInStringClassName;
        astRoot.programSections.add(generateProgramSection(stringClass));
        // int length();
        stringClass.methodDefines.add(createBuiltInFunction(
                typeInt,
                "length"
        ));
        // string substring(int left, int right);
        stringClass.methodDefines.add(createBuiltInFunction(
                typeString,
                "substring",
                typeInt, "left",
                typeInt, "right"
        ));
        // int parseInt();
        stringClass.methodDefines.add(createBuiltInFunction(
                typeInt,
                "parseInt"
        ));
        // int ord(int pos);
        stringClass.methodDefines.add(createBuiltInFunction(
                typeInt,
                "ord",
                typeInt,
                "pos"
        ));

        var arrayClass = new NodeClassDefine(null);
        arrayClass.builtIn = true;
        arrayClass.name = Constant.builtInArrayClassName;
        astRoot.programSections.add(generateProgramSection(arrayClass));
        // int size();
        arrayClass.methodDefines.add(createBuiltInFunction(
                typeInt,
                "size"
        ));
        // endregion
    }

    // Built-in function without argument
    NodeFunctionDefine createBuiltInFunction(
            AstType returnType, String name
    ) {
        var node = new NodeFunctionDefine(null);
        node.builtIn = true;
        node.type = new NodeType(null);
        node.type.type = returnType;
        node.name = name;
        node.argumentList = new NodeArgumentList(null);
        node.suite = new NodeSuite(null);
        return node;
    }

    // Built-in function with one argument
    NodeFunctionDefine createBuiltInFunction(
            AstType returnType, String name,
            AstType argumentType,      // Built-in functions have no more
            String argumentName     // than one argument.
    ) {
        var node = createBuiltInFunction(returnType, name);
        var typeNode = new NodeType(null);
        typeNode.type = argumentType;
        node.argumentList.types.add(typeNode);
        node.argumentList.identifiers.add(argumentName);
        return node;
    }

    // Built-in function with two arguments
    NodeFunctionDefine createBuiltInFunction(
            AstType returnType, String name,
            AstType argumentType1, String argumentName1,
            AstType argumentType2, String argumentName2
    ) {
        var node = createBuiltInFunction(returnType, name);
        var typeNode1 = new NodeType(null);
        var typeNode2 = new NodeType(null);
        typeNode1.type = argumentType1;
        typeNode2.type = argumentType2;
        node.argumentList.types.add(typeNode1);
        node.argumentList.types.add(typeNode2);
        node.argumentList.identifiers.add(argumentName1);
        node.argumentList.identifiers.add(argumentName2);
        return node;
    }

    // endregion
}












