package backend.asm.node;

import backend.asm.AsmId;

import java.util.LinkedList;

public class AsmBlock extends AsmNode{
// region BASIC

    public AsmId label = new AsmId(AsmId.Genre.LABEL);
    public LinkedList<AsmInstruction> instructions = new LinkedList<>();
    // ASM 中无需保证块末为跳转语句

// endregion

    @Override
    public String toString() {
        // todo
        return null;
    }
}
