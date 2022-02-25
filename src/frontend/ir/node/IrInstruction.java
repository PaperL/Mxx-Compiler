package frontend.ir.node;

import java.util.LinkedList;

import frontend.ir.IrId;
import frontend.ir.IrType;
import frontend.ir.IrBuilder;
import org.antlr.v4.runtime.misc.Pair;

public class IrInstruction extends IrNode {
    // region BASIC
    public enum Genre {
        COMMENT,
        DECLARE, GLOBAL_VARIABLE,
        ALLOCA,
        LOAD, STORE,
        BRANCH, JUMP,
        CALL, RETURN,
        PHI,
        ARITH,
        GET_ELEMENT_PTR,
        BITCAST,
    }

    public Genre genre = null;
    public IrId insId = null;   // Instruction result

    // Comment
    public String commentInfo = null;
    // Declare
    public String declareInfo = null;
    // Global Variable
    public String globalConstantString = null;
    // Alloca
    public IrId allocaQuantity = null;
    // Load
    public IrId loadAddress = null;
    // Store
    public IrId storeData = null;
    public IrId storeAddress = null;
    // Branch
    public IrId branchCondId = null;
    public IrId branchTrueLabel = null;
    public IrId branchFalseLabel = null;
    // Jump
    public IrId jumpLabel = null;
    // Call
    public String callName = null;
    public LinkedList<IrId> callArguments = null;
    // Return
    public IrId returnValue = null;
    // Phi φ
    // Pair<Value, Label>
    public LinkedList<Pair<IrId, IrId>> phiArgs = null;

    // Arith
    public enum operatorGenre {
        // I32 and STRING
        ADD, GT, LT, GE, LE,
        // I1, I32 and STRING
        EQ, NEQ,
        // I32
        SUB, MUL, DIV, MOD,
        SHIFT_L, SHIFT_R,
        // I32 and I1
        AND, OR, XOR,
    }   // 一元操作符 ~ 和 ! 均可视为 xor -1

    public operatorGenre opGenre = null;
    public IrId arithOperandLeft = null;
    public IrId arithOperandRight = null;

    // getelementptr (Member and Array)
    public IrId objectPtr;
    public LinkedList<IrId> eleIndexes = null;

    // bitcast (Directly cast pointer type)
    public IrId castPtr;
    // endregion

    public IrInstruction(Genre genre_) {
        genre = genre_;
        switch (genre) {
            case COMMENT, DECLARE, BRANCH, JUMP, GET_ELEMENT_PTR -> {
            }
            default -> IrBuilder.throwUnexpectedError();
        }
    }

    /**
     * @param insType Data type of instruction.
     *                (For variable define, just the type of variable)
     */
    public IrInstruction(Genre genre_, IrType insType) {
        genre = genre_;
        switch (genre) {
            case LOAD, STORE, CALL, RETURN,
                    ARITH, PHI, BITCAST -> insId = new IrId(insType);
            case ALLOCA -> insId = new IrId(insType.getPointer());
            // GLOBAL_VARIABLE should use IrInstruction(Genre, IrId)
            default -> IrBuilder.throwUnexpectedError();
        }
    }

    public IrInstruction(Genre genre_, IrId id) {
        genre = genre_;
        insId = id;
    }

    @Override
    public void genIndex() {
        switch (genre) {
            case ALLOCA, LOAD, ARITH, PHI,
                    GET_ELEMENT_PTR, BITCAST -> insId.setIndex();
            case CALL -> {
                if (insId.type.genre != IrType.Genre.VOID)
                    insId.setIndex();
            }
        }
    }

