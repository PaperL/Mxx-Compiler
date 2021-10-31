package frontend;

import ast.*;
import utility.scope.BroadScope;

import java.util.Stack;

public class SemanticChecker implements AstVisitor {
    public Stack<BroadScope> scopeStack;
    public BroadScope globalScope;

    public SemanticChecker(BroadScope globalScope_) {
        globalScope = globalScope_;
        scopeStack = new Stack<>();
        scopeStack.push(globalScope_);
    }

    @Override
    public void visit(NodeRoot node) {
        for (var section : node.programSections) visit(section);
    }

    @Override
    public void visit(NodeProgramSection node) {
        switch (node.genre) {
            case CLASS_DEFINE -> visit(node.classDefineNode);
            case FUNCTION_DEFINE -> visit(node.functionDefine);
            case VARIABLE_DEFINE -> visit(node.globalVariableDefine);
        }
    }

    @Override
    public void visit(NodeClassDefine node) {
        // Classes have been imported to global scope
        for (var methodDefine : node.methodDefines) visit(methodDefine);
        for (var variableDefine : node.variableDefines) visit(variableDefine);
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
