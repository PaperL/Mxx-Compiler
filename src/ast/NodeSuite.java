package ast;

import utility.AstPosition;

import java.util.ArrayList;

public class NodeSuite extends AstNode{
    public ArrayList<NodeStatement> statements = new ArrayList<>();

    public NodeSuite(AstPosition position) {
        super(position);
    }
}
