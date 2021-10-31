package ast;

import utility.Position;
import utility.Type;

import java.util.ArrayList;

public class NodeType extends AstNode {
    public Type type;
    public ArrayList<NodeBracket> brackets = new ArrayList<>();

    public NodeType(Position position) {
        super(position);
    }
}
