import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class Connector extends AbstractToken{

    private TerminalSymbol connectorType;

    //private static final Map<TerminalSymbol,String> stringMap = new HashMap<>();
    private static final Map<TerminalSymbol,String> stringMap = new HashMap<>();
    static{
        stringMap.put(TerminalSymbol.PLUS,"+");
        stringMap.put(TerminalSymbol.MINUS,"-");
        stringMap.put(TerminalSymbol.TIMES,"*");
        stringMap.put(TerminalSymbol.DIVIDE,"/");
        stringMap.put(TerminalSymbol.OPEN,"(");
        stringMap.put(TerminalSymbol.CLOSE,")");
    }

    private static final Map<TerminalSymbol,Boolean> boolMap = new HashMap<>();
    static{
        boolMap.put(TerminalSymbol.PLUS,true);
        boolMap.put(TerminalSymbol.MINUS,true);
        boolMap.put(TerminalSymbol.TIMES,true);
        boolMap.put(TerminalSymbol.DIVIDE,true);
        boolMap.put(TerminalSymbol.OPEN,true);
        boolMap.put(TerminalSymbol.CLOSE,true);
        boolMap.put(TerminalSymbol.VARIABLE,false);
    }

    private static final Map<TerminalSymbol,Boolean> isOperatorMap = new HashMap<>();
    static{
        isOperatorMap.put(TerminalSymbol.PLUS,true);
        isOperatorMap.put(TerminalSymbol.MINUS,true);
        isOperatorMap.put(TerminalSymbol.TIMES,true);
        isOperatorMap.put(TerminalSymbol.DIVIDE,true);
        isOperatorMap.put(TerminalSymbol.OPEN,false);
        isOperatorMap.put(TerminalSymbol.CLOSE,false);
        isOperatorMap.put(TerminalSymbol.VARIABLE,false);
    }

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
    public boolean isOperator() {
        return isOperatorMap.get(this.getType());
    }

    @Override
    public String toString() {
        return stringMap.get(Objects.requireNonNull(this.getType(),"Not a valid connector"));
    }

    public static final Connector build(TerminalSymbol type) {

        if (type == null){
            throw new NullPointerException("Cannot create connector from type null");
        }
        //Checks to see if type is an invalid type
        else if(!boolMap.get(type)){
            throw new IllegalArgumentException("Method requires TerminalSymbols of type: PLUS, MINUS, " +
                    "TIMES, DIVIDE, OPEN, or CLOSE.");
        }
        return cache.get(type,Connector::new);
    }

}

//Edits
/*

 */
