package frontend.ir.node;

import org.antlr.v4.runtime.misc.Pair;

import java.util.LinkedHashMap;

import frontend.ir.IrType;

public class IrClass extends IrNode {
    public String name = null;
    // Field name, index and type
    public LinkedHashMap<String, Pair<Integer, IrType>> fields = new LinkedHashMap<>();
    public IrFunction constructor = null;
    public LinkedHashMap<String, IrFunction> methods = new LinkedHashMap<>();

    @Override
    public void genIndex() {
        constructor.genIndex();
        for (var method : methods.values()) method.genIndex();
    }

    @Override
    public String toString() {
        var tot = new StringBuilder();
//        if (IrBuilder.cmdArgs.contains(CmdArgument.ArgumentType.IR_SOURCE_CODE))
//            tot.append(String.format("; Class %s\n", name));
        // Declare type
        var fieldsStr = new StringBuilder();
        for (var pair : fields.values())
            fieldsStr.append(pair.b).append(", ");
        if (!fields.isEmpty()) fieldsStr.delete(   // 删除末尾 ", "
                fieldsStr.length() - 2, fieldsStr.length());
        tot.append(String.format("%s = type { %s }\n",
                new IrType(this).getNotPointer(),
                fieldsStr));
        // Constructor
        tot.append('\n').append(constructor);
        // Method
        for (var method : methods.values())
            tot.append('\n').append(method);
        return tot.toString();
    }
}
