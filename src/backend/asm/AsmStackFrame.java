package backend.asm;

import java.util.LinkedHashMap;

public class AsmStackFrame {
    public int sizeLimit = 2 * 1024 * 1024;
    public LinkedHashMap<AsmId, Integer> stack = new LinkedHashMap<>();

    public void alloca(AsmId var) {
        // todo
    }
}
