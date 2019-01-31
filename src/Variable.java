public final class Variable extends AbstractToken implements  Token{

    private String representation = "";

    private Variable(String representation) {
        super(TerminalSignal.VARIABLE);
        this.representation = representation;
    }

    @Override
    public TerminalSignal getType() {
        return TerminalSignal.VARIABLE;
    }

    @Override
    public String toString() {
        return representation;
    }

    public final String getRepresentation() {
        return representation;
    }

    public static final Variable build(String representation) {
        Variable newRep = new Variable(representation);
        return newRep;
    }
}
