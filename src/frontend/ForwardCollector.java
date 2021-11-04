package frontend;

import ast.NodeClassDefine;
import ast.NodeFunctionDefine;
import ast.NodeProgramSection;
import ast.NodeRoot;
import utility.error.SemanticError;
import utility.scope.BroadScope;

// Collects forward reference symbol (class and function)
// Global variable cannot forward reference
public class ForwardCollector {
    public BroadScope globalScope = new BroadScope();

    public void collectRoot(NodeRoot node) {
        // 以 BFS 序收集 identifier
        for (var section : node.programSections) {
            if (section.genre == NodeProgramSection.Genre.CLASS_DEFINE)
                globalScope.defineClass(section.classDefineNode.name,
                        section.classDefineNode.position);
        }

        for (var section : node.programSections) {
            switch (section.genre) {
                case CLASS_DEFINE -> collectClass(section.classDefineNode);
                case FUNCTION_DEFINE -> collectFunction(section.functionDefine);
            }
        }
    }

    public void collectClass(NodeClassDefine node) {
        var classScope = globalScope.getClass(node.name, node.position);
        for (var variableDefine : node.variableDefines) {
            // term's initial expression should be empty
            for (var term : variableDefine.variableTerms) {
                if (term.initialExpression != null)
                    throw new SemanticError("Class member variable cannot " +
                            "have initialization expression", term.position);
                // ! misc-31.mx: line 55: "ltree[i][2 * x].mtag" 无法识别为左值
//                variableDefine.type.type.isVariable = true;
                classScope.defineVariable(term.name, variableDefine.type.type, term.position);
            }
        }
        boolean constructorExist = false;
        for (var methodDefine : node.methodDefines) {
            if (classScope.defineMethod(methodDefine)) {
                if (!constructorExist) constructorExist = true;
                else throw new SemanticError("Class '" + node.name
                        + "' has plural constructors", methodDefine.position);
            }
        }
    }

    public void collectFunction(NodeFunctionDefine node) {
        globalScope.defineFunction(node);
    }

}
