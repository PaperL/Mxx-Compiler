package backend.asm.node;

import backend.asm.AsmId;
import frontend.ir.IrId;
import frontend.ir.node.IrInstruction;
import utility.error.InternalError;

import java.util.LinkedList;

public class AsmInstruction extends AsmNode {
// region BASIC

    public Genre genre = null;
    // Instruction
    public AsmId rs1 = null, rs2 = null,
            imm = null, rd = null;
    // Label
    public AsmId labelName = null;  // Also 'call' target label name
    // Directive
    public DirectiveName directiveName = null;
    public LinkedList<AsmId> directiveArguments = null;

    public String comment = null;

    // * Use "created" AsmId to generate AsmInstruction from IrInstruction
    public AsmInstruction(IrInstruction node) {
        super(node);
//        switch (genre) {
//            case sw -> {
//                assert node.genre == IrInstruction.Genre.STORE;
//                rs1 = AsmId.irIdMap.get(node.storeAddress);
//                rs2 = AsmId.irIdMap.get(node.storeData);
//            }
//            case beqz -> {
//                assert node.genre == IrInstruction.Genre.BRANCH;
//                rs1 = AsmId.irIdMap.get(node.branchCondId);
//                imm = AsmId.irIdMap.get(node.branchTrueLabel);
//            }
//            case j -> {
//                assert node.genre == IrInstruction.Genre.BRANCH
//                        || node.genre == IrInstruction.Genre.JUMP;
//                switch (node.genre) {
//                    case BRANCH -> imm = AsmId.irIdMap.get(node.branchFalseLabel);
//                    case JUMP -> imm = AsmId.irIdMap.get(node.jumpLabel);
//                }
//            }
//            case mv -> {
//                assert node.genre == IrInstruction.Genre.PHI;
//                // rd and rs1 is assigned in "AsmBuilder.buildInstruction()"
//            }
//            case li -> {
//                // Generate a virtual register for imm
//            }
//            case mul -> {
//                assert node.genre == IrInstruction.Genre.GET_ELEMENT_PTR;
//
//            }
//            default -> throw new InternalError("todo", "asmins construction");
//        }
    }

    public AsmInstruction(DirectiveName directiveName_) {
        super(null);
        genre = Genre.DIRECTIVE;
        directiveName = directiveName_;
        directiveArguments = new LinkedList<>();
    }

// endregion

    public void setLABEL(AsmId variable) {
        genre = Genre.LABEL;    // todo 以下函数类似, 用 set 函数设置 genre 值
        labelName = variable;
    }

    public void setLw(AsmId address, AsmId data) {
        genre = Genre.lw;
        rs1 = address;
        rd = data;
    }

    public void setSw(AsmId address, AsmId data) {
        genre = Genre.sw;
        rs1 = address;
        rs2 = data;
    }

    public void setBeqz(AsmId cond, AsmId label) {
        genre = Genre.beqz;
        rs1 = cond;
        imm = label;
    }

    public void setJ(AsmId label) {
        genre = Genre.j;
        imm = label;
    }

    public void setMvLi(IrId irData, AsmId target) {
        if (irData.genre == IrId.Genre.CONSTANT) {
            genre = Genre.li;
            imm = new AsmId(irData.constantValue);
        } else {
            genre = Genre.mv;
            assert AsmId.irIdMap.containsKey(irData);
            rs1 = AsmId.irIdMap.get(irData);
        }
        rd = target;
    }

    public void setMv(AsmId data, AsmId target) {
        genre = Genre.mv;
        rs1 = data;
        rd = target;
    }

    public void setLi(AsmId imm_, AsmId target) {
        genre = Genre.li;
        rd = target;
        imm = imm_;
    }

    public void setIrArith(Genre genre_, IrId irOp1, IrId irOp2, AsmId result) {
        assert !(irOp1.genre == irOp2.genre && irOp1.genre == IrId.Genre.CONSTANT);
        if (irOp1.genre == IrId.Genre.CONSTANT
                || irOp2.genre == IrId.Genre.CONSTANT) {
            switch (genre_) {
                case add, sll, sra, and, or, xor, slt -> {
                    genre = Genre.valueOf(genre_.toString() + 'i');
                    if (irOp1.genre == IrId.Genre.CONSTANT) {
                        rs1 = AsmId.irIdMap.get(irOp2);
                        imm = new AsmId(irOp1.constantValue);
                    } else {
                        rs1 = AsmId.irIdMap.get(irOp1);
                        imm = new AsmId(irOp2.constantValue);
                    }
                }
                case sub, mul, div, rem -> {
                    genre = genre_;
                    if (irOp1.genre == IrId.Genre.CONSTANT) {
                        rs1 = new AsmId(irOp1.constantValue);
                        rs2 = AsmId.irIdMap.get(irOp2);
                    } else {
                        rs1 = AsmId.irIdMap.get(irOp1);
                        rs2 = new AsmId(irOp2.constantValue);
                    }
                }
                default -> throw new InternalError("AsmIns", "setARITH");
            }
        } else {
            genre = genre_;
            rs1 = AsmId.irIdMap.get(irOp1);
            rs2 = AsmId.irIdMap.get(irOp2);
        }
        rd = result;
    }

    public void setArith(Genre genre_, AsmId op1, AsmId op2, AsmId result) {
        genre = genre_;
        rs1 = op1;
        rs2 = op2;
        rd = result;
    }

    public void setArithImm(Genre genre_, AsmId op1, AsmId imm, AsmId result) {
        genre = genre_;
        rs1 = op1;
        rs2 = imm;
        rd = result;
    }

    public void setEz(Genre asmOpGenre, AsmId op, AsmId result) {
        genre = asmOpGenre;
        rs1 = op;
        rd = result;
    }

    public void setCall(AsmId targetLabelName){
        labelName = targetLabelName;
    }

    @Override
    public String toString() {
        // todo
        return null;
    }

    public enum Genre {
        // RV32I
        add, sub, sll, sra,
        and, or, xor,
        slt,
        addi, slli, srai, andi, ori, xori, slti,
        lw, sw,
        // RV32M
        mul, div, rem,
        // Pseudo Instruction
        mv, li,
        seqz, snez,
        beqz, j,
        call, ret,
        // Non-instruction
        LABEL, DIRECTIVE
    }

    public enum DirectiveName {
        test, file,
        globl, type,    // .p2align is useless for Ravel
        word, size,
        EMPTY_INSTRUCTION,
    }
}
