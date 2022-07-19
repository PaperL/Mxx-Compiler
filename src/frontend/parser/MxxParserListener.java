package frontend.parser;

// Generated from E:/WinWorkSpace/@Java/Mxx-Compiler/grammar\MxxParser.g4 by ANTLR 4.9.1

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MxxParser}.
 */
public interface MxxParserListener extends ParseTreeListener {
    /**
     * Enter a parse tree produced by {@link MxxParser#program}.
     *
     * @param ctx the parse tree
     */
    void enterProgram(MxxParser.ProgramContext ctx);

    /**
     * Exit a parse tree produced by {@link MxxParser#program}.
     *
     * @param ctx the parse tree
     */
    void exitProgram(MxxParser.ProgramContext ctx);

    /**
     * Enter a parse tree produced by {@link MxxParser#programSection}.
     *
     * @param ctx the parse tree
     */
    void enterProgramSection(MxxParser.ProgramSectionContext ctx);

    /**
     * Exit a parse tree produced by {@link MxxParser#programSection}.
     *
     * @param ctx the parse tree
     */
    void exitProgramSection(MxxParser.ProgramSectionContext ctx);

    /**
     * Enter a parse tree produced by {@link MxxParser#classDefine}.
     *
     * @param ctx the parse tree
     */
    void enterClassDefine(MxxParser.ClassDefineContext ctx);

    /**
     * Exit a parse tree produced by {@link MxxParser#classDefine}.
     *
     * @param ctx the parse tree
     */
    void exitClassDefine(MxxParser.ClassDefineContext ctx);

    /**
     * Enter a parse tree produced by {@link MxxParser#functionDefine}.
     *
     * @param ctx the parse tree
     */
    void enterFunctionDefine(MxxParser.FunctionDefineContext ctx);

    /**
     * Exit a parse tree produced by {@link MxxParser#functionDefine}.
     *
     * @param ctx the parse tree
     */
    void exitFunctionDefine(MxxParser.FunctionDefineContext ctx);

    /**
     * Enter a parse tree produced by {@link MxxParser#argumentList}.
     *
     * @param ctx the parse tree
     */
    void enterArgumentList(MxxParser.ArgumentListContext ctx);

    /**
     * Exit a parse tree produced by {@link MxxParser#argumentList}.
     *
     * @param ctx the parse tree
     */
    void exitArgumentList(MxxParser.ArgumentListContext ctx);

    /**
     * Enter a parse tree produced by {@link MxxParser#variableDefine}.
     *
     * @param ctx the parse tree
     */
    void enterVariableDefine(MxxParser.VariableDefineContext ctx);

    /**
     * Exit a parse tree produced by {@link MxxParser#variableDefine}.
     *
     * @param ctx the parse tree
     */
    void exitVariableDefine(MxxParser.VariableDefineContext ctx);

    /**
     * Enter a parse tree produced by {@link MxxParser#variableTerm}.
     *
     * @param ctx the parse tree
     */
    void enterVariableTerm(MxxParser.VariableTermContext ctx);

    /**
     * Exit a parse tree produced by {@link MxxParser#variableTerm}.
     *
     * @param ctx the parse tree
     */
    void exitVariableTerm(MxxParser.VariableTermContext ctx);

    /**
     * Enter a parse tree produced by {@link MxxParser#type}.
     *
     * @param ctx the parse tree
     */
    void enterType(MxxParser.TypeContext ctx);

    /**
     * Exit a parse tree produced by {@link MxxParser#type}.
     *
     * @param ctx the parse tree
     */
    void exitType(MxxParser.TypeContext ctx);

    /**
     * Enter a parse tree produced by {@link MxxParser#bracket}.
     *
     * @param ctx the parse tree
     */
    void enterBracket(MxxParser.BracketContext ctx);

    /**
     * Exit a parse tree produced by {@link MxxParser#bracket}.
     *
     * @param ctx the parse tree
     */
    void exitBracket(MxxParser.BracketContext ctx);

