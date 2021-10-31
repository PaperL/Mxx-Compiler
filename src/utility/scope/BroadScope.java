package utility.scope;

import java.util.HashMap;

// Manages name of global class, function and variable.
public class BroadScope extends VariableScope {
    private HashMap<String, ClassScope> classes = new HashMap<>();
    private HashMap<String, VariableScope> functions = new HashMap<>();

    public BroadScope() {
        super(null, null);
    }
}
