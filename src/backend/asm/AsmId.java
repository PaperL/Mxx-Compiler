package backend.asm;

/**
 * Assembly Identifier
 */
public class AsmId {
// region BASIC
    public enum Genre {
        GLOBAL, REGISTER,
        // GLOBAL 为全局变量, 可视为存变量地址的虚拟寄存器
        // REGISTER 为实际寄存器, 为 CPU 中确定的数十个寄存器
        IMMEDIATE,
        // 前三者均为指令操作数
        LABEL,
        // 用于分支 / 跳转
    }

    public Genre genre = null;

    public enum RegisterAbiName {
        zero, ra, sp, gp, tp,   // x0 - x4
        t0, t1, t2,             // x5 - x7
        fp, s1,                 // x8 - x9
        a0, a1, a2, a3, a4, a5, a6, a7,             // x10 - x17
        s2, s3, s4, s5, s6, s7, s8, s9, s10, s11,   // x18 - x27
        t3, t4, t5, t6          // x28 - x31
    }

    public String globl = null;
    public RegisterAbiName reg = null;
    public int imm = 0;

    public String label = null; // Block or Function
// endregion

    public AsmId(Genre genre_) {
        genre = genre_;
    }
}
