package frontend.ir;

import utility.error.InternalError;

/**
 * IR Identifier
 * Store both type and value of global variable, local variable, constant value.
 * In addition, jump label is also contained.
 * <p>
 * For convenience, constructor's arguments do not contain genre.
 */
public class IrId {
    public enum Genre {
        GLOBAL, LOCAL, CONSTANT,
        NULL,
        LABEL,
    }

    public Genre genre = null;

    public IrType type = null;

    private static int staticLastRegId = 0;
    public int finalId = -1;    // Local Value

    public String globalName;   // Global Variable or Function

    public int constantValue = 0;   // Constant Integer

    /**
     * @return LLVM label name
     */
    public IrId(Genre genre_) {
        genre = genre_;
    }

    /**
     * @return LLVM register name of local variable
     */
    public IrId(IrType type_) {
        genre = Genre.LOCAL;
        type = type_;
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

    public static void clearIndexCounter() {
        staticLastRegId = 0;
    }

    public void setIndex() {
        if (finalId == -1) finalId = staticLastRegId++;
    }

    public int getLabel() {
        setIndex();
        return finalId;
    }

    @Override
    public String toString() {
        switch (genre) {
            case GLOBAL -> {
                return ("@" + globalName);
            }
            case LOCAL, LABEL -> {
                return ("%" + finalId);
            }
            case CONSTANT -> {
                return Integer.toString(constantValue);
            }
            case NULL -> {
                return "null";
            }
        }
        IrBuilder.throwUnexpectedError();
        return null;
    }


}
