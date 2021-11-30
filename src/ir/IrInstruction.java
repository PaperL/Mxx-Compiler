package ir;

public class IrInstruction {
    public enum Genre {
        CALL, ARITH, NEW, ASSIGN,TYPE_DEFINE,GLOBAL_VARIABLE
    }

    public Genre genre = null;
    public boolean isLocal = false;
    public String name = null;

    public IrInstruction(Genre genre_, boolean isLocal_, String name_) {
        genre = genre_;
        isLocal = isLocal_;
        name = name_;
    }
}
