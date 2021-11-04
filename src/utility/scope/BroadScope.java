package utility.scope;

import ast.NodeFunctionDefine;
import utility.Position;
import utility.Type;
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

    public void defineClass(String name, Position position) {
        if (classes.containsKey(name))
            throw new SemanticError("Redefinition of class '" + name + "'", position);
        classes.put(name, new ClassScope(globalScope));
    }

    public ClassScope getClass(String name, Position position) {
        if (!classes.containsKey(name))
            throw new SemanticError("Undefined class '" + name + "'", position);
        return classes.get(name);
    }

    public void defineFunction(NodeFunctionDefine node) {
        functions.put(node.name, new FunctionScope(globalScope, node));
    }

    public FunctionScope getFunction(String name, Position position, boolean throwable) {
        if (!functions.containsKey(name))
            if (throwable) throw new SemanticError("Undefined function '" + name + "'", position);
            else return null;
        return functions.get(name);
    }

    public void checkTypeExist(Type type, Position position) {
        if (type.genre == Type.Genre.CLASS_NAME && !classes.containsKey(type.className))
            throw new SemanticError("Undefined class '" + type.className + "'", position);
    }
}
