package utility.scope;

import ast.NodeFunctionDefine;
import utility.Position;
import utility.error.SemanticError;

import java.util.HashMap;

// Manages name of global class, function and variable.
public class BroadScope extends VariableScope {
    // VariableScope: HashMap<String, Type> variables
    public HashMap<String, ClassScope> classes = new HashMap<>();
    public HashMap<String, FunctionScope> functions = new HashMap<>();

    public BroadScope() {
        super(null);
    }

    public void DefineClass(String name, Position position) {
        if (classes.containsKey(name))
            throw new SemanticError("Redefinition of class '" + name + "'", position);
        classes.put(name, new ClassScope(globalScope));
    }

    public ClassScope GetClass(String name, Position position) {
        if (!classes.containsKey(name))
            throw new SemanticError("Undefined class '" + name + "'", position);
        return classes.get(name);
    }

    public void DefineFunction(NodeFunctionDefine node) {
        functions.put(node.name, new FunctionScope(globalScope, node));
    }
}
