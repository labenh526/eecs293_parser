import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConnectorTest {

    //Create strings
    String open = "(";
    String close = ")";
    String plus = "+";
    String minus = "-";
    String times = "*";
    String divide = "/";

    //Create TerminalSymbols
    TerminalSymbol OPEN = TerminalSymbol.OPEN;
    TerminalSymbol CLOSE = TerminalSymbol.CLOSE;
    TerminalSymbol PLUS = TerminalSymbol.PLUS;
    TerminalSymbol MINUS = TerminalSymbol.MINUS;
    TerminalSymbol TIMES = TerminalSymbol.TIMES;
    TerminalSymbol DIVIDE = TerminalSymbol.DIVIDE;
    TerminalSymbol VARIABLE = TerminalSymbol.VARIABLE;
    TerminalSymbol nullTOKEN = null;

    //Create connectors
    Connector openCon;
    Connector closeCon;
    Connector plusCon;
    Connector minusCon;
    Connector timesCon;
    Connector divideCon;


    @Test
    void build() {
        assertEquals(open, Connector.build(OPEN).toString());
        assertEquals(close, Connector.build(CLOSE).toString());
        assertEquals(plus, Connector.build(PLUS).toString());
        assertEquals(minus, Connector.build(MINUS).toString());
        assertEquals(times, Connector.build(TIMES).toString());
        assertEquals(divide, Connector.build(DIVIDE).toString());

        assertThrows(NullPointerException.class, () -> {Connector.build(nullTOKEN);});
        assertThrows(IllegalArgumentException.class, () -> {Connector.build(VARIABLE);});
    }

    @Test
    void getType() {
        assertEquals(OPEN, openCon.getType());
        assertEquals(CLOSE, closeCon.getType());
        assertEquals(PLUS, plusCon.getType());
        assertEquals(MINUS, minusCon.getType());
        assertEquals(TIMES, timesCon.getType());
        assertEquals(DIVIDE, divideCon.getType());
    }

    @Test
    void testToString() {
        assertEquals(open, openCon.toString());
        assertEquals(close, closeCon.toString());
        assertEquals(plus, plusCon.toString());
        assertEquals(minus, minusCon.toString());
        assertEquals(times, timesCon.toString());
        assertEquals(divide, divideCon.toString());
    }


}