import java.util.List;


public enum TerminalSymbol implements Symbol{
    VARIABLE, PLUS, MINUS, TIMES, DIVIDE, OPEN, CLOSE;

    @Override
    public ParseState parse(List<Token> input) {
        if (input.isEmpty())
            return ParseState.FAILURE;
        Token token = input.get(0);
        if (token.matches(TerminalSymbol.this)) {
            return ParseState.build(LeafNode.build(token),input.subList(1,input.size()));
        } else {
            return ParseState.FAILURE;
        }
    }
}

//Edits
/*
Removed unnecessary code
Used .isEmpty instead of .size==0
 */