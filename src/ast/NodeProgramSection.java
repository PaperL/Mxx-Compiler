package ast;

import utility.Position;

public class NodeProgramSection extends AstNode {
    public enum Genre {
        CLASS_DEFINE, FUNCTION_DEFINE, VARIABLE_DEFINE;
    }

    public Genre genre = null;

    public NodeClassDefine classDefineNode = null;
    public NodeFunctionDefine functionDefine = null;
    public NodeVariableDefine globalVariableDefine = null;

    public NodeProgramSection(Position position) {
        super(position);
    }

}
