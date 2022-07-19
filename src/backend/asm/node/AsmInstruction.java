package backend.asm.node;

import backend.asm.AsmId;

import java.util.LinkedList;

public class AsmInstruction extends AsmNode {
// region BASIC

    public Genre genre = null;
    public AsmId rs1 = null, rs2 = null,
            rd = null, imm = null;
    public String directiveName = null;
    public LinkedList<AsmId> directiveArguments = null;

    public AsmInstruction(Genre genre_) {
        genre = genre_;
    }

// endregion

    @Override
    public String toString() {
        // todo
        return null;
    }

    public enum Genre {
        GLOBAL_VARIABLE,
        LOAD, STORE,
        BRANCH, JUMP,
        CALL, RETURN,
        ARITH,
        // Assembler Directive
        DIRECTIVE,
    }
}
