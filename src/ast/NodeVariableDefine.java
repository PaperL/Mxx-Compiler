package ast;

import utility.Position;

import java.util.ArrayList;

public class NodeVariableDefine extends AstNode {
    public NodeType type = null;
    public ArrayList<NodeVariableTerm> variableTerms = new ArrayList<>();

    public NodeVariableDefine(Position position) {
        super(position);
    }
}
