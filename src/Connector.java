public final class Connector extends AbstractToken{

    private Connector(TerminalSignal type) {
        super(type);
    }

    @Override
    public String toString() {
        String rep;
        switch (super.getType()) {
            case PLUS:
                rep = "+";
                break;
            case MINUS:
                rep = "-";
                break;
            case TIMES:
                rep = "*";
                break;
            case DIVIDE:
                rep = "/";
                break;
            case OPEN:
                rep = "(";
                break;
            case CLOSE:
                rep = ")";
                break;
            default:
                rep = "";
                break;
                //TODO: throw exception here
        }
        return rep;
    }

    public static final Connector build(TerminalSignal type) {
        Connector connector = new Connector(type);
        return connector;
    }


}
