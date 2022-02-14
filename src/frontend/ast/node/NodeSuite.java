package frontend.ast.node;

import java.util.ArrayList;
import frontend.ast.AstPosition;

public class NodeSuite extends AstNode{
    public ArrayList<NodeStatement> statements = new ArrayList<>();

    public NodeSuite(AstPosition position) {
        super(position);
    }
}
