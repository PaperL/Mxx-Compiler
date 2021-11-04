package ast;

import utility.Position;

import java.util.ArrayList;

public class NodeExpressionList extends AstNode{
    public ArrayList<NodeExpression> expressions = new ArrayList<>();

    public NodeExpressionList(Position position) {
        super(position);
    }
}
