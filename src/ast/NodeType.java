package ast;

import utility.AstPosition;
import utility.AstType;

import java.util.ArrayList;

public class NodeType extends AstNode {
    public AstType type;
    public ArrayList<NodeBracket> brackets = new ArrayList<>();

    public NodeType(AstPosition position) {
        super(position);
    }
}
