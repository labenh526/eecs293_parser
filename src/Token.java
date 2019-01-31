public interface Token {

    enum TerminalSignal{VARIABLE, PLUS, MINUS, TIMES, DIVIDE, OPEN, CLOSE};

    TerminalSignal getType();

    boolean matches(TerminalSignal type);

}

