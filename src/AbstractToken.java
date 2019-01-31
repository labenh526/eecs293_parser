import javax.swing.*;

public abstract class AbstractToken implements Token{
    private final TerminalSignal type;

    public AbstractToken(TerminalSignal type) {
        this.type = type;
    }

    @Override
    public final boolean matches(TerminalSignal type) {
        return this.type == type;
    }

    @Override
    public TerminalSignal getType() {
        return type;
    }
}
