import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class SymbolSequenceTest {

    String s1 = "X";
    String s2 = "Y";
    String s3 = "Number";
    String nullString = null;

    //Create strings
    String open = "(";
    String close = ")";
    String plus = "+";
    String minus = "-";
    String times = "*";
    String divide = "/";

    Variable v1 = Variable.build(s1);
    Variable v2 = Variable.build(s2);
    Variable v3 = Variable.build(s3);

    //Create TerminalSymbols
    TerminalSymbol OPEN = TerminalSymbol.OPEN;
    TerminalSymbol CLOSE = TerminalSymbol.CLOSE;
    TerminalSymbol PLUS = TerminalSymbol.PLUS;
    TerminalSymbol MINUS = TerminalSymbol.MINUS;
    TerminalSymbol TIMES = TerminalSymbol.TIMES;
    TerminalSymbol DIVIDE = TerminalSymbol.DIVIDE;
    TerminalSymbol VARIABLE = TerminalSymbol.VARIABLE;
    TerminalSymbol nullTOKEN = null;

    TerminalSymbol[] symbolArray1 = {OPEN, PLUS, MINUS, TIMES, DIVIDE};
    TerminalSymbol[] symbolArray2 = {OPEN, PLUS, MINUS};

    List<Symbol> list1 = new ArrayList<>(Arrays.asList(symbolArray1));
    List<Symbol> list2 = new ArrayList<>(Arrays.asList(symbolArray2));
    List<Symbol> nullList;

    //Create connectors
    Connector openCon = Connector.build(OPEN);
    Connector closeCon = Connector.build(CLOSE);
    Connector plusCon = Connector.build(PLUS);
    Connector minusCon = Connector.build(MINUS);
    Connector timesCon = Connector.build(TIMES);
    Connector divideCon = Connector.build(DIVIDE);

    Connector[] connectorArray1 = {openCon, closeCon, plusCon, minusCon,timesCon, divideCon};
    Connector[] connectorArray2 = {openCon, closeCon, plusCon, minusCon};

    List<Token> tokenList1 = new ArrayList<>(Arrays.asList(connectorArray1));
    List<Token> tokenList2 = new ArrayList<>(Arrays.asList(connectorArray2));



    @Test
    public void build() {
        assertEquals("[OPEN, PLUS, MINUS, TIMES, DIVIDE]", SymbolSequence.build(list1).toString());
        assertEquals("[OPEN, PLUS, MINUS]", SymbolSequence.build(list2).toString());
    }

    @Test(expected = NullPointerException.class)
    public void testBuildNullPointerException() {
        SymbolSequence.build(nullList);
    }

    @Test
    public void buildMultiple() {
        assertEquals("[OPEN, PLUS, MINUS, TIMES, DIVIDE]", SymbolSequence.build(OPEN, PLUS, MINUS, TIMES, DIVIDE).toString());
        assertEquals("[OPEN, PLUS, MINUS]", SymbolSequence.build(OPEN, PLUS, MINUS).toString());
    }
    @Test(expected = NullPointerException.class)
    public void testBuildMultipleNullPointerException() {
        SymbolSequence.build(OPEN, PLUS, null,  MINUS);
    }

    SymbolSequence ss1 = SymbolSequence.build(OPEN, PLUS, MINUS, TIMES, DIVIDE);
    SymbolSequence ss2 = SymbolSequence.build(OPEN, PLUS, MINUS);

    @Test
    public void testToString() {
        assertEquals("[OPEN, PLUS, MINUS, TIMES, DIVIDE]", ss1.toString());
        assertEquals("[OPEN, PLUS, MINUS]", ss2.toString());
    }

    @Test
    public void match() {
        assertTrue(ss1.match(tokenList1).hasNoRemainder());
        assertFalse(ss1.match(tokenList2).hasNoRemainder());
    }
}