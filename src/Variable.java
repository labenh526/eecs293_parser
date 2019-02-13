import java.util.Objects;

public final class Variable extends AbstractToken implements Token{

    //Stores the representation for the variable
    private String representation;

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
        return cache.get(Objects.requireNonNull(representation,"Variable representation cannot be null"), Variable::new);
    }
}

//Edits
/*
Reduced complexity and removed function in build
 */
