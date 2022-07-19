package backend.asm.node;

import java.util.LinkedList;

public class AsmTop extends AsmNode {
//region BASIC

    // Declaration
    public LinkedList<AsmInstruction> prefixDirectives = new LinkedList<>();
    // Program Body
    public LinkedList<AsmFunction> functions = new LinkedList<>();
    // Global variables, constants and assembler arguments
    public LinkedList<AsmInstruction> suffixDirectives = new LinkedList<>();

//endregion

    @Override
    public String toString() {
        // todo
        return null;
    }
}
