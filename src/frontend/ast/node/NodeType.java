package frontend.ast.node;

import java.util.ArrayList;
import frontend.ast.AstPosition;
import frontend.ast.AstType;

public class NodeType extends AstNode {
    public AstType type;
    public ArrayList<NodeBracket> brackets = new ArrayList<>();

    public NodeType(AstPosition position) {
        super(position);
    }
}
