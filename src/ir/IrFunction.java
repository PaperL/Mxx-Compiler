package ir;

import utility.IrId;
import utility.IrType;

import java.util.LinkedList;

public class IrFunction {
    public IrType returnType = null;
    public String name = null;  // ? 或许用不到 name
    public LinkedList<IrType> argumentTypes = new LinkedList<>();
    // public IrRegName  id = null;    // Attributes

    public IrId returnId = null;
    public IrBlock returnBlock = null;
    public LinkedList<IrBlock> blocks = new LinkedList<>();
}
