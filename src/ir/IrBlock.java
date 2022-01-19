package ir;

import utility.IrId;

import java.util.LinkedList;

public class IrBlock extends IrNode {
    public IrId label = new IrId();
    public LinkedList<IrInstruction> instructions = new LinkedList<>();
    public IrInstruction jumpInstruction = null;

    @Override
    public String toString() {
        // todo
        return null;
    }
}
