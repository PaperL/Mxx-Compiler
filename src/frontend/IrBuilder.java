package frontend;

import ast.*;
import ir.IrInstruction;
import ir.IrModule;

public class IrBuilder {
    public IrModule module;


    public void build(NodeRoot node) {
        for (var section : node.programSections)
            visitProgramSection(section);
    }

    public void visitProgramSection(NodeProgramSection node) {
        switch (node.genre) {
            case CLASS_DEFINE -> {
                visitClassDefine(node.classDefineNode);
            }
            case FUNCTION_DEFINE -> {
                visitFunctionDefine(node.functionDefine);
            }
            case VARIABLE_DEFINE -> {
                visitVariableDefine(node.globalVariableDefine, true);
            }
        }
    }

    public void visitVariableDefine(NodeVariableDefine node, boolean isGlobal) {
        if (isGlobal) {
            var ins = new IrInstruction(DE);

        } else {

        }
    }

    public void visitClassDefine(NodeClassDefine node) {

    }

    public void visitFunctionDefine(NodeFunctionDefine node) {

    }
}
