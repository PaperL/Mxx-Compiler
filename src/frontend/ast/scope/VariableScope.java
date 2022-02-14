package frontend.ast.scope;

import java.util.Objects;
import java.util.HashMap;
import utility.error.SemanticError;
import frontend.ast.AstPosition;
import frontend.ast.AstType;

public class VariableScope {
    public final BroadScope globalScope;
    public final HashMap<String, AstType> variables = new HashMap<>();

    public VariableScope(final BroadScope globalScope_) {
        globalScope = Objects.requireNonNullElseGet(globalScope_, () -> (BroadScope) this);
    }

    // Defines local variable with illegal type and redefinition check
    public void defineVariable(String name, AstType type, AstPosition position) {
        globalScope.checkTypeExist(type, position);
        if (globalScope.classes.containsKey(name))
            throw new SemanticError("Variable '" + name + "' has the same name of a class", position);
        if (variables.containsKey(name))
            throw new SemanticError("Redefinition of variable '" + name + "'", position);
        variables.put(name, type);
    }

    public AstType getVariableType(String name, AstPosition position) {
        // ! 垃圾补丁
        var returnType = variables.getOrDefault(name, null);
        if (returnType != null) returnType.isVariable = true;
        return variables.getOrDefault(name, null);
    }
}
