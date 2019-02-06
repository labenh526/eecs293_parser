public interface Token {

    enum TerminalSymbol{VARIABLE, PLUS, MINUS, TIMES, DIVIDE, OPEN, CLOSE};

    TerminalSymbol getType();

    boolean matches(TerminalSymbol type);

}

