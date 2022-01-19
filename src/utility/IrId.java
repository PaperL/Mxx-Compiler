package utility;

public class IrId {
    public enum Genre {
        GLOBAL, LOCAL, CONSTANT,
    }

    public Genre genre = null;

    public IrType type = null;

    private static int staticCnt = 0; // ? 这真的用得上吗
    public int staticId = 0;    // Local Value

    public String globalName;   // Global Variable

    public int outputId = -1;   // Local Variable

    public int constantValue = 0;   // Constant Integer

    /**
     * @return LLVM register name of local variable
     */
    public IrId(IrType type_) {
        genre = Genre.LOCAL;
        type = type_;
        staticId = staticCnt++;
    }

    /**
     * @return LLVM register name of global variable
     */
    public IrId(IrType type_, String name) {
        genre = Genre.GLOBAL;
        type = type_;
        globalName = name;
    }

    /**
     * @return Constant integer in LLVM instruction
     */
    public IrId(IrType type_, int value) {
        genre = Genre.CONSTANT;
        type = type_;
        constantValue = value;
    }

    public String toString() {
        // todo
    }
}
