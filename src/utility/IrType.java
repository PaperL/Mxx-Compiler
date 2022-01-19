package utility;

import ast.NodeType;
import utility.error.InternalError;

public class IrType {
    public final String STRING_CLASS_NAME = "__STRING";

    public enum Genre {
        I1, I8, I32, VOID, COMPOSITE
        // ? ARRAY
    }

    public Genre genre = null
    public boolean pointer = false;
    // todo public int dimension = 0;
    public String className = null;

    public IrType(Genre genre_) {
        genre = genre_;
    }

    public IrType(NodeType astType) {
        switch (astType.type.genre) {
            case BOOLEAN -> genre = Genre.I1;
            case INTEGER -> genre = Genre.I32;
            case STRING-> {
                genre = Genre.COMPOSITE;
                className = STRING_CLASS_NAME;
            }
            case CLASS_NAME -> {
                genre = Genre.COMPOSITE;
                className = astType.type.className;
            }
            case VOID -> genre = Genre.VOID;
            default -> throw new InternalError(
                    "IR",
                    "Unexpected AstType when Constructing IrType");
        }
    }

    /**
     * Get clone of an IrType object, and turn it to pointer type.
     * @return Pointer type clone
     */
    public IrType getPointer() {
        var ret = new IrType(genre);
        ret.pointer = true;
        return ret;
    }

    public String toString(){
        // todo
    }
}