    /**
     * Enter a parse tree produced by {@link MxxParser#suite}.
     *
     * @param ctx the parse tree
     */
    void enterSuite(MxxParser.SuiteContext ctx);

    /**
     * Exit a parse tree produced by {@link MxxParser#suite}.
     *
     * @param ctx the parse tree
     */
    void exitSuite(MxxParser.SuiteContext ctx);

    /**
     * Enter a parse tree produced by the {@code suiteStmt}
     * labeled alternative in {@link MxxParser#statement}.
     *
     * @param ctx the parse tree
     */
    void enterSuiteStmt(MxxParser.SuiteStmtContext ctx);

    /**
     * Exit a parse tree produced by the {@code suiteStmt}
     * labeled alternative in {@link MxxParser#statement}.
     *
     * @param ctx the parse tree
     */
    void exitSuiteStmt(MxxParser.SuiteStmtContext ctx);

    /**
     * Enter a parse tree produced by the {@code ifStmt}
     * labeled alternative in {@link MxxParser#statement}.
     *
     * @param ctx the parse tree
     */
    void enterIfStmt(MxxParser.IfStmtContext ctx);

    /**
     * Exit a parse tree produced by the {@code ifStmt}
     * labeled alternative in {@link MxxParser#statement}.
     *
     * @param ctx the parse tree
     */
    void exitIfStmt(MxxParser.IfStmtContext ctx);

    /**
     * Enter a parse tree produced by the {@code forStmt}
     * labeled alternative in {@link MxxParser#statement}.
     *
     * @param ctx the parse tree
     */
    void enterForStmt(MxxParser.ForStmtContext ctx);

    /**
     * Exit a parse tree produced by the {@code forStmt}
     * labeled alternative in {@link MxxParser#statement}.
     *
     * @param ctx the parse tree
     */
    void exitForStmt(MxxParser.ForStmtContext ctx);

    /**
     * Enter a parse tree produced by the {@code whileStmt}
     * labeled alternative in {@link MxxParser#statement}.
     *
     * @param ctx the parse tree
     */
    void enterWhileStmt(MxxParser.WhileStmtContext ctx);

    /**
     * Exit a parse tree produced by the {@code whileStmt}
     * labeled alternative in {@link MxxParser#statement}.
     *
     * @param ctx the parse tree
     */
    void exitWhileStmt(MxxParser.WhileStmtContext ctx);

    /**
     * Enter a parse tree produced by the {@code continueStmt}
     * labeled alternative in {@link MxxParser#statement}.
     *
     * @param ctx the parse tree
     */
    void enterContinueStmt(MxxParser.ContinueStmtContext ctx);

    /**
     * Exit a parse tree produced by the {@code continueStmt}
     * labeled alternative in {@link MxxParser#statement}.
     *
     * @param ctx the parse tree
     */
    void exitContinueStmt(MxxParser.ContinueStmtContext ctx);

    /**
     * Enter a parse tree produced by the {@code breakStmt}
     * labeled alternative in {@link MxxParser#statement}.
     *
     * @param ctx the parse tree
     */
    void enterBreakStmt(MxxParser.BreakStmtContext ctx);

    /**
     * Exit a parse tree produced by the {@code breakStmt}
     * labeled alternative in {@link MxxParser#statement}.
     *
     * @param ctx the parse tree
     */
    void exitBreakStmt(MxxParser.BreakStmtContext ctx);

    /**
     * Enter a parse tree produced by the {@code returnStmt}
     * labeled alternative in {@link MxxParser#statement}.
     *
     * @param ctx the parse tree
     */
    void enterReturnStmt(MxxParser.ReturnStmtContext ctx);

    /**
     * Exit a parse tree produced by the {@code returnStmt}
     * labeled alternative in {@link MxxParser#statement}.
     *
     * @param ctx the parse tree
     */
    void exitReturnStmt(MxxParser.ReturnStmtContext ctx);

