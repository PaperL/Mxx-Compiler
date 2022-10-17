package backend.asm.node;

import backend.asm.AsmId;
import frontend.ir.IrId;
import frontend.ir.node.IrBlock;

import java.util.HashMap;
import java.util.LinkedList;

public class AsmBlock extends AsmNode {
    // region BASIC
    public static HashMap<IrBlock, AsmBlock> irBlockMap = new HashMap<>();
    public static HashMap<IrId, AsmBlock> irBlockLabelMap = new HashMap<>();

    public AsmId label = null;
    public LinkedList<AsmInstruction> instructions = new LinkedList<>();
    // ASM 中无需保证块末为跳转语句

    public AsmBlock(IrBlock node, boolean explicitLabel) {
        super(node);
        if (node != null && explicitLabel) {
            irBlockMap.put(node, this);
            irBlockLabelMap.put(node.label, this);
            label = new AsmId(AsmId.Genre.LABEL, node.label);
        }
    }

// endregion

    @Override
    public String toString() {
        var tot = new StringBuilder();
        if (label != null) tot.append(label);   // null -> "null"
        instructions.forEach(tot::append);
        return null;
    }
}
