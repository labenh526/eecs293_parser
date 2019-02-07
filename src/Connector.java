import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public final class Connector extends AbstractToken{

    private TerminalSymbol connectorType;

    Map<TerminalSymbol,String> stringMap = new HashMap<TerminalSymbol, String>(){
        {
            put(TerminalSymbol.PLUS,"+");
            put(TerminalSymbol.MINUS,"-");
            put(TerminalSymbol.TIMES,"*");
            put(TerminalSymbol.DIVIDE,"/");
            put(TerminalSymbol.OPEN,"(");
            put(TerminalSymbol.CLOSE,")");
        }
    };

    //cache works properly if Cache constructor is public
    private static Cache<TerminalSymbol,Connector> cache = new Cache<>();

    //private constructor to create a connector
    private Connector(TerminalSymbol type) {
        connectorType = type;
    }

    @Override
    public TerminalSymbol getType(){
        return connectorType;
    }

    @Override
    public String toString() {
        if(this.getType()==null){
            throw new IllegalArgumentException("Not valid connector");
        }
        return stringMap.get(this.getType());
    }

    public static final Connector build(TerminalSymbol type) {

        if (type == null){
            throw new NullPointerException("Cannot create connector from type null");
        }
        else if(!isValidConnector(type)){
            throw new IllegalArgumentException("Method requires TerminalSymbols of type: PLUS, MINUS, " +
                    "TIMES, DIVIDE, OPEN, or CLOSE.");
        }
        return cache.get(type,Connector::new);
    }

    //Helper method to check to see if a TerminalSymbol is one of the valid connector types
    private static boolean isValidConnector(TerminalSymbol type) {

        Map<TerminalSymbol,Boolean> booleanMap = new HashMap<TerminalSymbol,Boolean>(){
            {
                put(TerminalSymbol.PLUS,true);
                put(TerminalSymbol.MINUS,true);
                put(TerminalSymbol.TIMES,true);
                put(TerminalSymbol.DIVIDE,true);
                put(TerminalSymbol.OPEN,true);
                put(TerminalSymbol.CLOSE,true);
                put(TerminalSymbol.VARIABLE,false);
            }
        };
        return booleanMap.get(type);

    }


}

