package backend.asm;

import frontend.ir.IrId;
import utility.error.InternalError;

import java.util.HashMap;

/**
 * Assembly Identifier
 */
public class AsmId {
    public static HashMap<IrId, AsmId> irIdMap = new HashMap<>();

    public Genre genre = null;
    public RegisterAbiName reg = null;
    public int imm = 0;
    public IrId irLabel = null; // ? Is irLabel used?
    // Function name, global variable name or directive argument
    public String caption = null;

    public AsmId(Genre genre_, IrId irId) {
        // * Any AsmId should be created at the first time
        // * the corresponding IrId is traversed.
        if (irId != null) {
            assert !irIdMap.containsKey(irId);
            irIdMap.put(irId, this);
        }
        genre = genre_;
        switch (genre) {
            case GLOBAL -> caption = irId.globalName;
            case ADDRESS -> {
                assert irId == null;
                reg = RegisterAbiName.sp;
            }
            case LABEL -> irLabel = irId;
            case REGISTER -> reg = RegisterAbiName.VIRTUAL;
            default -> throw new InternalError("todo", "asmid construction");
        }
    }

    public AsmId(RegisterAbiName reg_) {
        genre = Genre.REGISTER;
        reg = reg_;
    }

    public AsmId(String caption_) {
        genre = Genre.CAPTION;
        caption = caption_;
    }

    public AsmId(int imm_) {
        genre = Genre.IMMEDIATE;
        imm = imm_;
    }

    public enum Genre {
        GLOBAL, ADDRESS, REGISTER,
        // GLOBAL 为全局变量, 可视为存变量地址的虚拟寄存器
        // ADDRESS 为 x(sp), 即栈上地址
        // REGISTER 为实际寄存器, 为 CPU 中确定的数十个寄存器
        IMMEDIATE,
        // 前三者均为指令操作数
        LABEL, CAPTION
    }

    public enum RegisterAbiName {
        zero, ra, sp, gp, tp,   // x0 - x4
        t0, t1, t2,             // x5 - x7
        fp, s1,                 // x8 - x9
        a0, a1, a2, a3, a4, a5, a6, a7,             // x10 - x17
        s2, s3, s4, s5, s6, s7, s8, s9, s10, s11,   // x18 - x27
        t3, t4, t5, t6,          // x28 - x31
        VIRTUAL
    }
}
