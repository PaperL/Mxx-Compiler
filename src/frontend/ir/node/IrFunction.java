package frontend.ir.node;

import java.util.LinkedList;
import frontend.ir.IrId;
import frontend.ir.IrType;

public class IrFunction extends IrNode {
    public IrType returnType = null;
    public String name = null;
    public LinkedList<IrId> arguments = new LinkedList<>();
    // public IrRegName  id = null;    // Attributes

    public IrId retValPtr = null;
    public IrBlock returnBlock = null;
    public LinkedList<IrBlock> blocks = new LinkedList<>();

    @Override
    public String toString() {
        IrId.clearIndexCounter();
        var tot = new StringBuilder();
        // define i32 @main(i32 %1, i8 %2) {
        tot.append("define ").append(returnType)
                .append(" @").append(name).append("(");
        for (var arg : arguments)
            tot.append(arg.type).append(" ").append(arg).append(", ");
        tot.append(") {\n");
        for (var block : blocks)
            tot.append(block).append("\n");
        tot.deleteCharAt(tot.length() - 1); // 删除函数末空行
        tot.append("}\n");
        return tot.toString();
    }
}
