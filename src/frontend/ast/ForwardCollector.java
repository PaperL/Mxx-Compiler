package frontend.ast;

import frontend.ast.node.NodeClassDefine;
import frontend.ast.node.NodeFunctionDefine;
import frontend.ast.node.NodeProgramSection;
import frontend.ast.node.NodeRoot;
import frontend.ast.scope.BroadScope;
import utility.error.SemanticError;

/**
 * Collects forward reference symbol (class and function)
 * Global variable cannot forward reference
 */
public class ForwardCollector {
    final NodeRoot astRoot;
    final BroadScope globalScope = new BroadScope();

    public ForwardCollector(NodeRoot astRoot_) {
        astRoot = astRoot_;
    }

    public void work() {
        collectRoot(astRoot);
    }

    public BroadScope getScope() {
        return globalScope;
    }

    void collectRoot(NodeRoot node) {
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

    void collectClass(NodeClassDefine node) {
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

    void collectFunction(NodeFunctionDefine node) {
        globalScope.defineFunction(node);
    }

}