    /**
     * Enter a parse tree produced by the {@code singleExprStmt}
     * labeled alternative in {@link MxxParser#statement}.
     *
     * @param ctx the parse tree
     */
    void enterSingleExprStmt(MxxParser.SingleExprStmtContext ctx);

    /**
     * Exit a parse tree produced by the {@code singleExprStmt}
     * labeled alternative in {@link MxxParser#statement}.
     *
     * @param ctx the parse tree
     */
    void exitSingleExprStmt(MxxParser.SingleExprStmtContext ctx);

    /**
     * Enter a parse tree produced by the {@code variableStmt}
     * labeled alternative in {@link MxxParser#statement}.
     *
     * @param ctx the parse tree
     */
    void enterVariableStmt(MxxParser.VariableStmtContext ctx);

    /**
     * Exit a parse tree produced by the {@code variableStmt}
     * labeled alternative in {@link MxxParser#statement}.
     *
     * @param ctx the parse tree
     */
    void exitVariableStmt(MxxParser.VariableStmtContext ctx);

    /**
     * Enter a parse tree produced by the {@code emptyStmt}
     * labeled alternative in {@link MxxParser#statement}.
     *
     * @param ctx the parse tree
     */
    void enterEmptyStmt(MxxParser.EmptyStmtContext ctx);

    /**
     * Exit a parse tree produced by the {@code emptyStmt}
     * labeled alternative in {@link MxxParser#statement}.
     *
     * @param ctx the parse tree
     */
    void exitEmptyStmt(MxxParser.EmptyStmtContext ctx);

    /**
     * Enter a parse tree produced by the {@code newExpr}
     * labeled alternative in {@link MxxParser#expression}.
     *
     * @param ctx the parse tree
     */
    void enterNewExpr(MxxParser.NewExprContext ctx);

    /**
     * Exit a parse tree produced by the {@code newExpr}
     * labeled alternative in {@link MxxParser#expression}.
     *
     * @param ctx the parse tree
     */
    void exitNewExpr(MxxParser.NewExprContext ctx);

    /**
     * Enter a parse tree produced by the {@code unaryExpr}
     * labeled alternative in {@link MxxParser#expression}.
     *
     * @param ctx the parse tree
     */
    void enterUnaryExpr(MxxParser.UnaryExprContext ctx);

    /**
     * Exit a parse tree produced by the {@code unaryExpr}
     * labeled alternative in {@link MxxParser#expression}.
     *
     * @param ctx the parse tree
     */
    void exitUnaryExpr(MxxParser.UnaryExprContext ctx);

    /**
     * Enter a parse tree produced by the {@code arrayExpr}
     * labeled alternative in {@link MxxParser#expression}.
     *
     * @param ctx the parse tree
     */
    void enterArrayExpr(MxxParser.ArrayExprContext ctx);

    /**
     * Exit a parse tree produced by the {@code arrayExpr}
     * labeled alternative in {@link MxxParser#expression}.
     *
     * @param ctx the parse tree
     */
    void exitArrayExpr(MxxParser.ArrayExprContext ctx);

    /**
     * Enter a parse tree produced by the {@code lambdaExpr}
     * labeled alternative in {@link MxxParser#expression}.
     *
     * @param ctx the parse tree
     */
    void enterLambdaExpr(MxxParser.LambdaExprContext ctx);

    /**
     * Exit a parse tree produced by the {@code lambdaExpr}
     * labeled alternative in {@link MxxParser#expression}.
     *
     * @param ctx the parse tree
     */
    void exitLambdaExpr(MxxParser.LambdaExprContext ctx);

    /**
     * Enter a parse tree produced by the {@code memberExpr}
     * labeled alternative in {@link MxxParser#expression}.
     *
     * @param ctx the parse tree
     */
    void enterMemberExpr(MxxParser.MemberExprContext ctx);

