import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

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


    @Test
    public void parse() {
    }

    @Test
    public void parseInput() {
        Node root = NonTerminalSymbol.parseInput(list1).get();

    }
}