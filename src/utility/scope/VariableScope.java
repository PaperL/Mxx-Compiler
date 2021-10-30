package utility.scope;

import utility.Type;

import java.util.HashMap;

public class VariableScope {
    private HashMap<String, Type> variables = new HashMap<>();
    private final VariableScope parent, globalScope;

    public VariableScope(final VariableScope parent_, final BroadScope globalScope_) {
        parent = parent_;
        globalScope = globalScope_;
    }
}
