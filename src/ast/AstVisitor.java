package ast;

public interface  AstVisitor {
    // Throws error when try to visit a node without type
//    void visit(AstNode node)

    void visit(NodeRoot node);
    void visit(NodeProgramSection node);

    void visit(NodeClassDefine node);

    void visit(NodeFunctionDefine node);

    void visit(NodeArgumentList node);

    void visit(NodeVariableDefine node);
    void visit(NodeVariableTerm node);

    void visit(NodeType node);
    void visit(NodeBracket node);

    void visit(NodeSuite node);

    void visit(NodeStatement node);

    void visit(NodeExpression node);
    // todo 需要补充
    void visit(NodeExpressionList node);

    void visit(NodeAtom node);
}












