package ir;

import utility.IrId;

import java.util.LinkedList;

public class IrBlock extends IrNode {
    public IrId label = new IrId();
    public LinkedList<IrInstruction> instructions = new LinkedList<>();
    public IrInstruction jumpInstruction = null;

    @Override
    public String toString() {
        var tot = new StringBuilder();
        var labelId = label.getLabel();
        if (labelId != 0) tot.append(label.getLabel()).append(":\n");   // 省略 "0:"
        for (var ins : instructions)
            tot.append("  ").append(ins).append("\n");
        tot.append("  ").append(jumpInstruction).append("\n");
        return tot.toString();
    }
}
