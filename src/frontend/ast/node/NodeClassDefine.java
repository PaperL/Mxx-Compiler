package frontend.ast.node;

import java.util.ArrayList;
import frontend.ast.AstPosition;

public class NodeClassDefine extends AstNode {
    public String name = null;
    public ArrayList<NodeFunctionDefine> methodDefines = new ArrayList<>();
    public ArrayList<NodeVariableDefine> variableDefines = new ArrayList<>();

    public NodeClassDefine(AstPosition position) {
        super(position);
    }
}
