package frontend.ast.node;

import frontend.ast.AstPosition;

public class NodeBracket extends AstNode {
    public NodeExpression expression = null;

    public NodeBracket(AstPosition position) {
        super(position);
    }
}
