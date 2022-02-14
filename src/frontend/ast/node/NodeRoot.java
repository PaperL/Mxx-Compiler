package frontend.ast.node;

import java.util.ArrayList;
import frontend.ast.AstPosition;

/**
 * Stands for the whole program.
 */
public class NodeRoot extends AstNode {
    public ArrayList<NodeProgramSection> programSections = new ArrayList<>();

    public NodeRoot(AstPosition position) {
        super(position);
    }
}