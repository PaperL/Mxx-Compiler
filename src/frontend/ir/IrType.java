package frontend.ir;

import frontend.ast.node.NodeType;
import frontend.ir.node.IrClass;
import utility.error.InternalError;

import java.util.Objects;

public class IrType implements Cloneable {
    public final String STRING_CLASS_NAME = "__STRING";
    public Genre genre = null;
    // I8 only used for string
    public int dimension = 0;   // 多级指针
    public IrClass clas = null;
    // Constant string
    public int arrayLength = -1;

    public IrType(Genre genre_) {
        genre = genre_;
    }

    public IrType(Genre genre_, int arrayLen) {
        genre = genre_;
        arrayLength = arrayLen;
        dimension = 0;
    }

    public IrType(IrClass clas_) {
        genre = Genre.COMPOSITE;
        clas = clas_;
        dimension = 1;
    }

    /**
     * * For class, type is class pointer
     * * Object is just reference
     */
    public IrType(NodeType astType) {
        switch (astType.type.genre) {
            case BOOLEAN -> genre = Genre.I1;
            case INTEGER -> genre = Genre.I32;
            case STRING -> {
                genre = Genre.I8;
                dimension++;
            }
            case CLASS_NAME -> {
                genre = Genre.COMPOSITE;
                clas = IrBuilder.irRoot.classes
                        .get(astType.type.className);
                dimension++;
            }
            case VOID -> genre = Genre.VOID;
            default -> throw new InternalError(
                    "IR",
                    "Unexpected AstType when Constructing IrType");
        }
        dimension += astType.type.dimension;
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

    public boolean isArray() {
        return (dimension > (genre == Genre.COMPOSITE ? 1 : 0));
    }

    public int sizeof() {
        int size = 0;
        if (genre == Genre.COMPOSITE) {
            if (dimension != 1) size = 4;
            else {
                for (var field : clas.fields.values())
                    size += field.b.sizeof();
            }
        } else if (dimension != 0) size = 4;
        else {
            switch (genre) {
                case I1 -> size = 1;
                case I8 -> size = 1;
                case I32 -> size = 4;
                default -> IrBuilder.throwUnexpectedError();
            }
        }
        return size;
    }

    public String toString() {
        StringBuilder typeName = new StringBuilder();
        switch (genre) {
            case I1 -> typeName.append("i1");
            case I8 -> typeName.append("i8");
            case I32 -> typeName.append("i32");
            case VOID -> typeName.append("void");
            case COMPOSITE -> typeName.append("%class.").append(clas.name);
        }
        if (dimension < 0) IrBuilder.throwUnexpectedError();
        else if (arrayLength == -1) // Pointer instead of Array
            typeName.append("*".repeat(dimension));
        else // ! Only i8 Array
            return String.format("[%d x i8]", arrayLength)
                    + "*".repeat(dimension);
        return typeName.toString();
    }

    public String toZeroInitString() {
        if (dimension != 0) return "null";
        switch (genre) {
            case I1, I8, I32 -> {
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

    public enum Genre {
        I1, I8, I32, VOID, COMPOSITE
    }
}
