package frontend.ir.node;

import frontend.ir.IrId;
import utility.CmdArgument;
import utility.Constant;

import java.util.LinkedList;

public class IrBlock extends IrNode {
    public static boolean isFirstBlock = true;
    public IrId label = new IrId(IrId.Genre.LABEL);
    public LinkedList<IrInstruction> instructions = new LinkedList<>();
    public IrInstruction jumpInstruction = null;

    public static void setFirstBlock() {
        isFirstBlock = true;
    }

    @Override
    public void genIndex() {
        label.setIndex();
        for (var ins : instructions) ins.genIndex();
    }

    @Override
    public String toString() {
        var tot = new StringBuilder();
        var labelId = label.getLabel();     // 即使省略也占用命名
        if (!isFirstBlock)
            tot.append(labelId).append(":\n");   // 省略 "0:"
        else isFirstBlock = false;
        for (var ins : instructions) {
            if (ins.genre != IrInstruction.Genre.COMMENT) tot.append("  ");
            tot.append(ins).append("\n");
            if (Constant.cmdArgs.contains(CmdArgument.ArgumentType.DEBUG))
                System.out.println(ins);
        }
        tot.append("  ").append(jumpInstruction).append("\n");
        return tot.toString();
    }
}
