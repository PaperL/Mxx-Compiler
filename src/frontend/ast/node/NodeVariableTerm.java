package frontend.ast.node;

import frontend.ast.AstPosition;

public class NodeVariableTerm extends AstNode {
    public String name = null;
    public NodeExpression initialExpression = null;

    public NodeVariableTerm(AstPosition position) {
        super(position);
    }
}
