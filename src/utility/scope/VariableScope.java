package utility.scope;

import utility.Position;
import utility.Type;
import utility.error.SemanticError;

import java.util.HashMap;
import java.util.Objects;

public class VariableScope {
    final public BroadScope globalScope;
    public HashMap<String, Type> variables = new HashMap<>();

    public VariableScope(final BroadScope globalScope_) {
        globalScope = Objects.requireNonNullElseGet(globalScope_, () -> (BroadScope) this);
    }

    public void DefineVariable(String name, Type type, Position position) {
        if (variables.containsKey(name))
            throw new SemanticError("Redefinition of variable '" + name + "'", position);
        variables.put(name, new Type());
    }

    public Type GetVariableType(String name, Position position) {
        if (variables.containsKey(name))
            return variables.get(name);
        else throw new SemanticError("Cannot find variable '" + name + "'", position);
    }
}
