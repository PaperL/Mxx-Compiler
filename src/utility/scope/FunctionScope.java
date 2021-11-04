package utility.scope;

import ast.NodeFunctionDefine;
import utility.Type;
import utility.error.SemanticError;

import java.util.ArrayList;

// Store function claim information (return type and arguments)
public class FunctionScope extends VariableScope {
    // VariableScope: HashMap<String, Type> variables
    public Type returnType = null;
    public ArrayList<Type> argumentsType = new ArrayList<>();
    public ArrayList<String> argumentsName = new ArrayList<>();

    public FunctionScope(BroadScope globalScope_, NodeFunctionDefine node) {
        super(globalScope_);
        // Here is just function claim, not function body.

        if (node.type != null) {
            returnType = node.type.type;
            globalScope.checkTypeExist(returnType, node.position);
        }

        final var argList = node.argumentList;
        if (argList != null) {
            final var len = argList.types.size();
            for (int i = 0; i < len; i++) {
                var name = argList.identifiers.get(i);
                var type = argList.types.get(i).type;
                // Check type available
                globalScope.checkTypeExist(type, node.position);
                argumentsType.add(type);
                // Check argument redefinition
                if (variables.containsKey(name))
                    throw new SemanticError(
                            "Redefinition of argument '" + argList.identifiers.get(i)
                                    + "'", argList.types.get(i).position);
                else variables.put(name, type);
            }
            argumentsName = argList.identifiers;
        }
    }
}