    /**
     * Exit a parse tree produced by the {@code memberExpr}
     * labeled alternative in {@link MxxParser#expression}.
     *
     * @param ctx the parse tree
     */
    void exitMemberExpr(MxxParser.MemberExprContext ctx);

    /**
     * Enter a parse tree produced by the {@code selfExpr}
     * labeled alternative in {@link MxxParser#expression}.
     *
     * @param ctx the parse tree
     */
    void enterSelfExpr(MxxParser.SelfExprContext ctx);

    /**
     * Exit a parse tree produced by the {@code selfExpr}
     * labeled alternative in {@link MxxParser#expression}.
     *
     * @param ctx the parse tree
     */
    void exitSelfExpr(MxxParser.SelfExprContext ctx);

    /**
     * Enter a parse tree produced by the {@code atomExpr}
     * labeled alternative in {@link MxxParser#expression}.
     *
     * @param ctx the parse tree
     */
    void enterAtomExpr(MxxParser.AtomExprContext ctx);

    /**
     * Exit a parse tree produced by the {@code atomExpr}
     * labeled alternative in {@link MxxParser#expression}.
     *
     * @param ctx the parse tree
     */
    void exitAtomExpr(MxxParser.AtomExprContext ctx);

    /**
     * Enter a parse tree produced by the {@code binaryExpr}
     * labeled alternative in {@link MxxParser#expression}.
     *
     * @param ctx the parse tree
     */
    void enterBinaryExpr(MxxParser.BinaryExprContext ctx);

    /**
     * Exit a parse tree produced by the {@code binaryExpr}
     * labeled alternative in {@link MxxParser#expression}.
     *
     * @param ctx the parse tree
     */
    void exitBinaryExpr(MxxParser.BinaryExprContext ctx);

    /**
     * Enter a parse tree produced by the {@code assignExpr}
     * labeled alternative in {@link MxxParser#expression}.
     *
     * @param ctx the parse tree
     */
    void enterAssignExpr(MxxParser.AssignExprContext ctx);

    /**
     * Exit a parse tree produced by the {@code assignExpr}
     * labeled alternative in {@link MxxParser#expression}.
     *
     * @param ctx the parse tree
     */
    void exitAssignExpr(MxxParser.AssignExprContext ctx);

    /**
     * Enter a parse tree produced by the {@code parenExpr}
     * labeled alternative in {@link MxxParser#expression}.
     *
     * @param ctx the parse tree
     */
    void enterParenExpr(MxxParser.ParenExprContext ctx);

    /**
     * Exit a parse tree produced by the {@code parenExpr}
     * labeled alternative in {@link MxxParser#expression}.
     *
     * @param ctx the parse tree
     */
    void exitParenExpr(MxxParser.ParenExprContext ctx);

    /**
     * Enter a parse tree produced by the {@code functionExpr}
     * labeled alternative in {@link MxxParser#expression}.
     *
     * @param ctx the parse tree
     */
    void enterFunctionExpr(MxxParser.FunctionExprContext ctx);

    /**
     * Exit a parse tree produced by the {@code functionExpr}
     * labeled alternative in {@link MxxParser#expression}.
     *
     * @param ctx the parse tree
     */
    void exitFunctionExpr(MxxParser.FunctionExprContext ctx);

    /**
     * Enter a parse tree produced by {@link MxxParser#expressionList}.
     *
     * @param ctx the parse tree
     */
    void enterExpressionList(MxxParser.ExpressionListContext ctx);

    /**
     * Exit a parse tree produced by {@link MxxParser#expressionList}.
     *
     * @param ctx the parse tree
     */
    void exitExpressionList(MxxParser.ExpressionListContext ctx);

    /**
     * Enter a parse tree produced by {@link MxxParser#atom}.
     *
     * @param ctx the parse tree
     */
    void enterAtom(MxxParser.AtomContext ctx);

    /**
     * Exit a parse tree produced by {@link MxxParser#atom}.
     *
     * @param ctx the parse tree
     */
    void exitAtom(MxxParser.AtomContext ctx);
}