    /**
     * @return LLVM Instruction without end-of-line newline
     */
    @Override
    public String toString() {
        var insType = (insId == null) ? null
                : ((genre == Genre.ARITH) ? arithOperandLeft.type : insId.type);
        switch (genre) {
            case COMMENT -> {
                var strBuilder = new StringBuilder(commentInfo);
                int pos = 0;
                while (pos < strBuilder.length() - 1) {
                    strBuilder.insert(pos, "; ");
                    pos = strBuilder.indexOf("\n", pos);
                    if (pos++ == -1) break;
                }
                return (strBuilder.toString());
            }
            case DECLARE -> {
                return declareInfo;
            }
            case GLOBAL_VARIABLE -> {
                if (insType.arrayLength != 0) {
                    // @__CONSTANT_STR__1 = private unnamed_addr constant [5 x i8] c"abcd\00", align 1
                    return String.format("%s = private unnamed_addr constant [%d x i8] "
                                    + "c\"%s\", align 1 ",
                            insId,
                            insType.arrayLength,
                            globalConstantString);
                } else {
                    // @a = global i32 0
                    return String.format("%s = global %s %s",
                            insId,
                            insType.getNotPointer(),
                            insType.getNotPointer().toZeroInitString());
                }
            }
            case ALLOCA -> {
                // %1 = alloca i32
                // %2 = alloca i1, i32 3    // bool[3]
                var quantityStr = (allocaQuantity == null)
                        ? "" : (", i32 " + allocaQuantity);
                return String.format("%s = alloca %s%s",
                        insId,
                        insType.getNotPointer(),
                        quantityStr);
            }
            case LOAD -> {
                // %1 = load i32, i32* @a
                return String.format("%s = load %s, %s %s",
                        insId,
                        insType,
                        insType.getPointer(),
                        loadAddress);
            }
            case STORE -> {
                // store i32 %1, i32* %2
                return String.format("store %s %s, %s %s",
                        insType,
                        storeData,
                        insType.getPointer(),
                        storeAddress);
            }
            case BRANCH -> {
                // br i1 %1, label %2, label %3
                return String.format("br %s %s, label %s, label %s",
                        branchCondId.type,
                        branchCondId,
                        branchTrueLabel,
                        branchFalseLabel);
            }
            case JUMP -> {
                // br label %1
                return String.format("br label %s", jumpLabel);
            }
            case CALL -> {
                // call void @funcName(i32 %1)
                // %1 = call void @funcName(i32 %2, i1 %3)
                var argStrBuilder = new StringBuilder();
                for (var arg : callArguments) {
                    argStrBuilder.append(arg.type).append(' ')
                            .append(arg).append(", ");
                }
                if (!callArguments.isEmpty())
                    argStrBuilder.delete(argStrBuilder.length() - 2,
                            argStrBuilder.length());    // 删除末尾 ", "
                return ((insType.genre == IrType.Genre.VOID)
                        ? "" : (insId + " = "))
                        + String.format("call %s @%s(%s)",
                        insType,
                        callName,
                        argStrBuilder);
            }
            case RETURN -> {
                if (insType.genre == IrType.Genre.VOID)
                    return "ret " + insType;    // ret void
                else return "ret " + insType + " " + returnValue;   // ret i32 %1
            }
            case PHI -> {
                var argBuilder = new StringBuilder();
                for (var arg : phiArgs)
                    argBuilder.append(String.format(
                            "[ %s, %s ], ", arg.a, arg.b));
                argBuilder.delete(argBuilder.length() - 2, argBuilder.length());
                return String.format("%s = phi %s %s",
                        insId,
                        insType,
                        argBuilder);
            }
            case ARITH -> {
                String opStr = null;
                switch (opGenre) {
                    case ADD -> opStr = "add";
                    case GT -> opStr = "icmp sgt";
                    case LT -> opStr = "icmp slt";
                    case GE -> opStr = "icmp sge";
                    case LE -> opStr = "icmp sle";
                    case EQ -> opStr = "icmp eq";
                    case NEQ -> opStr = "icmp ne";
                    case SUB -> opStr = "sub";
                    case MUL -> opStr = "mul";
                    case DIV -> opStr = "sdiv";
                    case MOD -> opStr = "srem";
                    case SHIFT_L -> opStr = "shl";
                    case SHIFT_R -> opStr = "ashr";
                    case AND -> opStr = "and";
                    case OR -> opStr = "or";
                    case XOR -> opStr = "xor";
                }
                // %1 = add i32 %2, %3
                return String.format("%s = %s %s %s, %s",
                        insId, opStr, insType,
                        arithOperandLeft,
                        arithOperandRight);
            }
            case GET_ELEMENT_PTR -> {
                // %2 = getelementptr %class.CC, %class.CC* %1, i32 0, i32 0
                var argBuilder = new StringBuilder();
                for (var id : eleIndexes)
                    argBuilder.append(String.format(
                            "%s %s, ", id.type, id));
                argBuilder.delete(argBuilder.length() - 2, argBuilder.length());
                return String.format("%s = getelementptr %s, %s %s, %s",
                        insId,
                        objectPtr.type.getNotPointer(),
                        objectPtr.type, objectPtr,
                        argBuilder);
            }
            case BITCAST -> {
                // %2 = bitcast i8* %1 to i32*
                return String.format("%s = bitcast %s %s to %s",
                        insId, castPtr.type, castPtr, insType);
            }
        }
        IrBuilder.throwTodoError("NO RETURN");
        return null;
    }
}
