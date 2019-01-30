public final class Variable extends AbstractToken implements  Token{

    private String representation = "";

    public Variable(String representation) {
        this.representation = representation;
    }

    @Override
    public TerminalSignal getType() {
        return null;
    }

    @Override
    public String toString() {
        return "f";
    }

    public final String getRepresentation() {
        return representation;
    }

    public final void setRepresentation(String representation) {
        this.representation = representation;
    }

    public static final Variable build(String representation) {
        return null;
    }
}
