import java.util.*;

public enum NonTerminalSymbol implements Symbol {

    EXPRESSION,
    EXPRESSION_TAIL,
    TERM,
    TERM_TAIL,
    UNARY,
    FACTOR;

    //TODO: make this look more readable
    private final static Map<NonTerminalSymbol, List<SymbolSequence>> nonTerminalMap = new HashMap<NonTerminalSymbol, List<SymbolSequence>>(){

        List<SymbolSequence> expressionList = Arrays.asList(SymbolSequence.build(TERM, EXPRESSION_TAIL));
        List<SymbolSequence> expression_TailList = Arrays.asList(SymbolSequence.build(TerminalSymbol.PLUS, TERM, EXPRESSION_TAIL),
                                                                 SymbolSequence.build(TerminalSymbol.MINUS, TERM, EXPRESSION_TAIL),
                                                                 SymbolSequence.EPSILON);
        List<SymbolSequence> termList = Arrays.asList(SymbolSequence.build(UNARY, TERM_TAIL));
        List<SymbolSequence> term_TailList = Arrays.asList(SymbolSequence.build(TerminalSymbol.TIMES, UNARY, TERM_TAIL),
                                                           SymbolSequence.build(TerminalSymbol.DIVIDE, UNARY, TERM_TAIL),
                                                           SymbolSequence.EPSILON);
        List<SymbolSequence> unaryList = Arrays.asList(SymbolSequence.build(TerminalSymbol.MINUS, FACTOR),
                                                       SymbolSequence.build(FACTOR));
        List<SymbolSequence> factorList = Arrays.asList(SymbolSequence.build(TerminalSymbol.OPEN, EXPRESSION, TerminalSymbol.CLOSE),
                                                        SymbolSequence.build(TerminalSymbol.VARIABLE));

        {
            put(EXPRESSION,expressionList);
            put(EXPRESSION_TAIL,expression_TailList);
            put(TERM,termList);
            put(TERM_TAIL,term_TailList);
            put(UNARY,unaryList);
            put(FACTOR,factorList);

        }
    };

//(
    @Override
    public ParseState parse(List<Token> input) {
        for (SymbolSequence seq: nonTerminalMap.get(this)) {
            ParseState state = seq.match(Objects.requireNonNull(input,"Input cannot be null"));
            if(state != ParseState.FAILURE)
                return state;
        }
        return ParseState.FAILURE;
    }



}
