import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum TerminalSymbol implements Symbol{
    VARIABLE, PLUS, MINUS, TIMES, DIVIDE, OPEN, CLOSE;

    @Override
    public ParseState parse(List<Token> input) {
        Token token = input.get(0);
        if (token.matches(TerminalSymbol.this)) {
            List<Token> remainder = new ArrayList<>();
            Collections.copy(remainder, input);
            remainder.remove(0);
            return ParseState.build(LeafNode.build(token),remainder);
        } else {
            return ParseState.FAILURE;
        }
    }
}
