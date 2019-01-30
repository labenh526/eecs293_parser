public abstract class AbstractToken implements Token{

    public final boolean matches(TerminalSignal type) {
        return false;
    }
}
