package frontend.ast.node;

import frontend.ast.AstPosition;

import java.util.ArrayList;

public class NodeSuite extends AstNode {
    public ArrayList<NodeStatement> statements = new ArrayList<>();

    public NodeSuite(AstPosition position) {
        super(position);
    }
}
