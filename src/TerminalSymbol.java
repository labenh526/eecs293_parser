import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public enum TerminalSymbol implements Symbol{
    VARIABLE, PLUS, MINUS, TIMES, DIVIDE, OPEN, CLOSE;

    @Override
    public ParseState parse(List<Token> input) {
        if (input.size()==0)
            return ParseState.FAILURE;
        Token token = input.get(0);
        if (token.matches(TerminalSymbol.this)) {
            //List<Token> remainder = new ArrayList<>();
            //Collections.copy(remainder, input);
            //remainder.remove(0);
            return ParseState.build(LeafNode.build(token),input.subList(1,input.size()));
        } else {
            System.out.println("Parse returned failure");
            return ParseState.FAILURE;
        }
    }
}
