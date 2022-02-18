package frontend.ir.node;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import frontend.ir.IrBuilder;

public class IrTop extends IrNode {
    public LinkedList<IrInstruction> declares = new LinkedList<>();
    public LinkedList<IrInstruction> variableDefines = new LinkedList<>();

    // Here class is just structure without method.
    public LinkedHashMap<String, IrClass> classes = new LinkedHashMap<>();
    // HashMap key is function name without FUNCTION_PREFIX at beginning,
    // but function name of IrFunction has FUNCTION_PREFIX.
    // Method of class has prefix CLASS_METHOD_PREFIX in name.
    public LinkedHashMap<String, IrFunction> functions = new LinkedHashMap<>();

    @Override
    public void genIndex() {
        for (var clas: classes.values()) clas.genIndex();
        for (var func : functions.values()) func.genIndex();
    }

    @Override
    public String toString() {
        var tot = new StringBuilder();
        // * toString() 可以省略
        tot.append("; LLVM IR generated from programing language Mx*\n" +
                "; Compile Option: " + IrBuilder.cmdArgs + '\n' +
                "; === Declare ===\n\n");
        for (var declare : declares) tot.append(declare).append('\n');

        tot.append("\n\n; === Global Variable ===\n\n");
        for (var varDef : variableDefines) tot.append(varDef).append('\n');

        tot.append("\n\n; === Class Field ===\n\n");
        for (var clas : classes.values())
            tot.append(clas).append("\n");
        if (!classes.isEmpty()) tot.deleteCharAt(tot.length() - 1); // 多余空行

        tot.append("\n\n; === Function ===\n\n");
        for (var func : functions.values())
            tot.append(func).append("\n");
        if (!functions.isEmpty()) tot.deleteCharAt(tot.length() - 1);

        return tot.toString();
    }
}