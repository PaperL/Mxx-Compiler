package frontend.ast.node;

import java.util.ArrayList;
import frontend.ast.AstPosition;

public class NodeArgumentList extends AstNode {
    public ArrayList<NodeType> types = new ArrayList<>();
    public ArrayList<String> identifiers = new ArrayList<>();

    public NodeArgumentList(AstPosition position) {
        super(position);
    }
}
