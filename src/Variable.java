import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public final class Variable extends AbstractToken implements Token{

    //Stores the representation for the variable
    private String representation = null;

    //Stores cache of all created variables
    //cache works properly if Cache constructor is public
    private static Cache<String, Variable> cache = new Cache<>();

    // private Constructor to create variable
    private Variable(String representation) {
        this.setRepresentation(representation);
    }

    public final String getRepresentation() {
        return representation;
    }

    //All variables will always return the type VARIABLE
    @Override
    public TerminalSymbol getType() {
        return TerminalSymbol.VARIABLE;
    }

    @Override
    public String toString() {
        return this.getRepresentation();
    }

    public final void setRepresentation(String representation) {
        this.representation = representation;
    }



    public static final Variable build(String representation) {

        Function<String, Variable> construct = (String key) -> new Variable(key);

        //Error check for null strings
        if (representation == null){
            throw new NullPointerException("Variable representation cannot be null");
        }
        return cache.get(representation, construct);
    }
}
