package utility;

import org.antlr.v4.runtime.ParserRuleContext;

public class Position {
    private final int line;
    private final int column;

    public Position(int line_, int column_) {
        this.line = line_;
        this.column = column_;
    }

//    public Position(Token token) {
//        this.line = token.getLine();
//        this.column = token.getCharPositionInLine();
//    }

    public Position(ParserRuleContext ctx) {
        var token = ctx.getStart();
        this.line = token.getLine();
        this.column = token.getCharPositionInLine();
    }

    public String toString() {
        return line + ":" + column;
    }
}