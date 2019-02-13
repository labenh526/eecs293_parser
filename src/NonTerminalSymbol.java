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

    private final static Map<NonTerminalSymbol, Map<TerminalSymbol, SymbolSequence>> productions = new EnumMap<>(NonTerminalSymbol.class);
    static {
        /*Map for Expression */
        Map<TerminalSymbol, SymbolSequence> expressionMap = new HashMap<>();
        expressionMap.put(TerminalSymbol.PLUS, null);
        expressionMap.put(TerminalSymbol.MINUS, SymbolSequence.build(TERM, EXPRESSION_TAIL));
        expressionMap.put(TerminalSymbol.TIMES, null);
        expressionMap.put(TerminalSymbol.DIVIDE, null);
        expressionMap.put(TerminalSymbol.OPEN, SymbolSequence.build(TERM, EXPRESSION_TAIL));
        expressionMap.put(TerminalSymbol.CLOSE, null);
        expressionMap.put(TerminalSymbol.VARIABLE, SymbolSequence.build(TERM, EXPRESSION_TAIL));
        expressionMap.put(null, SymbolSequence.EPSILON);

        /*Map for Expression_Tail */
        Map<TerminalSymbol, SymbolSequence> expressionTailMap = new HashMap<>();
        expressionMap.put(TerminalSymbol.PLUS, SymbolSequence.build(TerminalSymbol.PLUS, TERM, EXPRESSION_TAIL));
        expressionMap.put(TerminalSymbol.MINUS, SymbolSequence.build(TerminalSymbol.MINUS, TERM, EXPRESSION_TAIL));
        expressionMap.put(TerminalSymbol.TIMES, SymbolSequence.EPSILON);
        expressionMap.put(TerminalSymbol.DIVIDE, SymbolSequence.EPSILON);
        expressionMap.put(TerminalSymbol.OPEN, SymbolSequence.EPSILON);
        expressionMap.put(TerminalSymbol.CLOSE, SymbolSequence.EPSILON);
        expressionMap.put(TerminalSymbol.VARIABLE, SymbolSequence.EPSILON);
        expressionMap.put(null, SymbolSequence.EPSILON);

        /*Map for Term */
        Map<TerminalSymbol, SymbolSequence> termMap = new HashMap<>();
        expressionMap.put(TerminalSymbol.PLUS, null);
        expressionMap.put(TerminalSymbol.MINUS, SymbolSequence.build(UNARY, TERM_TAIL));
        expressionMap.put(TerminalSymbol.TIMES, null);
        expressionMap.put(TerminalSymbol.DIVIDE, null);
        expressionMap.put(TerminalSymbol.OPEN, SymbolSequence.build(UNARY, TERM_TAIL));
        expressionMap.put(TerminalSymbol.CLOSE, null);
        expressionMap.put(TerminalSymbol.VARIABLE, SymbolSequence.build(UNARY, TERM_TAIL));
        expressionMap.put(null, SymbolSequence.EPSILON);

        /*Map for Term_Tail */
        Map<TerminalSymbol, SymbolSequence> termTailMap = new HashMap<>();
        expressionMap.put(TerminalSymbol.PLUS, SymbolSequence.EPSILON);
        expressionMap.put(TerminalSymbol.MINUS, SymbolSequence.EPSILON);
        expressionMap.put(TerminalSymbol.TIMES, SymbolSequence.build(TerminalSymbol.TIMES, UNARY, TERM_TAIL));
        expressionMap.put(TerminalSymbol.DIVIDE, SymbolSequence.build(TerminalSymbol.DIVIDE, UNARY, TERM_TAIL));
        expressionMap.put(TerminalSymbol.OPEN, SymbolSequence.EPSILON);
        expressionMap.put(TerminalSymbol.CLOSE, SymbolSequence.EPSILON);
        expressionMap.put(TerminalSymbol.VARIABLE, SymbolSequence.EPSILON);
        expressionMap.put(null, SymbolSequence.EPSILON);

        /*Map for Unary */
        Map<TerminalSymbol, SymbolSequence> unaryMap = new HashMap<>();
        expressionMap.put(TerminalSymbol.PLUS, null);
        expressionMap.put(TerminalSymbol.MINUS, SymbolSequence.build(TerminalSymbol.MINUS, FACTOR));
        expressionMap.put(TerminalSymbol.TIMES, null);
        expressionMap.put(TerminalSymbol.DIVIDE, null);
        expressionMap.put(TerminalSymbol.OPEN, SymbolSequence.build(FACTOR));
        expressionMap.put(TerminalSymbol.CLOSE, null);
        expressionMap.put(TerminalSymbol.VARIABLE, SymbolSequence.build(FACTOR));
        expressionMap.put(null, SymbolSequence.EPSILON);

        /*Map for Factor */
        Map<TerminalSymbol, SymbolSequence> factorMap = new HashMap<>();
        expressionMap.put(TerminalSymbol.PLUS, null);
        expressionMap.put(TerminalSymbol.MINUS, null);
        expressionMap.put(TerminalSymbol.TIMES, null);
        expressionMap.put(TerminalSymbol.DIVIDE, null);
        expressionMap.put(TerminalSymbol.OPEN, SymbolSequence.build(TerminalSymbol.OPEN, EXPRESSION, TerminalSymbol.CLOSE));
        expressionMap.put(TerminalSymbol.CLOSE, null);
        expressionMap.put(TerminalSymbol.VARIABLE, SymbolSequence.build(TerminalSymbol.VARIABLE));
        expressionMap.put(null, SymbolSequence.EPSILON);

        //Build productions
        productions.put(EXPRESSION, expressionMap);
        productions.put(EXPRESSION_TAIL, expressionTailMap);
        productions.put(TERM, termMap);
        productions.put(TERM_TAIL, termTailMap);
        productions.put(UNARY, unaryMap);
        productions.put(FACTOR, factorMap);
    }

    @Override
    public ParseState parse(List<Token> input) {
        ParseState state;
//        if (input.isEmpty()) {
//            state = productions.get(this).get(null).match(Objects.requireNonNull(input,"Input cannot be null"));
//        }
//        else {
            System.out.println(input);
            state = productions.get(this).get(input.get(0).getType()).match(Objects.requireNonNull(input, "Input cannot be null"));
        //}
        if(state.isSuccess())
            return state;
        return ParseState.FAILURE;
    }

    static final Optional<Node> parseInput(List<Token> input){
        ParseState testParse = EXPRESSION.parse(Objects.requireNonNull(input, "Input cannot be null"));
        if (!testParse.isSuccess() || !testParse.hasNoRemainder()) {
            return Optional.empty();
        }
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
