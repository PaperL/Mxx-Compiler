package ast;

import utility.Position;

abstract public class AstNode {
    public Position position = null;

    public AstNode(Position position_) {
        position = position_;
    }
}
