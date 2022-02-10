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
        var tot = new StringBuilder();
        tot.append("; LLVM IR generated from programing language Mx*\n" +
                "\n; === Declare ===\n\n");
        // toString() 可以省略
        for (var declare : declares) tot.append(declare);
        tot.append("\n\n; === Class Member Variable ===\n\n");
        for (var type : types) tot.append(type);
        tot.append("\n\n; === Global Variable ===\n\n");
        for (var varDef : variableDefines) tot.append(varDef);
        tot.append("\n\n; === Function ===\n\n");
        for (var func : functions.values())
            tot.append(func).append("\n");
        tot.deleteCharAt(tot.length() - 1); // 删除文末多余空行
        return tot.toString();
    }
}