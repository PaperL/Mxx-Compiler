package ast;

import utility.AstPosition;

abstract public class AstNode {
    public AstPosition position;

    public AstNode(AstPosition position_) {
        position = position_;
    }
}
