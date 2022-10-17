package backend.asm;

import backend.asm.node.AsmBlock;
import backend.asm.node.AsmFunction;
import backend.asm.node.AsmInstruction;
import backend.asm.node.AsmTop;
import frontend.ir.IrId;
import frontend.ir.node.IrBlock;
import frontend.ir.node.IrFunction;
import frontend.ir.node.IrInstruction;
import frontend.ir.node.IrTop;
import utility.Constant;

import java.util.Arrays;
import java.util.stream.IntStream;

public class AsmBuilder {
// region BASIC

    public AsmTop asmRoot;

    AsmFunction currentFunction = null;
    AsmBlock currentBlock = null;

    public AsmBuilder(IrTop irRoot) {
        asmRoot = new AsmTop(irRoot);
    }

    public void build() {
        buildRoot();
    }

    public String print() {
        return asmRoot.toString();
    }

    AsmId getAsmId(IrId irId) {
        assert AsmId.irIdMap.containsKey(irId);
        assert irId.genre != IrId.Genre.CONSTANT;
//        if (AsmId.irIdMap.containsKey(irId))
        return AsmId.irIdMap.get(irId);
//        else {
//            assert irId.genre == IrId.Genre.CONSTANT;
//            var constant = new AsmId(AsmId.Genre.REGISTER, irId);
//            var insLi = new AsmInstruction(null, AsmInstruction.Genre.li);
//            currentBlock.instructions.add(insLi);
//            insLi.setLi(new AsmId(irId.constantValue), constant);
//            return constant;
//        }
    }

    AsmId createConstReg(int val) {
        var constant = new AsmId(AsmId.Genre.REGISTER, null);
        var insLi = new AsmInstruction((IrInstruction) null);
        currentBlock.instructions.add(insLi);
        insLi.setLi(new AsmId(val), constant);
        return constant;
    }

// endregion

// region Top Build

    void buildRoot() {
        var irRoot = (IrTop) asmRoot.irNode;
        currentBlock = asmRoot.prefixBlock;
        var dirText = new AsmInstruction(AsmInstruction.DirectiveName.test);
        var dirFile = new AsmInstruction(AsmInstruction.DirectiveName.file);
        dirFile.directiveArguments.add(new AsmId(Constant.SOURCE_FILENAME));
        currentBlock.instructions.addAll(Arrays.asList(
                dirText, dirFile));
        currentBlock = asmRoot.sufficBlock;
        // declares 仅供使用 Clang 测试 LLVM IR 正确性, 在 Assembly 中忽略
        for (var globalVariableDefine : irRoot.variableDefines)
            buildInstruction(globalVariableDefine);
        currentBlock = null;
        for (var clas : irRoot.classes.values()) {
            if (clas.constructor != null)
                buildFunction(clas.constructor);
            for (var method : clas.methods.values())
                buildFunction(method);
        }
        for (var func : irRoot.functions.values())
            buildFunction(func);
    }

    void buildFunction(IrFunction irNode) {
        if (irNode.builtIn) return;
        var func = new AsmFunction(irNode);
        currentFunction = func;
        asmRoot.functions.add(func);
        IntStream.range(0, irNode.blocks.size()).forEach(
                i -> func.blocks.add(new AsmBlock(
                        irNode.blocks.get(i), i != 0)));
        func.blocks.add(new AsmBlock(irNode.returnBlock, true));
        for (var block : func.blocks)
            buildBlock((IrBlock) block.irNode);
    }

    void buildBlock(IrBlock irNode) {
        currentBlock = AsmBlock.irBlockMap.get(irNode);
        // AsmId of block label is created in AsmBlock constructor
        for (var ins : irNode.instructions)
            buildInstruction(ins);
        buildInstruction(irNode.jumpInstruction);
    }

