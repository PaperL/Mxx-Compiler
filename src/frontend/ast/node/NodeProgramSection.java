package frontend.ast.node;

import frontend.ast.AstPosition;

public class NodeProgramSection extends AstNode {
    public Genre genre = null;
    public NodeClassDefine classDefineNode = null;
    public NodeFunctionDefine functionDefine = null;
    public NodeVariableDefine globalVariableDefine = null;

    public NodeProgramSection(AstPosition position) {
        super(position);
    }

    public enum Genre {
        CLASS_DEFINE, FUNCTION_DEFINE, VARIABLE_DEFINE
    }

}
