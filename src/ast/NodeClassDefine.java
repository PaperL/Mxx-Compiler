package ast;

import utility.Position;

import java.util.ArrayList;

public class NodeClassDefine extends AstNode {
    public String name = null;
    public ArrayList<NodeFunctionDefine> methodDefines = new ArrayList<>();
    public ArrayList<NodeVariableDefine> variableDefines = new ArrayList<>();

    public NodeClassDefine(Position position) {
        super(position);
    }
}
