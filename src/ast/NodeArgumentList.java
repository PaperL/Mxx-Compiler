package ast;

import utility.Position;

import java.util.ArrayList;

public class NodeArgumentList extends AstNode {
    public ArrayList<NodeType> types = new ArrayList<>();
    public ArrayList<String> identifiers = new ArrayList<>();

    public NodeArgumentList(Position position) {
        super(position);
    }
}
