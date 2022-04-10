package backend.asm;

import backend.asm.node.AsmTop;
import frontend.ir.node.*;
import utility.CmdArgument;

public class AsmBuilder {
// region BASIC
    public static CmdArgument cmdArgs = null;

    // * Builder's Member Variables
    public static final AsmTop asmRoot = new AsmTop();

    public AsmBuilder(CmdArgument cmdArgs_) {
        cmdArgs = cmdArgs_;
    }

    public String print(){
        return asmRoot.toString();
    }

// endregion

// region Top Build

// todo

    public void buildRoot(IrTop irNode) {
        // declares 仅供使用 Clang 测试 LLVM IR 正确性
        for(var globalVariableDefine:irNode.variableDefines)
            buildInstruction(globalVariableDefine);
        for(var clas:irNode.classes.values())
            buildClass(clas);
        for(var func:irNode.functions.values())
            buildFunction(func);
    }

    public void buildClass(IrClass irNode){

    }

    public void buildFunction(IrFunction irNode) {

    }

    public void buildBlock(IrBlock irNode) {

    }

    public void buildInstruction(IrInstruction irNode) {

    }

    public void registerAllocation(){
        // todo
    }

// endregion
}
