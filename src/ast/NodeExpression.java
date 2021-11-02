package ast;

import utility.Position;

import java.util.ArrayList;

public class NodeExpression extends AstNode {
    public enum Genre {
        PAREN, ATOM, MEMBER, ARRAY, FUNCTION,   // term
        ASSIGN, NEW,                            // command
        SELF, UNARY, BINARY,                    // arithmetic
        LAMBDA;
    }

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
    // endregion

    // * Command
    // region command
    // ASSIGN
    public NodeExpression lValue = null;
    public NodeExpression rValue = null;
    // NEW
    public NodeType type = null;
    // endregion

    // * Arithmetic
    // region arithmetic
    public enum OpEnum {    // Arithmetic Operator Enum
        INC, DEC,
        BANG, TILDE, ADD, SUB,
        MUL, DIV, MOD, SHIFT_L, SHIFT_R,
        GT, LT, GE, LE, EQ, NOT_EQ,
        BIT_AND, BIT_OR, CARET, AND, OR
    }

    public OpEnum operator = null;

    public NodeExpression termExpr = null;
    public NodeExpression lTermExpr = null;
    public NodeExpression rTermExpr = null;

    // endregion

    // LAMBDA
    public NodeArgumentList lambdaArgumentList = null;
    public NodeSuite lambdaSuite = null;
    public NodeExpressionList lambdaExpressionList = null;

    public NodeExpression(Position position) {
        super(position);
    }
}
