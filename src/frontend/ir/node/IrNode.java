package frontend.ir.node;

abstract public class IrNode {

    /**
     * Generate name for virtual register
     */
    abstract public void genIndex();

    /**
     * @return LLVM IR code
     */
    abstract public String toString();
}
