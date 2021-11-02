package utility.scope;

import ast.NodeFunctionDefine;
import utility.Position;
import utility.error.SemanticError;

import java.util.HashMap;

// Stores class member variables and methods information.
public class ClassScope extends VariableScope {
    // VariableScope: HashMap<String, Type> variables
    public HashMap<String, FunctionScope> methods;

    public ClassScope(BroadScope globalScope_) {
        super(globalScope_);
    }

    public void defineMethod(NodeFunctionDefine node) {
        methods.put(node.name, new FunctionScope(globalScope, node));
    }

    public FunctionScope getMethod(String name, Position position) {
        if (!methods.containsKey(name))
            throw new SemanticError("Undefined methods '" + name + "'", position);
        return methods.get(name);
    }
}
