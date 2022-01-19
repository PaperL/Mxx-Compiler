package ast;

import utility.AstPosition;

import java.util.ArrayList;

public class NodeExpressionList extends AstNode{
    public ArrayList<NodeExpression> expressions = new ArrayList<>();

    public NodeExpressionList(AstPosition position) {
        super(position);
    }
}
