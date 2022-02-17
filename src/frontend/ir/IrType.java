package frontend.ir;

import frontend.ast.node.NodeType;
import frontend.ir.node.IrClass;
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
    public IrClass clas = null;

    public IrType(Genre genre_) {
        genre = genre_;
    }

    public IrType(IrClass clas_) {
        genre = Genre.COMPOSITE;
        clas = clas_;
    }

    public IrType(NodeType astType) {
        switch (astType.type.genre) {
            case BOOLEAN -> genre = Genre.I1;
            case INTEGER -> genre = Genre.I32;
            case STRING -> {
                genre = Genre.COMPOSITE;
                // todo
                IrBuilder.throwTodoError("String type");
            }
            case CLASS_NAME -> {
                genre = Genre.COMPOSITE;
                clas = IrBuilder.irRoot.classes
                        .get(astType.type.className);
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
            case I1 -> typeName.append("i1");
            case I32 -> typeName.append("i32");
            case VOID -> typeName.append("void");
            case COMPOSITE -> typeName.append("%class.").append(clas.name);
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
                return "zeroinitializer";
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
            clone.clas = clas;
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
        return dimension == irType.dimension
                && genre == irType.genre
                && Objects.equals(clas, irType.clas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(STRING_CLASS_NAME, genre, dimension, clas);
    }
}
