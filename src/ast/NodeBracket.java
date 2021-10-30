package ast;

import utility.Position;

public class NodeBracket extends AstNode {
    public NodeExpression expression = null;

    public NodeBracket(Position position) {
        super(position);
    }
}
