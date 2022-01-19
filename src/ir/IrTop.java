package ir;

import java.util.LinkedList;
import java.util.HashMap;

public class IrTop extends IrNode {
    public LinkedList<IrInstruction> declares = new LinkedList<>();
    public LinkedList<IrInstruction> types = new LinkedList<>();
    public LinkedList<IrInstruction> variableDefines = new LinkedList<>();
    public HashMap<String, IrFunction> functions = new HashMap<>();

    @Override
    public String toString() {
        // todo
        return null;
    }
}