    void buildInstruction(IrInstruction irIns) {
        // * AsmIns(IrIns, Genre) 构造函数会直接使用 IR 信息生成对象
        // * 因此需要保证所涉及的 AsmId 均已存在, 才能通过 irIdMap 访问.
        // * 此外, 为了减少 bug, 所有 AsmId 当且仅当对应 IrId 第一次被遍历到时生成.
        // * 故本函数中会存在未使用的 new AsmId
        switch (irIns.genre) {
            case COMMENT, DECLARE -> {
                return;
            }
            case GLOBAL_VARIABLE -> {
                var variable = new AsmId(AsmId.Genre.GLOBAL, irIns.insId);
                var dirType = new AsmInstruction(AsmInstruction.DirectiveName.type);
                dirType.directiveArguments.addAll(Arrays.asList(
                        variable, new AsmId("@object")));
                dirType.comment = "@" + irIns.insId.globalName;
                var dirGlobl = new AsmInstruction(AsmInstruction.DirectiveName.globl);
                dirGlobl.directiveArguments.add(variable);
                var ins = new AsmInstruction(irIns);
                ins.setLABEL(variable);
                var dirWord = new AsmInstruction(AsmInstruction.DirectiveName.word);
                dirWord.directiveArguments.add(new AsmId(0));
                var dirSize = new AsmInstruction(AsmInstruction.DirectiveName.size);
                dirSize.directiveArguments.addAll(Arrays.asList(
                        variable, new AsmId(irIns.insId.type.sizeof())));
                // todo ? 此处可以加个 asmid.sizeof
                currentBlock.instructions.addAll(Arrays.asList(
                        dirType, dirGlobl, ins, dirWord, dirSize));
            }
            case ALLOCA -> { // Alloca size is compile-time constant
                var ptr = new AsmId(AsmId.Genre.REGISTER, irIns.insId);
                var address = currentFunction.stackFrame.buildAlloca(irIns);
                var ins = new AsmInstruction(irIns);
                ins.setArithImm(AsmInstruction.Genre.addi,
                        new AsmId(AsmId.RegisterAbiName.sp), address, ptr);
                currentBlock.instructions.add(ins);
            }
            case LOAD -> {
                // In Mx*, all memory data are word (4 bytes)
                var data = new AsmId(AsmId.Genre.REGISTER, irIns.insId);
                var ins = new AsmInstruction(irIns);
                ins.setLw(getAsmId(irIns.loadAddress), data);
                currentBlock.instructions.add(ins);
            }
            case STORE -> {
                var ins = new AsmInstruction(irIns);
                ins.setSw(getAsmId(irIns.storeAddress), getAsmId(irIns.storeData));
                currentBlock.instructions.add(ins);
            }
            case BRANCH -> {
                var insBeqz = new AsmInstruction(irIns);
                insBeqz.setBeqz(getAsmId(irIns.branchCondId), getAsmId(irIns.branchTrueLabel));
                var insJ = new AsmInstruction(irIns);
                insJ.setJ(getAsmId(irIns.branchFalseLabel));
                currentBlock.instructions.addAll(Arrays.asList(insBeqz, insJ));
            }
            case JUMP -> {
                var ins = new AsmInstruction(irIns);
                ins.setJ(getAsmId(irIns.jumpLabel));
                currentBlock.instructions.add(ins);
            }
            case CALL -> {
                int argNum = irIns.callArguments.size();
                for (int argIndex = 0; argIndex < argNum; argIndex++) {
                    var irArg = irIns.callArguments.get(argIndex);
                    if (argIndex < 8) {    // Passed in register
                        var insMvLi = new AsmInstruction(irIns);
                        var reg = new AsmId(
                                AsmId.RegisterAbiName.valueOf(
                                        'a' + Integer.toString(argIndex)));
                        insMvLi.setMvLi(irArg, reg);
                        currentBlock.instructions.add(insMvLi);
                    } else {    // Passed on the stack
                        var address = currentFunction.stackFrame.allocaArgument(irArg);
                        var data = (irArg.genre == IrId.Genre.CONSTANT)
                                ? createConstReg(irArg.constantValue)
                                : getAsmId(irArg);
                        var insSw = new AsmInstruction(irIns);
                        insSw.setSw(address, data);
                        currentBlock.instructions.add(insSw);
                    }
                }
                var insCall = new AsmInstruction(irIns);
                var callLabel = (irIns.callFunc == null) ? null
                        : AsmFunction.irFunctionMap.get(irIns.callFunc).label;
                insCall.setCall(callLabel);
                var insMv = new AsmInstruction(irIns);
                var retVal = new AsmId(AsmId.Genre.REGISTER, irIns.insId);
                insMv.setMv(new AsmId(AsmId.RegisterAbiName.a0), retVal);
                currentBlock.instructions.addAll(Arrays.asList(
                        insCall, insMv));
            }
            case RETURN -> {
                var insMvLi = new AsmInstruction(irIns);
                var a0 = new AsmId(AsmId.RegisterAbiName.a0);
                insMvLi.setMvLi(irIns.returnValue, a0);
                // IR code ensures that there is only one
                // 'ret' instruction in a function
                var insRet = new AsmInstruction(irIns);
                insRet.genre = AsmInstruction.Genre.ret;
                currentBlock.instructions.addAll(Arrays.asList(
                        insMvLi, insRet));
            }
            case PHI -> {
                // ? All block in phi instruction argument should have been built
                var result = new AsmId(AsmId.Genre.REGISTER, irIns.insId);
                for (var phiArg : irIns.phiArgs) {
                    var branchBlock = AsmBlock.irBlockLabelMap.get(phiArg.b);
                    var ins = new AsmInstruction(irIns);
                    var irValue = phiArg.a;
                    ins.setMvLi(irValue, result);
                    branchBlock.instructions.add(
                            branchBlock.instructions.size() - 1,
                            ins); // Insert before jump instruction
                }
            }
            case ARITH -> {
                var result = new AsmId(AsmId.Genre.REGISTER, irIns.insId);
                var ins = new AsmInstruction(irIns);
                currentBlock.instructions.add(ins);
                switch (irIns.opGenre) {
                    case ADD, SUB, MUL, DIV, MOD, SHIFT_L, SHIFT_R, AND, OR, XOR -> {
                        AsmInstruction.Genre asmOpGenre = null;
                        switch (irIns.opGenre) {
                            case ADD, SUB, MUL, DIV, AND, OR, XOR -> asmOpGenre
                                    = AsmInstruction.Genre.valueOf(
                                    irIns.opGenre.toString().toLowerCase());
                            case SHIFT_L -> asmOpGenre = AsmInstruction.Genre.sll;
                            case SHIFT_R -> asmOpGenre = AsmInstruction.Genre.sra;
                            case MOD -> asmOpGenre = AsmInstruction.Genre.rem;
                        }
                        ins.setIrArith(asmOpGenre,
                                irIns.arithOperandLeft, irIns.arithOperandRight, result);
                    }
                    case GT -> ins.setIrArith(AsmInstruction.Genre.slt,
                            irIns.arithOperandRight, irIns.arithOperandLeft, result);
                    case LT -> ins.setIrArith(AsmInstruction.Genre.slt,
                            irIns.arithOperandLeft, irIns.arithOperandRight, result);
                    case GE, LE -> {
                        var temp = new AsmId(AsmId.Genre.REGISTER, null);
                        var insXori = new AsmInstruction(irIns);
                        currentBlock.instructions.add(insXori);
                        if (irIns.opGenre == IrInstruction.operatorGenre.GE)
                            insXori.setIrArith(AsmInstruction.Genre.slt,
                                    irIns.arithOperandLeft, irIns.arithOperandRight, temp);
                        else
                            insXori.setIrArith(AsmInstruction.Genre.slt,
                                    irIns.arithOperandRight, irIns.arithOperandLeft, temp);
                        ins.setArithImm(AsmInstruction.Genre.xori,
                                temp, new AsmId(1), result);
                    }
                    case EQ, NEQ -> {
                        var temp = new AsmId(AsmId.Genre.REGISTER, null);
                        var insSub = new AsmInstruction(irIns);
                        currentBlock.instructions.add(insSub);
                        insSub.setIrArith(AsmInstruction.Genre.sub,
                                irIns.arithOperandRight, irIns.arithOperandLeft, temp);
                        ins.setEz((irIns.opGenre == IrInstruction.operatorGenre.EQ)
                                        ? AsmInstruction.Genre.seqz : AsmInstruction.Genre.snez,
                                temp, result);
                    }
                }
            }
            case GET_ELEMENT_PTR -> {
                var ptr = new AsmId(AsmId.Genre.REGISTER, irIns.insId);
                var insAdd = new AsmInstruction(irIns);
                if (irIns.eleIndexes.size() == 1) {
                    AsmId offset;
                    offset = new AsmId(AsmId.Genre.REGISTER, null);
                    var insMul = new AsmInstruction(irIns);
                    currentBlock.instructions.add(insMul);
                    insMul.setArith(AsmInstruction.Genre.mul,
                            getAsmId(irIns.eleIndexes.get(0)),
                            createConstReg(irIns.objectPtr.type.getDeref().sizeof()),
                            offset);    // "mul" doesn't have immediate instruction "muli"
                    insAdd.setArith(AsmInstruction.Genre.add,
                            getAsmId(irIns.objectPtr), offset, ptr);
                } else {
                    assert irIns.eleIndexes.size() == 2;
                    assert irIns.eleIndexes.get(1).genre == IrId.Genre.CONSTANT;
                    var id = irIns.eleIndexes.get(1).constantValue;
                    var offsetVal = 0;
                    var fields = irIns.objectPtr.type.clas.fields;
                    var iter = fields.entrySet().iterator();
                    for (var i = 0; i < id; i++) {
                        var entry = iter.next();
                        var type = entry.getValue().b;
                        offsetVal += type.sizeof();
                    }
                    insAdd.setIrArith(AsmInstruction.Genre.add,
                            irIns.objectPtr,
                            new IrId(null, offsetVal),
                            ptr);
                }
            }
            case BITCAST -> {
                var result = new AsmId(AsmId.Genre.REGISTER, irIns.insId);
                var insMv = new AsmInstruction(irIns);
                insMv.setMv(getAsmId(irIns.castPtr), result);
            }
        }
    }

// endregion
}
