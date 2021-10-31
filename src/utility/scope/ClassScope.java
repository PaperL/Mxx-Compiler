package utility.scope;

import ast.NodeFunctionDefine;
import utility.Position;
import utility.error.SemanticError;

import java.util.HashMap;

public class ClassScope extends VariableScope {
    // VariableScope: HashMap<String, Type> variables
    public HashMap<String, FunctionScope> methods;

    public ClassScope(BroadScope globalScope_) {
        super(globalScope_);
    }

    public void DefineMethod(NodeFunctionDefine node) {
        methods.put(node.name, new FunctionScope(globalScope, node));
    }
}
