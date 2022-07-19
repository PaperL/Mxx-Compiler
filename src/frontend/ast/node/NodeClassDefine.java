package frontend.ast.node;

import frontend.ast.AstPosition;

import java.util.ArrayList;

public class NodeClassDefine extends AstNode {
    public boolean builtIn = false;
    public String name = null;
    public ArrayList<NodeFunctionDefine> methodDefines = new ArrayList<>();
    public ArrayList<NodeVariableDefine> variableDefines = new ArrayList<>();

    public NodeClassDefine(AstPosition position) {
        super(position);
    }
}
