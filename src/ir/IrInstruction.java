package ir;

import utility.IrId;
import utility.IrType;

public class IrInstruction {
    public enum Genre {
        DECLARE, GLOBAL_VARIABLE, TYPE,
        ALLOCA,
        LOAD, STORE,
        ARITH, LOGIC,
        BRANCH, JUMP,
        CALL, RETURN,
    }

    public Genre genre = null;
    public IrId rstId = null;

    // Declare
    public String declareInfo = null;
    // Global Variable
    public IrType globalVariableType = null;
    // Type (for Class Member Variable)

    // Alloca
    public IrType allocaType = null;
    // Store
    public IrType storeType = null;
    public IrId storeData = null;
    public IrId storeAddress = null;
    // Branch
    public IrId branchCondId = null;
    public IrBlock branchTrueBlock = null;
    public IrBlock branchFalseBlock = null;
    // Jump
    public IrId jumpLabel = null;
    // Return
    public IrType returnType = null;
    public IrId returnValue = null;

    public IrInstruction(Genre genre_) {
        switch (genre_) {
            case ALLOCA, LOAD, ARITH, LOGIC -> rstId = new IrId();
        }
    }

    public IrInstruction(Genre genre_, IrId id) {
        genre = genre_;
        rstId = id;
    }
}
