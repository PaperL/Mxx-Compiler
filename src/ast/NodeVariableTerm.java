package ast;

import utility.Position;

public class NodeVariableTerm extends AstNode {
    public String name = null;
    public NodeExpression initialExpression = null;

    public NodeVariableTerm(Position position) {
        super(position);
    }
}
