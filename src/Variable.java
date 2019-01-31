import java.util.function.Function;

public final class Variable extends AbstractToken implements Token{

    //Stores the representation for the variable
    private String representation = null;

    //Stores cache of all created variables
    static Cache<String, Variable> cache;

    // private Constructor to create variable
    private Variable(String representation) {
        this.setRepresentation(representation);
    }

    public final String getRepresentation() {
        return representation;
    }

    //All variables will always return the type VARIABLE
    @Override
    public TerminalSignal getType() {
        return TerminalSignal.VARIABLE;
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
