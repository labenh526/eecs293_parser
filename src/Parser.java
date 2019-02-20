import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Parser {

    List<Token> input = new ArrayList<>();
    static Map<String,Token> parseMap = new HashMap<>();
    static{
        parseMap.put("+", Connector.build(TerminalSymbol.PLUS));
        parseMap.put("-", Connector.build(TerminalSymbol.MINUS));
        parseMap.put("*", Connector.build(TerminalSymbol.TIMES));
        parseMap.put("/", Connector.build(TerminalSymbol.DIVIDE));
        parseMap.put("(", Connector.build(TerminalSymbol.OPEN));
        parseMap.put(")", Connector.build(TerminalSymbol.CLOSE));
    }


    public static void main(String[] args){
        List<Token> input = new ArrayList<>();
        for (String token: args){
            input.add(toToken(token));
        }
        System.out.println(NonTerminalSymbol.parseInput(input).get());
    }

    private static Token toToken(String input){
        Token temp = parseMap.get(input);
        if(temp != null){
            return temp;
        }
        else{
            return Variable.build(input);
        }
    }
}
