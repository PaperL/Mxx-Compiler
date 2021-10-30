package utility;

import java.util.HashMap;

public class Type {
    public enum TypeEnum {
        BOOL, INT, VOID, STRING;
    }

    private TypeEnum type;

    public Type(TypeEnum type_) {
        type = type_;
    }

    public TypeEnum getType() {
        return type;
    }

}
