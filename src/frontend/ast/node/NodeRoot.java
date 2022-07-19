package frontend.ast.node;

import frontend.ast.AstPosition;

import java.util.ArrayList;

/**
 * Stands for the whole program.
 */
public class NodeRoot extends AstNode {
    public ArrayList<NodeProgramSection> programSections = new ArrayList<>();

    public NodeRoot(AstPosition position) {
        super(position);
    }
}