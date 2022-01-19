package ast;

import utility.AstPosition;

public class NodeProgramSection extends AstNode {
    public enum Genre {
        CLASS_DEFINE, FUNCTION_DEFINE, VARIABLE_DEFINE
    }

    public Genre genre = null;

    public NodeClassDefine classDefineNode = null;
    public NodeFunctionDefine functionDefine = null;
    public NodeVariableDefine globalVariableDefine = null;

    public NodeProgramSection(AstPosition position) {
        super(position);
    }

}
