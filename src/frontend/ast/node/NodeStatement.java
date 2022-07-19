package frontend.ast.node;

import frontend.ast.AstPosition;

public class NodeStatement extends AstNode {
    public Genre genre = null;
    // SUITE
    public NodeSuite suite = null;
    // IF
    public NodeExpression ifCondExpr = null;    // condition expression
    public NodeStatement trueBranchStmt = null;
    public NodeStatement falseBranchStmt = null;
    // FOR
    public Boolean initialWithVarDef = null;
    public NodeExpression initialExpr = null;
    public NodeVariableDefine initialVarDef = null;
    public NodeExpression forCondExpr = null;
    public NodeExpression stepExpr = null;
    public NodeStatement forBodyStmt = null;
    // WHILE
    public NodeExpression whileCondExpr = null;
    public NodeStatement whileBodyStmt = null;
    // RETURN
    public NodeExpression returnExpr = null;
    // SINGLE_EXPRESSION
    public NodeExpression singleExpr = null;
    // VARIABLE_DEFINE
    public NodeVariableDefine variableDefine = null;

    public NodeStatement(AstPosition position) {
        super(position);
    }


    public enum Genre {
        SUITE, IF, FOR, WHILE, CONTINUE, BREAK,
        RETURN, SINGLE_EXPRESSION, VARIABLE_DEFINE, EMPTY
    }
}
