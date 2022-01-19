package ast;

import utility.AstPosition;

public class NodeBracket extends AstNode {
    public NodeExpression expression = null;

    public NodeBracket(AstPosition position) {
        super(position);
    }
}
