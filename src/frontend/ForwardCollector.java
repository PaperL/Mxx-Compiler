package frontend;

import ast.NodeClassDefine;
import ast.NodeFunctionDefine;
import ast.NodeProgramSection;
import ast.NodeRoot;
import utility.Type;
import utility.error.SemanticError;
import utility.scope.BroadScope;

import java.util.ArrayList;

// Collects forward reference symbol (class and function)
// Global variable cannot forward reference
public class ForwardCollector {
    public BroadScope globalScope = new BroadScope();

    public void visitRoot(NodeRoot node) {
        // 以 BFS 序收集 identifier
        for (var section : node.programSections) {
            if (section.genre == NodeProgramSection.Genre.CLASS_DEFINE)
                globalScope.DefineClass(section.classDefineNode.name,
                        section.classDefineNode.position);
        }

        for (var section : node.programSections) {
            switch (section.genre) {
                case CLASS_DEFINE -> visitClass(section.classDefineNode);
                case FUNCTION_DEFINE -> visitFunction(section.functionDefine);
            }
        }
    }

    public void visitClass(NodeClassDefine node) {
        var classScope = globalScope.GetClass(node.name, node.position);
        for (var variableDefine : node.variableDefines) {
            // term's initial expression should be empty
            for (var term : variableDefine.variableTerms) {
                if (term.initialExpression != null)
                    throw new SemanticError("Class member variable cannot have initialization expression", term.position);
                classScope.DefineVariable(term.name, variableDefine.type.type, term.position);
            }
        }
        for (var methodDefine : node.methodDefines)
            classScope.DefineMethod(methodDefine);
    }

    public void visitFunction(NodeFunctionDefine node) {
        globalScope.DefineFunction(node);
    }

}
