package utility;

public class Constant {
    // AST Internal name
    public static final String builtInStringClassName = "__STRING__";
    public static final String builtInArrayClassName = "__ARRAY__";
    // IR Internal naming prefix
    public static final String GLOBAL_VARIABLE_PREFIX = "__VAR__";
    public static final String CONSTANT_STRING_PREFIX = "__CONSTANT_STR__";
    public static final String FUNCTION_PREFIX = "__FUNC__";
    public static final String CLASS_CONSTRUCTOR_PREFIX = "__CONSTRUCTOR__";
    public static final String CLASS_PREFIX = "__CLAS__", METHOD_PREFIX = "__MTHD__";
    // region Frontend
    public static CmdArgument cmdArgs = null;
    // endregion
}
