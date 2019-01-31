import java.util.function.Function;

public final class Connector extends AbstractToken{

    private TerminalSignal connectorType;

    static Cache<TerminalSignal,Connector> cache;

    //private constructor to create a connector
    private Connector(TerminalSignal type) {
        connectorType = type;
    }

    @Override
    public TerminalSignal getType(){
        return connectorType;
    }

    @Override
    public String toString() {
        //Switch statement to return the character representation on each of the valid tokens
        //No need for break statements because each case causes the method to return
        switch(this.getType()){
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

    public static final Connector build(TerminalSignal type) {

        Function<TerminalSignal, Connector> construct = (TerminalSignal key) -> new Connector(key);

        if (type == null){
            throw new NullPointerException("Cannot create connector from type null");
        }
        else if(!isValidConnector(type)){
            throw new IllegalArgumentException("Method requires TerminalSignals of type: PLUS, MINUS, " +
                    "TIMES, DIVIDE, OPEN, or CLOSE.");
        }
        return cache.get(type,construct);
    }

    //Helper method to check to see if a TerminalSignal is one of the valid connector types
    private static boolean isValidConnector(TerminalSignal type) {
        switch (type) {
            case PLUS:
                return true;
            case MINUS:
                return true;
            case TIMES:
                return true;
            case DIVIDE:
                return true;
            case OPEN:
                return true;
            case CLOSE:
                return true;
        }
        //If no valid connector types were found
        return false;
    }


}

