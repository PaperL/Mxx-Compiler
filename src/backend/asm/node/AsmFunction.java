package backend.asm.node;

import backend.asm.AsmStackFrame;

import java.util.LinkedList;

public class AsmFunction extends AsmNode{
// region BASIC
    public LinkedList<AsmBlock> blocks = new LinkedList<>();
    public AsmStackFrame stackFrame = new AsmStackFrame();
// endregion

    @Override
    public String toString() {
        // todo
        return null;
    }
}
