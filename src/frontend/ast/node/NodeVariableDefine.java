package frontend.ast.node;

import java.util.ArrayList;
import frontend.ast.AstPosition;

public class NodeVariableDefine extends AstNode {
    public NodeType type = null;
    public ArrayList<NodeVariableTerm> variableTerms = new ArrayList<>();

    public NodeVariableDefine(AstPosition position) {
        super(position);
    }
}
