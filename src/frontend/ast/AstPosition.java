package frontend.ast;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.Interval;

/**
 * Used in semantic check
 */
public class AstPosition {
    private final int line;
    private final int column;
    public String rawText;

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
        this.rawText = ctx.start.getInputStream().getText(
                new Interval(ctx.start.getStartIndex(), ctx.stop.getStopIndex()));
    }

    public String toString() {
        return line + ":" + column;
    }
}