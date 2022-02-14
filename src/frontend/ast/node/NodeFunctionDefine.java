package frontend.ast.node;

import frontend.ast.AstPosition;

public class NodeFunctionDefine extends AstNode {
    public NodeType type = null;
    public String name = null;
    public NodeArgumentList argumentList = null;
    public NodeSuite suite = null;

    public NodeFunctionDefine(AstPosition position) {
        super(position);
    }

}
