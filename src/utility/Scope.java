package utility;

import java.util.HashMap;

public class Scope {

    private HashMap<String, Type> variables;
    private final Scope parent;

    public Scope(final Scope parent_) {
        parent = parent_;
        variables = new HashMap<>();
    }
}
