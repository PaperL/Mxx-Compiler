package ast;

import utility.Position;
import utility.scope.BroadScope;

import java.util.ArrayList;

// Stands for the whole program.
public class NodeRoot extends AstNode {
    public ArrayList<NodeProgramSection> programSections = new ArrayList<>();

    public BroadScope scope = new BroadScope();

    public NodeRoot(Position position) {
        super(position);
    }
}
