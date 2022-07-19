package frontend.ast.node;

import frontend.ast.AstPosition;

import java.util.ArrayList;

public class NodeExpression extends AstNode {
    public Genre genre = null;
    // * Term
    // region term
    // PAREN
    public NodeExpression parenExpr = null;
    // ATOM
    public NodeAtom atom = null;
    // MEMBER
    public NodeExpression objectExpr = null;
    public String memberName = null;
    // ARRAY
    public NodeExpression arrayNameExpr = null;
    public ArrayList<NodeBracket> brackets = new ArrayList<>();
    // FUNCTION
    public NodeExpression functionExpr = null;
    public NodeExpressionList arguments = null;
    // * Command
    // region command
    // ASSIGN
    public NodeExpression lValue = null;
    // endregion
    public NodeExpression rValue = null;
    // NEW
    public NodeType type = null;
    public OpEnum operator = null;
    // endregion
    public NodeExpression termExpr = null;
    public NodeExpression lTermExpr = null;
    public NodeExpression rTermExpr = null;
    // LAMBDA
    public NodeArgumentList lambdaArgumentList = null;
    public NodeSuite lambdaSuite = null;

    // endregion
    public NodeExpressionList lambdaExpressionList = null;

    public NodeExpression(AstPosition position) {
        super(position);
    }

    public enum Genre {
        PAREN, ATOM, MEMBER, ARRAY, FUNCTION,   // term
        ASSIGN, NEW,                            // command
        SELF, UNARY, BINARY,                    // arithmetic
        LAMBDA
    }

    // * Arithmetic
    // region arithmetic
    public enum OpEnum {    // Arithmetic Operator Enum
        INC, DEC,
        BANG, TILDE, ADD, SUB,
        MUL, DIV, MOD, SHIFT_L, SHIFT_R,
        GT, LT, GE, LE, EQ, NOT_EQ,
        BIT_AND, BIT_OR, CARET, AND, OR
    }
}
