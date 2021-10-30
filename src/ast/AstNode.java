package ast;

import utility.Position;

abstract public class AstNode {
    public Position position = null;

    public AstNode(Position position_) {
        position = position_;
    }

    // accept 看起来是 visit 的加强版
//    public void accept(AstVisitor visitor) {
//        visitor.visit(this);
//    }
}
