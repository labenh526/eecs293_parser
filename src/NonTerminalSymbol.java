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
        SymbolSequence[] expressionProductions =   {null,
                                                    expressionProduction1,
                                                    null,
                                                    null,
                                                    expressionProduction1,
                                                    null,
                                                    expressionProduction1};

        Map<TerminalSymbol, SymbolSequence> expressionMap = generateMap(expressionProductions);

        /* Map for Expression_Tail*/
        SymbolSequence[] expressionTailProductions =   {expressionTailProduction1,
                                                        expressionTailProduction2,
                                                        SymbolSequence.EPSILON,
                                                        SymbolSequence.EPSILON,
                                                        SymbolSequence.EPSILON,
                                                        SymbolSequence.EPSILON,
                                                        SymbolSequence.EPSILON};

        Map<TerminalSymbol, SymbolSequence> expressionTailMap = generateMap(expressionTailProductions);

        /*Map for Term */
        SymbolSequence[] termProductions =  {null,
                                            termProduction1,
                                            null,
                                            null,
                                            termProduction1,
                                            null,
                                            termProduction1};

        Map<TerminalSymbol, SymbolSequence> termMap = generateMap(termProductions);

        /*Map for Term_Tail */
        SymbolSequence[] termTailProductions =  {SymbolSequence.EPSILON,
                                                SymbolSequence.EPSILON,
                                                termTailProduction1,
                                                termTailProduction2,
                                                SymbolSequence.EPSILON,
                                                SymbolSequence.EPSILON,
                                                SymbolSequence.EPSILON};

        Map<TerminalSymbol, SymbolSequence> termTailMap = generateMap(termTailProductions);

        /*Map for Unary */
        SymbolSequence[] unaryProductions = {null,
                                            unaryProduction1,
                                            null,
                                            null,
                                            unaryProduction2,
                                            null,
                                            unaryProduction2};

        Map<TerminalSymbol, SymbolSequence> unaryMap = generateMap(unaryProductions);


        /*Map for Factor */
        SymbolSequence[] factorProductions =    {null,
                                                null,
                                                null,
                                                null,
                                                factorProduction1,
                                                null,
                                                factorProduction2};

        Map<TerminalSymbol, SymbolSequence> factorMap = generateMap(factorProductions);

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

    private static Map<TerminalSymbol, SymbolSequence> generateMap(SymbolSequence[] productions) {
        Map<TerminalSymbol, SymbolSequence> map= new HashMap<>();
        map.put(TerminalSymbol.PLUS, productions[0]);
        map.put(TerminalSymbol.MINUS, productions[1]);
        map.put(TerminalSymbol.TIMES, productions[2]);
        map.put(TerminalSymbol.DIVIDE, productions[3]);
        map.put(TerminalSymbol.OPEN, productions[4]);
        map.put(TerminalSymbol.CLOSE, productions[5]);
        map.put(TerminalSymbol.VARIABLE, productions[6]);
        map.put(null, SymbolSequence.EPSILON);
        return map;
    }
}


//Edits
/*
Changed HashMap to EnumMap
Moved anonymous class into static block
Combined ifElse in parseInput
 */
