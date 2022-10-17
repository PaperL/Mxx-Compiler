package utility;

public class Constant {
    // region Frontend
    // * Compiler Environment
    public static CmdArgument cmdArgs = null;
    public static String SOURCE_FILENAME = null;
    public static int POINTER_SIZE = 4;
    // Be 64 when output llvm IR for Clang
    // Must be 32 for assembly
    public static int STACK_SIZE = 2 * 1024 * 1024;
    // * IR Internal naming prefix
    public static final String GLOBAL_VARIABLE_PREFIX = "__VAR__";
    public static final String CONSTANT_STRING_PREFIX = "__CONSTANT_STR__";
    public static final String FUNCTION_PREFIX = "__FUNC__";
    public static final String CLASS_CONSTRUCTOR_PREFIX = "__CONSTRUCTOR__";
    public static final String CLASS_PREFIX = "__CLAS__", METHOD_PREFIX = "__MTHD__";
    // * AST Internal name
    public static final String builtInStringClassName = "__STRING__";
    public static final String builtInArrayClassName = "__ARRAY__";
    // endregion
}
