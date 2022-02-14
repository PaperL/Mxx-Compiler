package frontend.ir.node;

import java.util.LinkedList;
import frontend.ir.IrId;
import frontend.ir.IrType;
import frontend.ir.IrBuilder;

public class IrInstruction extends IrNode {
    public enum Genre {
        DECLARE, GLOBAL_VARIABLE, TYPE,
        ALLOCA,
        LOAD, STORE,
        BRANCH, JUMP,
        CALL, RETURN,
        ARITH,
    }

    public Genre genre = null;
    public IrId insId = null;

    // Declare
    public String declareInfo = null;
    // Global Variable
    // todo Type (for Class Member Variable)
    // Alloca
    // Load
    public IrId loadAddress = null;
    // Store
    public IrId storeData = null;
    public IrId storeAddress = null;
    // Branch
    public IrId branchCondId = null;
    public IrBlock branchTrueBlock = null;
    public IrBlock branchFalseBlock = null;
    // Jump
    public IrId jumpLabel = null;
    // Call
    public String callName = null;
    public LinkedList<IrId> callArguments = null;
    // Return
    public IrId returnValue = null;

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
    }
    // 一元操作符 ~ 和 ! 均可视为 xor -1

    // Arith
    public operatorGenre opGenre = null;
    public IrId arithOperandLeft = null;
    public IrId arithOperandRight = null;

    public IrInstruction(Genre genre_) {
        genre = genre_;
        switch (genre) {
            case DECLARE, TYPE, BRANCH, JUMP -> {
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
            case LOAD, STORE, CALL,
                    RETURN, ARITH -> insId = new IrId(insType);
            case ALLOCA -> insId = new IrId(insType.getPointer());
            // GLOBAL_VARIABLE should use IrInstruction(Genre, IrId)
            default -> IrBuilder.throwUnexpectedError();
        }
    }

    public IrInstruction(Genre genre_, IrId id) {
        genre = genre_;
        insId = id;
    }

    /**
     * @return LLVM Instruction without end-of-line newline
     */
    @Override
    public String toString() {
        var insType = ((insId == null) ? null : insId.type);
        switch (genre) {
            case DECLARE -> {
                return declareInfo;
            }
            case GLOBAL_VARIABLE -> {
                // @a = global i32 0
                return String.format("%s = global %s %s",
                        insId,
                        insType.getNotPointer(),
                        insType.getNotPointer().toZeroInitString());
            }
            case TYPE -> {
                // todo
                IrBuilder.throwTodoError("ins_1");
            }
            case ALLOCA -> {
                // %1 = alloca i32
                return String.format("%s = alloca %s",
                        insId,
                        insType.getNotPointer());
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
                        branchTrueBlock.label,
                        branchFalseBlock.label);
            }
            case JUMP -> {
                // br label %1
                return String.format("br label %s", jumpLabel);
            }
            case CALL -> {
                // todo
                IrBuilder.throwTodoError("CALL");
            }
            case RETURN -> {
                if (insType.genre == IrType.Genre.VOID)
                    return "ret " + insType;    // ret void
                else return "ret " + insType + " " + returnValue;   // ret i32 %1
            }
            case ARITH -> {
                var opStr = "";
                // todo
                switch (opGenre) {
                    case ADD -> {
                        opStr = "add";
                    }
                    case GT -> {
                    }
                    case LT -> {
                    }
                    case GE -> {
                    }
                    case LE -> {
                    }
                    case EQ -> {
                    }
                    case NEQ -> {
                    }
                    case SUB -> {
                    }
                    case MUL -> {
                    }
                    case DIV -> {
                    }
                    case MOD -> {
                    }
                    case SHIFT_L -> {
                    }
                    case SHIFT_R -> {
                    }
                    case AND -> {
                    }
                    case OR -> {
                    }
                    case XOR -> {
                    }
                }
                // %1 = add i32 %2, %3
                return String.format("%s = %s %s %s, %s",
                        insId, opStr, insType,
                        arithOperandLeft,
                        arithOperandRight);
            }
        }
        IrBuilder.throwTodoError("NO RETURN");
        return null;
    }
}
