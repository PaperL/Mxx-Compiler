package frontend.parser;

// Generated from E:/WinWorkSpace/@Java/Mxx-Compiler/grammar\MxxParser.g4 by ANTLR 4.9.1

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MxxParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 *            operations with no return type.
 */
public interface MxxParserVisitor<T> extends ParseTreeVisitor<T> {
    /**
     * Visit a parse tree produced by {@link MxxParser#program}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitProgram(MxxParser.ProgramContext ctx);

    /**
     * Visit a parse tree produced by {@link MxxParser#programSection}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitProgramSection(MxxParser.ProgramSectionContext ctx);

    /**
     * Visit a parse tree produced by {@link MxxParser#classDefine}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitClassDefine(MxxParser.ClassDefineContext ctx);

    /**
     * Visit a parse tree produced by {@link MxxParser#functionDefine}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitFunctionDefine(MxxParser.FunctionDefineContext ctx);

    /**
     * Visit a parse tree produced by {@link MxxParser#argumentList}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitArgumentList(MxxParser.ArgumentListContext ctx);

    /**
     * Visit a parse tree produced by {@link MxxParser#variableDefine}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitVariableDefine(MxxParser.VariableDefineContext ctx);

    /**
     * Visit a parse tree produced by {@link MxxParser#variableTerm}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitVariableTerm(MxxParser.VariableTermContext ctx);

    /**
     * Visit a parse tree produced by {@link MxxParser#type}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitType(MxxParser.TypeContext ctx);

    /**
     * Visit a parse tree produced by {@link MxxParser#bracket}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitBracket(MxxParser.BracketContext ctx);

    /**
     * Visit a parse tree produced by {@link MxxParser#suite}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitSuite(MxxParser.SuiteContext ctx);

    /**
     * Visit a parse tree produced by the {@code suiteStmt}
     * labeled alternative in {@link MxxParser#statement}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitSuiteStmt(MxxParser.SuiteStmtContext ctx);

    /**
     * Visit a parse tree produced by the {@code ifStmt}
     * labeled alternative in {@link MxxParser#statement}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitIfStmt(MxxParser.IfStmtContext ctx);

    /**
     * Visit a parse tree produced by the {@code forStmt}
     * labeled alternative in {@link MxxParser#statement}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitForStmt(MxxParser.ForStmtContext ctx);

    /**
     * Visit a parse tree produced by the {@code whileStmt}
     * labeled alternative in {@link MxxParser#statement}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitWhileStmt(MxxParser.WhileStmtContext ctx);

    /**
     * Visit a parse tree produced by the {@code continueStmt}
     * labeled alternative in {@link MxxParser#statement}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitContinueStmt(MxxParser.ContinueStmtContext ctx);

    /**
     * Visit a parse tree produced by the {@code breakStmt}
     * labeled alternative in {@link MxxParser#statement}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitBreakStmt(MxxParser.BreakStmtContext ctx);

    /**
     * Visit a parse tree produced by the {@code returnStmt}
     * labeled alternative in {@link MxxParser#statement}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitReturnStmt(MxxParser.ReturnStmtContext ctx);

    /**
     * Visit a parse tree produced by the {@code singleExprStmt}
     * labeled alternative in {@link MxxParser#statement}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitSingleExprStmt(MxxParser.SingleExprStmtContext ctx);

    /**
     * Visit a parse tree produced by the {@code variableStmt}
     * labeled alternative in {@link MxxParser#statement}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitVariableStmt(MxxParser.VariableStmtContext ctx);

    /**
     * Visit a parse tree produced by the {@code emptyStmt}
     * labeled alternative in {@link MxxParser#statement}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitEmptyStmt(MxxParser.EmptyStmtContext ctx);

    /**
     * Visit a parse tree produced by the {@code newExpr}
     * labeled alternative in {@link MxxParser#expression}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitNewExpr(MxxParser.NewExprContext ctx);

    /**
     * Visit a parse tree produced by the {@code unaryExpr}
     * labeled alternative in {@link MxxParser#expression}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitUnaryExpr(MxxParser.UnaryExprContext ctx);

    /**
     * Visit a parse tree produced by the {@code arrayExpr}
     * labeled alternative in {@link MxxParser#expression}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitArrayExpr(MxxParser.ArrayExprContext ctx);

    /**
     * Visit a parse tree produced by the {@code lambdaExpr}
     * labeled alternative in {@link MxxParser#expression}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitLambdaExpr(MxxParser.LambdaExprContext ctx);

    /**
     * Visit a parse tree produced by the {@code memberExpr}
     * labeled alternative in {@link MxxParser#expression}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitMemberExpr(MxxParser.MemberExprContext ctx);

    /**
     * Visit a parse tree produced by the {@code selfExpr}
     * labeled alternative in {@link MxxParser#expression}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitSelfExpr(MxxParser.SelfExprContext ctx);

    /**
     * Visit a parse tree produced by the {@code atomExpr}
     * labeled alternative in {@link MxxParser#expression}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitAtomExpr(MxxParser.AtomExprContext ctx);

    /**
     * Visit a parse tree produced by the {@code binaryExpr}
     * labeled alternative in {@link MxxParser#expression}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitBinaryExpr(MxxParser.BinaryExprContext ctx);

    /**
     * Visit a parse tree produced by the {@code assignExpr}
     * labeled alternative in {@link MxxParser#expression}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitAssignExpr(MxxParser.AssignExprContext ctx);

    /**
     * Visit a parse tree produced by the {@code parenExpr}
     * labeled alternative in {@link MxxParser#expression}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitParenExpr(MxxParser.ParenExprContext ctx);

    /**
     * Visit a parse tree produced by the {@code functionExpr}
     * labeled alternative in {@link MxxParser#expression}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitFunctionExpr(MxxParser.FunctionExprContext ctx);

    /**
     * Visit a parse tree produced by {@link MxxParser#expressionList}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitExpressionList(MxxParser.ExpressionListContext ctx);

    /**
     * Visit a parse tree produced by {@link MxxParser#atom}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitAtom(MxxParser.AtomContext ctx);
}