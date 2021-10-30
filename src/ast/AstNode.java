package ast;

import utility.Position;

abstract public class AstNode {
    public Position position = null;

    public AstNode(Position pos) {
        position = pos;
    }

    public void accept(AstVisitor visitor) {
        visitor.visit(this);
    }
}
