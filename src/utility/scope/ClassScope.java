package utility.scope;

import java.util.HashMap;

public class ClassScope extends VariableScope{
    private HashMap<String, VariableScope> methods = new HashMap<>();

    public ClassScope(VariableScope parent_, BroadScope globalScope_) {
        super(parent_, globalScope_);
    }
}
