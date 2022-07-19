package frontend.ast.scope;

import frontend.ast.AstPosition;
import frontend.ast.AstType;
import frontend.ast.node.NodeFunctionDefine;
import utility.error.SemanticError;

import java.util.HashMap;

/**
 * Manages name of global class, function and variable.
 */
public class BroadScope extends VariableScope {
    // VariableScope: HashMap<String, Type> variables
    public HashMap<String, ClassScope> classes = new HashMap<>();
    public HashMap<String, FunctionScope> functions = new HashMap<>();

    public BroadScope() {
        super(null);
    }

    public void defineClass(String name, AstPosition position) {
        if (classes.containsKey(name))
            throw new SemanticError("Redefinition of class '" + name + "'", position);
        classes.put(name, new ClassScope(globalScope));
    }

    public ClassScope getClass(String name, AstPosition position) {
        if (!classes.containsKey(name))
            throw new SemanticError("Undefined class '" + name + "'", position);
        return classes.get(name);
    }

    public void defineFunction(NodeFunctionDefine node) {
        if (classes.containsKey(node.name))
            throw new SemanticError(
                    "Function '" + node.name + "' has the same name of a class",
                    node.position
            );
        functions.put(node.name, new FunctionScope(globalScope, node));
    }

    public FunctionScope getFunction(String name, AstPosition position, boolean throwable) {
        if (!functions.containsKey(name))
            if (throwable) throw new SemanticError("Undefined function '" + name + "'", position);
            else return null;
        return functions.get(name);
    }

    public void checkTypeExist(AstType type, AstPosition position) {
        if (type.genre == AstType.Genre.CLASS_NAME && !classes.containsKey(type.className))
            throw new SemanticError("Undefined class '" + type.className + "'", position);
    }
}
