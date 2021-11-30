package ir;

import java.util.ArrayList;

public class IrModule {
    public ArrayList<IrInstruction> globalVariables = new ArrayList<>();
    public ArrayList<IrFunction> functions = new ArrayList<>();
}
