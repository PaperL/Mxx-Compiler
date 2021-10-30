package utility;

import org.antlr.v4.runtime.Token;

public class Position {
    private int line, column;

    public Position(int line_, int column_) {
        this.line = line_;
        this.column = column_;
    }

    public Position(Token token) {
        this.line = token.getLine();
        this.column = token.getCharPositionInLine();
    }

//    public Position(TerminalNode terminal) {
//        this(terminal.getSymbol());
//    }

//    public position(ParserRuleContext ctx) {
//        this(ctx.getStart());
//    }

    public int line() {
        return line;
    }

    public int column() {
        return column;
    }

    public String toString() {
        return line + ":" + column;
    }
}