package backend.asm.node;

import backend.asm.AsmId;
import backend.asm.AsmStackFrame;
import frontend.ir.node.IrFunction;
import frontend.ir.node.IrInstruction;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class AsmFunction extends AsmNode {
    // region BASIC
    public static HashMap<IrFunction, AsmFunction> irFunctionMap = new HashMap<>();

    public LinkedList<AsmBlock> blocks = new LinkedList<>();
    public AsmStackFrame stackFrame = new AsmStackFrame();

    public AsmId label;
    public AsmBlock prefixBlock;

    public AsmFunction(IrFunction node) {
        super(node);
        assert node != null;
        var funcName = node.name;

        irFunctionMap.put(node, this);
        label = new AsmId(AsmId.Genre.LABEL, null);
        label.caption = funcName;

        prefixBlock = new AsmBlock(null, false);
        var dirGlobl = new AsmInstruction(AsmInstruction.DirectiveName.globl);
        dirGlobl.directiveArguments.add(new AsmId(funcName));
        var insLabel = new AsmInstruction((IrInstruction) null);
        insLabel.setLABEL(label);
        prefixBlock.instructions.addAll(Arrays.asList(
                dirGlobl, insLabel));
    }
// endregion

    @Override
    public String toString() {
        // todo stackFrame confirm offsets
        // todo confirm block labels
        var tot = new StringBuilder();
        tot.append(prefixBlock);
//        for (var block : blocks)
//            tot.append(block);
        blocks.forEach(tot::append);    // ? Magic Grammar!
        tot.append('\t' * 10 + "# -- End function");
        return tot.toString();
    }
}
