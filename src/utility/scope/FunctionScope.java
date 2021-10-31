package utility.scope;

import ast.NodeFunctionDefine;
import utility.Position;
import utility.Type;
import utility.error.SemanticError;

import java.util.ArrayList;
import java.util.HashMap;

public class FunctionScope extends VariableScope {
    // VariableScope: HashMap<String, Type> variables
    public Type returnType = null;
    public ArrayList<Type> argumentsType = new ArrayList<>();
    public ArrayList<String> argumentsName = new ArrayList<>();

    public FunctionScope(BroadScope globalScope_, NodeFunctionDefine node) {
        super(globalScope_);

        returnType = node.type.type;
        var argTypes = new ArrayList<Type>();
        var len = node.argumentList.types.size();
        var argList = node.argumentList;
        for (int i = 0; i < len; i++) {
            // Check argument redefinition
            if (variables.containsKey(argList.identifiers.get(i)))
                throw new SemanticError(
                        "Redefinition of argument '" + argList.identifiers.get(i)
                                + "'", argList.types.get(i).position);
            // Check type available
            var type = argList.types.get(i).type;
            // todo
//            if (type.genre == Type.Genre.IDENTIFIER) {
//
//            }
//            argTypes.add();
        }
        argumentsType = argTypes;
        argumentsName = node.argumentList.identifiers;
    }
}
