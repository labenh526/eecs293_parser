import org.junit.Test;
import static org.junit.Assert.*;

public class VariableTest {

    String s1 = "X";
    String s2 = "Y";
    String s3 = "Number";

    String nullString = null;

    Variable v1 = Variable.build(s1);
    Variable v2 = Variable.build(s2);
    Variable v3 = Variable.build(s3);


    @Test
    public void build() {
        assertEquals("X",Variable.build(s1).getRepresentation());
        assertEquals("Y",Variable.build(s2).getRepresentation());
        assertEquals("Number",Variable.build(s3).getRepresentation());
    }

    @Test(expected = NullPointerException.class)
    public void testBuildNullPointerException() {
        Variable.build(nullString);
    }


    @Test
    public void getRepresentation() {
        assertEquals("X", v1.getRepresentation());
        assertEquals("Y", v2.getRepresentation());
        assertEquals("Number", v3.getRepresentation());
    }

    @Test
    public void getType() {
        assertEquals(TerminalSymbol.VARIABLE, v1.getType());
        assertEquals(TerminalSymbol.VARIABLE, v2.getType());
        assertEquals(TerminalSymbol.VARIABLE, v3.getType());
    }

    @Test
    public void testToString() {
        assertEquals("X", v1.toString());
        assertEquals("Y", v2.toString());
        assertEquals("Number", v3.toString());
    }


    @Test
    public void setRepresentation() {
        Variable v4 = Variable.build("W");
        v4.setRepresentation("Double You");
        assertFalse(v4.getRepresentation()=="W");
        assertTrue(v4.getRepresentation()=="Double You");
    }


}