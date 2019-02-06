import org.junit.Test;
import static org.junit.Assert.*;

public class ConnectorTest {

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
    Connector openCon = Connector.build(OPEN);
    Connector closeCon = Connector.build(CLOSE);
    Connector plusCon = Connector.build(PLUS);
    Connector minusCon = Connector.build(MINUS);
    Connector timesCon = Connector.build(TIMES);
    Connector divideCon = Connector.build(DIVIDE);


    @Test
    public void build() {
        assertEquals(open, Connector.build(OPEN).toString());
        assertEquals(close, Connector.build(CLOSE).toString());
        assertEquals(plus, Connector.build(PLUS).toString());
        assertEquals(minus, Connector.build(MINUS).toString());
        assertEquals(times, Connector.build(TIMES).toString());
        assertEquals(divide, Connector.build(DIVIDE).toString());

    }
    @Test(expected = NullPointerException.class)
    public void testBuildNullPointerException() {
        Connector.build(nullTOKEN);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testBuildIllegalArgException() {
        Connector.build(VARIABLE);
    }



    @Test
    public void getType() {
        assertEquals(OPEN, openCon.getType());
        assertEquals(CLOSE, closeCon.getType());
        assertEquals(PLUS, plusCon.getType());
        assertEquals(MINUS, minusCon.getType());
        assertEquals(TIMES, timesCon.getType());
        assertEquals(DIVIDE, divideCon.getType());
    }

    @Test
    public void testToString() {
        assertEquals(open, openCon.toString());
        assertEquals(close, closeCon.toString());
        assertEquals(plus, plusCon.toString());
        assertEquals(minus, minusCon.toString());
        assertEquals(times, timesCon.toString());
        assertEquals(divide, divideCon.toString());
    }


}