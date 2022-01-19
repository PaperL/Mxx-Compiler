package utility.scope;

import ast.NodeFunctionDefine;
import utility.AstPosition;
import utility.error.SemanticError;

import java.util.HashMap;

// Stores class member variables and methods information.
public class ClassScope extends VariableScope {
    // VariableScope: HashMap<String, Type> variables
    public HashMap<String, FunctionScope> methods = new HashMap<>();

    public ClassScope(BroadScope globalScope_) {
        super(globalScope_);
    }

    public boolean defineMethod(NodeFunctionDefine node) {
        var functionScope = new FunctionScope(globalScope, node);
        methods.put(node.name, functionScope);
        return (functionScope.returnType == null);
    }

    public FunctionScope getMethod(String name, AstPosition position, boolean throwable) {
        if (!methods.containsKey(name))
            if (throwable) throw new SemanticError("Undefined methods '" + name + "'", position);
            else return null;
        return methods.get(name);
    }
}
