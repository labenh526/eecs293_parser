import java.util.HashMap;
import java.util.function.Function;

public final class Connector extends AbstractToken{

    private TerminalSymbol connectorType;

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
        //Switch statement to return the character representation on each of the valid tokens
        //No need for break statements because each case causes the method to return
        switch(this.getType()){  /*TODO: Do this with less complexity */
            case PLUS:
                return "+";
            case MINUS:
                return "-";
            case TIMES:
                return "*";
            case DIVIDE:
                return "/";
            case OPEN:
                return "(";
            case CLOSE:
                return ")";
        }
        //If no condition was found;
        throw new IllegalArgumentException("Not valid connector");
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
        switch (type) {
            /* TODO: Lower complexity */
            case PLUS:
            case MINUS:
            case TIMES:
            case DIVIDE:
            case OPEN:
            case CLOSE:
                return true;
            default:
                return false;
        }
    }


}

