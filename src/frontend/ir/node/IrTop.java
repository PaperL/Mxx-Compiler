package frontend.ir.node;

import utility.CmdArgument;
import utility.Constant;

import java.util.LinkedHashMap;
import java.util.LinkedList;

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
        for (var clas : classes.values()) clas.genIndex();
        for (var func : functions.values()) func.genIndex();
    }

    @Override
    public String toString() {
        var tot = new StringBuilder();
        var comment = Constant.cmdArgs.contains(CmdArgument.ArgumentType.IR_COMMENT);
        // * toString() 可以省略
        if (comment) tot.append("; LLVM IR generated from programing language Mx*\n" +
                "; Compile Option: " + Constant.cmdArgs + '\n' +
                "; === Declare ===\n\n");
        for (var declare : declares) tot.append(declare).append('\n');

        if (comment) tot.append("\n\n; === Global Variable ===\n\n");
        for (var varDef : variableDefines) tot.append(varDef).append('\n');

        if (comment) tot.append("\n\n; === Class Field ===\n\n");
        for (var clas : classes.values())
            tot.append(clas).append("\n");
        if (!classes.isEmpty()) tot.deleteCharAt(tot.length() - 1); // 多余空行

        if (comment) tot.append("\n\n; === Function ===\n\n");
        for (var func : functions.values())
            tot.append(func).append("\n");
        if (!functions.isEmpty()) tot.deleteCharAt(tot.length() - 1);

        return tot.toString();
    }
}