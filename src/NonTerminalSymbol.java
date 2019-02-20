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
        /* Create productions*/
        SymbolSequence expressionProduction = SymbolSequence.build(TERM, EXPRESSION_TAIL);
        SymbolSequence expressionTailProductionPlus = SymbolSequence.build(TerminalSymbol.PLUS, TERM, EXPRESSION_TAIL);
        SymbolSequence expressionTailProductionMinus = SymbolSequence.build(TerminalSymbol.MINUS, TERM, EXPRESSION_TAIL);
        SymbolSequence termProduction = SymbolSequence.build(UNARY, TERM_TAIL);
        SymbolSequence termTailProductionTimes = SymbolSequence.build(TerminalSymbol.TIMES, UNARY, TERM_TAIL);
        SymbolSequence termTailProductionDivide = SymbolSequence.build(TerminalSymbol.DIVIDE, UNARY, TERM_TAIL);
        SymbolSequence unaryProductionMinus = SymbolSequence.build(TerminalSymbol.MINUS, FACTOR);
        SymbolSequence unaryProductionFactor = SymbolSequence.build(FACTOR);
        SymbolSequence factorProductionOpen = SymbolSequence.build(TerminalSymbol.OPEN, EXPRESSION, TerminalSymbol.CLOSE);
        SymbolSequence factorProductionVariable = SymbolSequence.build(TerminalSymbol.VARIABLE);

        /*Map for Expression*/
        Map<TerminalSymbol, SymbolSequence> expressionMap = new HashMap<>();

        expressionMap.put(TerminalSymbol.MINUS,expressionProduction);
        expressionMap.put(TerminalSymbol.OPEN, expressionProduction);
        expressionMap.put(TerminalSymbol.VARIABLE, expressionProduction);
        expressionMap.put(null, SymbolSequence.EPSILON);

        /* Map for Expression_Tail*/
        Map<TerminalSymbol, SymbolSequence> expressionTailMap = new HashMap<>();

        expressionTailMap.put(TerminalSymbol.PLUS,expressionTailProductionPlus);
        expressionTailMap.put(TerminalSymbol.MINUS, expressionTailProductionMinus);
        expressionTailMap.put(TerminalSymbol.CLOSE, SymbolSequence.EPSILON);
        expressionTailMap.put(null, SymbolSequence.EPSILON);

        /* Map for Term*/
        Map<TerminalSymbol, SymbolSequence> termMap = new HashMap<>();

        termMap.put(TerminalSymbol.MINUS,termProduction);
        termMap.put(TerminalSymbol.OPEN, termProduction);
        termMap.put(TerminalSymbol.VARIABLE, termProduction);
        termMap.put(null, SymbolSequence.EPSILON);

        /* Map for Term_Tail*/
        Map<TerminalSymbol, SymbolSequence> termTailMap = new HashMap<>();

        termTailMap.put(TerminalSymbol.TIMES,termTailProductionTimes);
        termTailMap.put(TerminalSymbol.DIVIDE, termTailProductionDivide);
        termTailMap.put(TerminalSymbol.PLUS, SymbolSequence.EPSILON);
        termTailMap.put(TerminalSymbol.CLOSE, SymbolSequence.EPSILON);
        termTailMap.put(TerminalSymbol.MINUS, SymbolSequence.EPSILON);
        termTailMap.put(null, SymbolSequence.EPSILON);

        /*Map for Unary*/
        Map<TerminalSymbol, SymbolSequence> unaryMap = new HashMap<>();

        unaryMap.put(TerminalSymbol.MINUS,unaryProductionMinus);
        unaryMap.put(TerminalSymbol.OPEN, unaryProductionFactor);
        unaryMap.put(TerminalSymbol.VARIABLE, unaryProductionFactor);
        unaryMap.put(null, SymbolSequence.EPSILON);

        /*Map for Factor*/
        Map<TerminalSymbol, SymbolSequence> factorMap = new HashMap<>();

        factorMap.put(TerminalSymbol.OPEN, factorProductionOpen);
        factorMap.put(TerminalSymbol.VARIABLE, factorProductionVariable);
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

    //helper method
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
Updated productions to remove excess EPSILON
Removed generate map function
 */
