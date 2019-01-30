public interface Token {

    public enum TerminalSignal{VARIABLE, PLUS, MINUS, TIMES, DIVIDE, OPEN, CLOSE};

    public TerminalSignal getType();
    public boolean matches(TerminalSignal type);
}
