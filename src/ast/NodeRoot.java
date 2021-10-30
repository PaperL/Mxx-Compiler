package ast;

import utility.Position;

import java.util.ArrayList;

// Stands for the whole program.
public class NodeRoot extends AstNode {
    public ArrayList<NodeProgramSection> programSections = new ArrayList<>();

    public NodeRoot(Position position) {
        super(position);
    }
}
