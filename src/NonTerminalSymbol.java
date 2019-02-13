import com.sun.org.apache.xerces.internal.xs.datatypes.ObjectList;

import java.util.*;
import java.util.EnumMap;

public enum NonTerminalSymbol implements Symbol {

    EXPRESSION,
    EXPRESSION_TAIL,
    TERM,
    TERM_TAIL,
    UNARY,
    FACTOR;

    private final static Map<NonTerminalSymbol, Map<TerminalSymbol, SymbolSequence>> productions = new EnumMap<>(NonTerminalSymbol.class);
    static {
        /* Create productions */
        SymbolSequence expressionProduction1 = SymbolSequence.build(TERM, EXPRESSION_TAIL);
        SymbolSequence expressionTailProduction1 = SymbolSequence.build(TerminalSymbol.PLUS, TERM, EXPRESSION_TAIL);
        SymbolSequence expressionTailProduction2 = SymbolSequence.build(TerminalSymbol.MINUS, TERM, EXPRESSION_TAIL);
        SymbolSequence termProduction1 = SymbolSequence.build(UNARY, TERM_TAIL);
        SymbolSequence termTailProduction1 = SymbolSequence.build(TerminalSymbol.TIMES, UNARY, TERM_TAIL);
        SymbolSequence termTailProduction2 = SymbolSequence.build(TerminalSymbol.DIVIDE, UNARY, TERM_TAIL);
        SymbolSequence unaryProduction1 = SymbolSequence.build(TerminalSymbol.MINUS, FACTOR);
        SymbolSequence unaryProduction2 = SymbolSequence.build(FACTOR);
        SymbolSequence factorProduction1 = SymbolSequence.build(TerminalSymbol.OPEN, EXPRESSION, TerminalSymbol.CLOSE);
        SymbolSequence factorProduction2 = SymbolSequence.build(TerminalSymbol.VARIABLE);

        /*Map for Expression */
        Map<TerminalSymbol, SymbolSequence> expressionMap = new HashMap<>();
        expressionMap.put(TerminalSymbol.PLUS, null);
        expressionMap.put(TerminalSymbol.MINUS, expressionProduction1);
        expressionMap.put(TerminalSymbol.TIMES, null);
        expressionMap.put(TerminalSymbol.DIVIDE, null);
        expressionMap.put(TerminalSymbol.OPEN, expressionProduction1);
        expressionMap.put(TerminalSymbol.CLOSE, null);
        expressionMap.put(TerminalSymbol.VARIABLE, expressionProduction1);
        expressionMap.put(null, SymbolSequence.EPSILON);

        /*Map for Expression_Tail */
        Map<TerminalSymbol, SymbolSequence> expressionTailMap = new HashMap<>();
        expressionTailMap.put(TerminalSymbol.PLUS, expressionTailProduction1);
        expressionTailMap.put(TerminalSymbol.MINUS, expressionTailProduction2);
        expressionTailMap.put(TerminalSymbol.TIMES, SymbolSequence.EPSILON);
        expressionTailMap.put(TerminalSymbol.DIVIDE, SymbolSequence.EPSILON);
        expressionTailMap.put(TerminalSymbol.OPEN, SymbolSequence.EPSILON);
        expressionTailMap.put(TerminalSymbol.CLOSE, SymbolSequence.EPSILON);
        expressionTailMap.put(TerminalSymbol.VARIABLE, SymbolSequence.EPSILON);
        expressionTailMap.put(null, SymbolSequence.EPSILON);

        /*Map for Term */
        Map<TerminalSymbol, SymbolSequence> termMap = new HashMap<>();
        termMap.put(TerminalSymbol.PLUS, null);
        termMap.put(TerminalSymbol.MINUS, termProduction1);
        termMap.put(TerminalSymbol.TIMES, null);
        termMap.put(TerminalSymbol.DIVIDE, null);
        termMap.put(TerminalSymbol.OPEN, termProduction1);
        termMap.put(TerminalSymbol.CLOSE, null);
        termMap.put(TerminalSymbol.VARIABLE, termProduction1);
        termMap.put(null, SymbolSequence.EPSILON);

        /*Map for Term_Tail */
        Map<TerminalSymbol, SymbolSequence> termTailMap = new HashMap<>();
        termTailMap.put(TerminalSymbol.PLUS, SymbolSequence.EPSILON);
        termTailMap.put(TerminalSymbol.MINUS, SymbolSequence.EPSILON);
        termTailMap.put(TerminalSymbol.TIMES, termTailProduction1);
        termTailMap.put(TerminalSymbol.DIVIDE, termTailProduction2);
        termTailMap.put(TerminalSymbol.OPEN, SymbolSequence.EPSILON);
        termTailMap.put(TerminalSymbol.CLOSE, SymbolSequence.EPSILON);
        termTailMap.put(TerminalSymbol.VARIABLE, SymbolSequence.EPSILON);
        termTailMap.put(null, SymbolSequence.EPSILON);

        /*Map for Unary */
        Map<TerminalSymbol, SymbolSequence> unaryMap = new HashMap<>();
        unaryMap.put(TerminalSymbol.PLUS, null);
        unaryMap.put(TerminalSymbol.MINUS, unaryProduction1);
        unaryMap.put(TerminalSymbol.TIMES, null);
        unaryMap.put(TerminalSymbol.DIVIDE, null);
        unaryMap.put(TerminalSymbol.OPEN, unaryProduction2);
        unaryMap.put(TerminalSymbol.CLOSE, null);
        unaryMap.put(TerminalSymbol.VARIABLE, unaryProduction2);
        unaryMap.put(null, SymbolSequence.EPSILON);

        /*Map for Factor */
        Map<TerminalSymbol, SymbolSequence> factorMap = new HashMap<>();
        factorMap.put(TerminalSymbol.PLUS, null);
        factorMap.put(TerminalSymbol.MINUS, null);
        factorMap.put(TerminalSymbol.TIMES, null);
        factorMap.put(TerminalSymbol.DIVIDE, null);
        factorMap.put(TerminalSymbol.OPEN, factorProduction1);
        factorMap.put(TerminalSymbol.CLOSE, null);
        factorMap.put(TerminalSymbol.VARIABLE, factorProduction2);
        factorMap.put(null, SymbolSequence.EPSILON);

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
        //TerminalSymbol lookAhead;
        state = findState(Objects.requireNonNull(input,"Input cannot be null"),this);
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

    private static ParseState findState(List<Token> input, NonTerminalSymbol nonTerminalSymbol){
        TerminalSymbol lookAhead;
        SymbolSequence production;
        if (Objects.requireNonNull(input, "Input cannot be null").isEmpty()) {
            lookAhead = null;
        }
        else {
            lookAhead = input.get(0).getType();
        }
        production = productions.get(nonTerminalSymbol).get(lookAhead);
        if(production==null){
            return ParseState.FAILURE;
        }
        return production.match(input);
    }
}


//Edits
/*
Changed HashMap to EnumMap
Moved anonymous class into static block
Combined ifElse in parseInput
 */
