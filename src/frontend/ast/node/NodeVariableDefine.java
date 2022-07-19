package frontend.ast.node;

import frontend.ast.AstPosition;

import java.util.ArrayList;

public class NodeVariableDefine extends AstNode {
    public NodeType type = null;
    public ArrayList<NodeVariableTerm> variableTerms = new ArrayList<>();

    public NodeVariableDefine(AstPosition position) {
        super(position);
    }
}
