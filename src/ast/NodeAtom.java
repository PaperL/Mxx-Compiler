package ast;

import utility.Position;

public class NodeAtom extends AstNode {
    public enum Genre {
        THIS, IDENTIFIER, STRING_CONSTANT,
        DECIMAL_INTEGER, BOOLEAN, NULL;
    }

    public Genre genre = null;

    public String identifier = null;
    public String stringConstant = null;
    public Integer decimalInteger = null;
    public Boolean booleanValue = null;

    public NodeAtom(Position position) {
        super(position);
    }
}
