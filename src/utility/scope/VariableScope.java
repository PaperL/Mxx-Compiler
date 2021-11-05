package utility.scope;

import utility.Position;
import utility.Type;
import utility.error.SemanticError;

import java.util.HashMap;
import java.util.Objects;

public class VariableScope {
    public final BroadScope globalScope;
    public final HashMap<String, Type> variables = new HashMap<>();

    public VariableScope(final BroadScope globalScope_) {
        globalScope = Objects.requireNonNullElseGet(globalScope_, () -> (BroadScope) this);
    }

    // Defines local variable with illegal type and redefinition check
    public void defineVariable(String name, Type type, Position position) {
        globalScope.checkTypeExist(type, position);
        if (globalScope.classes.containsKey(name))
            throw new SemanticError("Variable '" + name + "' has the same name of a class", position);
        if (variables.containsKey(name))
            throw new SemanticError("Redefinition of variable '" + name + "'", position);
        variables.put(name, type);
    }

    public Type getVariableType(String name, Position position) {
        // ! 垃圾补丁
        var returnType = variables.getOrDefault(name, null);
        if (returnType != null) returnType.isVariable = true;
        return variables.getOrDefault(name, null);
    }
}
