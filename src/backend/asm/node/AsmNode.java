package backend.asm.node;

import frontend.ir.node.IrNode;

abstract public class AsmNode {

    public IrNode irNode = null;

    public AsmNode(IrNode node) {
        irNode = node;
    }

    abstract public String toString();
}
