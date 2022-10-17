package backend.asm.node;

import frontend.ir.node.IrNode;

import java.util.LinkedList;

public class AsmTop extends AsmNode {
//region BASIC

    // Declaration
    public AsmBlock prefixBlock = new AsmBlock(null, false);
    // Program Body
    public LinkedList<AsmFunction> functions = new LinkedList<>();
    // Global variables and constants
    public AsmBlock sufficBlock = new AsmBlock(null, false);

    public AsmTop(IrNode node) {
        super(node);
    }

//endregion

    @Override
    public String toString() {
        var tot = new StringBuilder();
        tot.append(prefixBlock);
        for (var func : functions) tot.append(func);
        tot.append(sufficBlock);
        return tot.toString();
    }
}
