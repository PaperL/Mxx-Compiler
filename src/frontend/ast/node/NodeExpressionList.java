package frontend.ast.node;

import java.util.ArrayList;
import frontend.ast.AstPosition;

public class NodeExpressionList extends AstNode{
    public ArrayList<NodeExpression> expressions = new ArrayList<>();

    public NodeExpressionList(AstPosition position) {
        super(position);
    }
}
