package backend.asm;

import frontend.ir.IrId;
import frontend.ir.node.IrInstruction;
import org.antlr.v4.runtime.misc.Pair;
import utility.Constant;

import java.util.LinkedList;

public class AsmStackFrame {
    int sizeLimit = Constant.STACK_SIZE;
    // AsmId and data size
    LinkedList<Pair<AsmId, Integer>> stack = new LinkedList<>();
    // The stack grows downwards (towards lower addresses).
    // Earlier elements in the list correspond to lower addresses in the stack.

    public AsmId buildAlloca(IrInstruction irIns) {
        var irId = irIns.insId;
        var address = new AsmId(AsmId.Genre.ADDRESS, null);
        var size = (irIns.allocaQuantity == null)
                ? irId.type.getDeref().sizeof()
                : irIns.allocaQuantity.constantValue;
        stack.addLast(new Pair<>(address, size));
        return address;
    }

    public AsmId allocaArgument(IrId irArg) {
        var address = new AsmId(AsmId.Genre.ADDRESS, null);
        var size = irArg.type.sizeof(); // ? Actually this is always 4
        stack.addFirst(new Pair<>(address, size));
        return address;
    }
}
