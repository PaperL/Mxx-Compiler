package ast;

import utility.Position;

import java.util.ArrayList;

public class NodeSuite extends AstNode{
    public ArrayList<NodeStatement> statements = new ArrayList<>();

    public NodeSuite(Position position) {
        super(position);
    }
}
