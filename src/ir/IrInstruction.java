package ir;

import frontend.IrBuilder;
import utility.IrId;
import utility.IrType;
import utility.error.InternalError;

import java.util.LinkedList;

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
        SHILFT_L, SHIFT_R,
        // I32 and I1
        AND, OR, XOR, NOT,
    }

    // Arith
    public operatorGenre opGenre = null;
    public IrId arithOperandLeft = null;
    public IrId arithOperandRight = null;

    public IrInstruction(Genre genre_) {
        genre = genre_;
        switch (genre) {
            case DECLARE, TYPE, BRANCH, JUMP -> {
            }
            default -> throw new InternalError(
                    "IR",
                    "Wrong IrInstruction construction (1)");
        }
    }

    /**
     * @param insType Data type of instruction
     */
    public IrInstruction(Genre genre_, IrType insType) {
        genre = genre_;
        switch (genre) {
            case GLOBAL_VARIABLE, LOAD, STORE, CALL,
                    RETURN, ARITH -> insId = new IrId(insType);
            case ALLOCA -> insId = new IrId(insType.getPointer());
            default -> throw new InternalError(
                    "IR",
                    "Wrong IrInstruction construction (2)");
        }
    }

    public IrInstruction(Genre genre_, IrId id) {
        genre = genre_;
        insId = id;
    }

    @Override
    public String toString() {
        var insType = ((insId == null) ? null : insId.type);
        switch (genre) {
            case DECLARE -> {
                return declareInfo;
            }
            case GLOBAL_VARIABLE -> {
                // @a = global i32 1
                return insId + " = global "
                        + insType + " "
                        + insType.toZeroInitString();
            }
            case TYPE -> {
                // todo
                IrBuilder.throwTodoError("ins_1");
            }
            case ALLOCA -> {
                // %1 = alloca i32
                return insId + " = alloca " + insType.getNotPointer();
            }
            case LOAD -> {
                // %1 = load i32, i32* @a
                return insId + " = load "
                        + insType + ", "
                        + insType.getPointer() + " "
                        + loadAddress;
            }
            case STORE -> {
                // store i32 %1, i32* %2
                return "store " + insType + " "
                        + storeData + ", "
                        + insType.getPointer() + " "
                        + storeAddress;
            }
            case BRANCH -> {
                // br i1 %1, label %2, label %3
                return "br " + branchCondId.type + " "  // branchCondId.type must be i1
                        + branchCondId
                        + ", label " + branchTrueBlock.label
                        + ", label " + branchFalseBlock.label;
            }
            case JUMP -> {
                // br label %1
                return "br label " + jumpLabel;
            }
            case CALL -> {
                // todo
                IrBuilder.throwTodoError("ins_2");
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
                    case SHILFT_L -> {
                    }
                    case SHIFT_R -> {
                    }
                    case AND -> {
                    }
                    case OR -> {
                    }
                    case XOR -> {
                    }
                    case NOT -> {
                    }
                }
                return insId + " = "
                        + opStr + " " + insType + " "
                        + arithOperandLeft + ", "
                        + arithOperandRight;
            }
        }
        IrBuilder.throwTodoError("ins_3");
        return null;
    }
}
