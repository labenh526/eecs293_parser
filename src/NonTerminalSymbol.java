import java.util.*;
import java.util.EnumMap;

public enum NonTerminalSymbol implements Symbol {

    EXPRESSION,
    EXPRESSION_TAIL,
    TERM,
    TERM_TAIL,
    UNARY,
    FACTOR;

    private final static Map<NonTerminalSymbol, List<SymbolSequence>> nonTerminalMap = new EnumMap<>(NonTerminalSymbol.class);
    static {
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

        nonTerminalMap.put(EXPRESSION,expressionList);
        nonTerminalMap.put(EXPRESSION_TAIL,expression_TailList);
        nonTerminalMap.put(TERM,termList);
        nonTerminalMap.put(TERM_TAIL,term_TailList);
        nonTerminalMap.put(UNARY,unaryList);
        nonTerminalMap.put(FACTOR,factorList);
    }

    @Override
    public ParseState parse(List<Token> input) {
        for (SymbolSequence seq: nonTerminalMap.get(this)) {
            ParseState state = seq.match(Objects.requireNonNull(input,"Input cannot be null"));
            if(state.isSuccess())
                return state;
        }
        return ParseState.FAILURE;
    }

    static final Optional<Node> parseInput(List<Token> input){
        ParseState testParse = EXPRESSION.parse(Objects.requireNonNull(input, "Input cannot be null"));
        if (!testParse.isSuccess() || !testParse.hasNoRemainder())
            return Optional.empty();
        else {
            return Optional.of(testParse.getNode());
        }
    }
}


//Edits
/*
Changed HashMap to EnumMap
Moved anonymous class into static block
Combined ifElse in parseInput
 */
