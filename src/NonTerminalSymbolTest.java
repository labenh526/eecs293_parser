import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import static org.junit.Assert.assertEquals;


public class NonTerminalSymbolTest {



    Variable a = Variable.build("a");
    Variable b = Variable.build("b");
    Variable c = Variable.build("c");

    //Create connectors
    Connector openCon = Connector.build(TerminalSymbol.OPEN);
    Connector closeCon = Connector.build(TerminalSymbol.CLOSE);
    Connector plusCon = Connector.build(TerminalSymbol.PLUS);
    Connector minusCon = Connector.build(TerminalSymbol.MINUS);
    Connector timesCon = Connector.build(TerminalSymbol.TIMES);
    Connector divideCon = Connector.build(TerminalSymbol.DIVIDE);


    List<Token> list1 = Arrays.asList(a,plusCon,b,divideCon,c);
    List<Token> failList = Arrays.asList(plusCon,divideCon,b);


    @Test
    public void parseInput() {
        assertEquals("[[a],[+,[[b],[/,[c]]]]]",NonTerminalSymbol.parseInput(list1).get().toString());
    }
    @Test(expected = NoSuchElementException.class)
    public void testParseInputException() {
        NonTerminalSymbol.parseInput(failList).get();
    }
}