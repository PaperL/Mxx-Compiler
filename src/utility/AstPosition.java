package utility;

import org.antlr.v4.runtime.ParserRuleContext;

/**
 * Used in semantic check
 */
public class AstPosition {
    private final int line;
    private final int column;

    public AstPosition(int line_, int column_) {
        this.line = line_;
        this.column = column_;
    }

//    public Position(Token token) {
//        this.line = token.getLine();
//        this.column = token.getCharPositionInLine();
//    }

    public AstPosition(ParserRuleContext ctx) {
        var token = ctx.getStart();
        this.line = token.getLine();
        this.column = token.getCharPositionInLine();
    }

    public String toString() {
        return line + ":" + column;
    }
}