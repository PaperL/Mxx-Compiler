package utility;

import ast.NodeType;
import frontend.IrBuilder;
import utility.error.InternalError;

import java.util.Objects;

public class IrType implements Cloneable {
    public final String STRING_CLASS_NAME = "__STRING";

    public enum Genre {
        I1, I32, VOID, COMPOSITE
        // ? ARRAY
    }
    // No I8

    public Genre genre = null;
    public int dimension = 0;   // 多级指针
    public String className = null;

    public IrType(Genre genre_) {
        genre = genre_;
    }

    public IrType(NodeType astType) {
        switch (astType.type.genre) {
            case BOOLEAN -> genre = Genre.I1;
            case INTEGER -> genre = Genre.I32;
            case STRING -> {
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
     *
     * @return Pointer type clone
     */
    public IrType getPointer() {
        var ret = this.clone();
        ret.dimension++;
        return ret;
    }

    /**
     * Get clone of an IrType object, and turn it to not pointer type.
     *
     * @return Not pointer type clone
     */
    public IrType getNotPointer() {
        var ret = this.clone();
        if (dimension == 0) throw new InternalError(
                "IR",
                "Current dimension is 0 in IrType.getNotPointer()");
        ret.dimension = dimension - 1;
        return ret;
    }

    public String toString() {
        StringBuilder typeName = new StringBuilder();
        switch (genre) {
            case I1 -> {
                typeName = new StringBuilder("i1");
            }
            case I32 -> {
                typeName = new StringBuilder("i32");
            }
            case VOID -> {
                typeName = new StringBuilder("void");
            }
            case COMPOSITE -> {
                typeName = new StringBuilder(className);
            }
        }
        typeName.append("*".repeat(dimension));
        return typeName.toString();
    }

    public String toZeroInitString() {
        switch (genre) {
            case I1, I32 -> {
                return "0";
            }
            case COMPOSITE -> {
                IrBuilder.throwTodoError("toZeroInitString()");
                return null;
            }
            default -> throw new InternalError(
                    "IR",
                    "Unexpected AstType at toZeroInitString()");
        }
    }

    @Override
    public IrType clone() {
        try {
            IrType clone = (IrType) super.clone();
            clone.genre = genre;
            clone.dimension = dimension;
            clone.className = className;
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IrType irType = (IrType) o;
        return dimension == irType.dimension && genre == irType.genre && Objects.equals(className, irType.className);
    }

    @Override
    public int hashCode() {
        return Objects.hash(STRING_CLASS_NAME, genre, dimension, className);
    }
}
