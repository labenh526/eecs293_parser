import org.junit.Test;
import static org.junit.Assert.*;

public class LeafNodeTest {

    TerminalSymbol OPEN = TerminalSymbol.OPEN;
    TerminalSymbol CLOSE = TerminalSymbol.CLOSE;

    String s1 = "variable 1";
    String s2 = "variable 2";

    Variable v1 = Variable.build(s1);
    Variable v2 = Variable.build(s2);
    Connector c1 = Connector.build(OPEN);
    Connector c2 = Connector.build(CLOSE);

    LeafNode ln1 = LeafNode.build(v1);
    LeafNode ln2 = LeafNode.build(v2);
    LeafNode ln3 = LeafNode.build(c1);
    LeafNode ln4 = LeafNode.build(c2);

    @Test
    public void build() {
        assertEquals(v1,ln1.getLeaf());
        assertEquals(v2,ln2.getLeaf());
        assertEquals(c1,ln3.getLeaf());
        assertEquals(c2,ln4.getLeaf());
    }

    @Test(expected = NullPointerException.class)
    public void testBuildNullPointerException() {
        LeafNode.build(null);
    }
    @Test
    public void getLeaf() {
        assertEquals(v1,ln1.getLeaf());
        assertEquals(v2,ln2.getLeaf());
        assertEquals(c1,ln3.getLeaf());
        assertEquals(c2,ln4.getLeaf());
    }

    @Test
    public void toList() {
        assertEquals(v1,ln1.toList().get(0));
        assertEquals(v2,ln2.toList().get(0));
        assertEquals(c1,ln3.toList().get(0));
        assertEquals(c2,ln4.toList().get(0));
    }

    @Test
    public void testToString() {
        assertEquals(s1,ln1.toString());
        assertEquals(s2,ln2.toString());
        assertEquals("(",ln3.toString());
        assertEquals(")",ln4.toString());
    }
}