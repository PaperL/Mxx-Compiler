package frontend;

import ast.*;
import utility.scope.VariableScope;

public class SymbolCollector implements AstVisitor {
    @Override
    public void visit(NodeRoot node) {
        for (var son : node.programSections)
            visit(son);
    }

    @Override
    public void visit(NodeProgramSection node) {

    }

    @Override
    public void visit(NodeClassDefine node) {

    }

    @Override
    public void visit(NodeFunctionDefine node) {

    }

    @Override
    public void visit(NodeArgumentList node) {

    }

    @Override
    public void visit(NodeVariableDefine node) {

    }

    @Override
    public void visit(NodeVariableTerm node) {

    }

    @Override
    public void visit(NodeType node) {

    }

    @Override
    public void visit(NodeBracket node) {

    }

    @Override
    public void visit(NodeSuite node) {

    }

    @Override
    public void visit(NodeStatement node) {

    }

    @Override
    public void visit(NodeExpression node) {

    }

    @Override
    public void visit(NodeExpressionList node) {

    }

    @Override
    public void visit(NodeAtom node) {

    }
}
