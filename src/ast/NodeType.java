package ast;

import utility.Position;

import java.util.ArrayList;

public class NodeType extends AstNode {
    public enum Genre {
        VOID, BOOL, INT, STRING, IDENTIFIER, ARRAY;
    }

    public Genre genre = null;

    public String name = null;
    public int dimension = 0;
    public ArrayList<NodeBracket> brackets = new ArrayList<>();

    public NodeType(Position position) {
        super(position);
    }
}
