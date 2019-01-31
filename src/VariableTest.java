import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VariableTest {

    String s1 = "X";
    String s2 = "Y";
    String s3 = "Number";

    String nullString = null;

    Variable v1, v2, v3;

    @Test
    void build() {
        assertEquals("X",Variable.build(s1).getRepresentation());
        assertEquals("Y",Variable.build(s2).getRepresentation());
        assertEquals("Number",Variable.build(s3).getRepresentation());
        assertThrows(NullPointerException.class, () -> {Variable.build(nullString);});
    }

    @Test
    void getRepresentation() {
        assertEquals("X", v1.getRepresentation());
        assertEquals("Y", v2.getRepresentation());
        assertEquals("Number", v3.getRepresentation());
    }

    @Test
    void getType() {
        assertEquals(Token.TerminalSignal.VARIABLE, v1.getType());
        assertEquals(Token.TerminalSignal.VARIABLE, v2.getType());
        assertEquals(Token.TerminalSignal.VARIABLE, v3.getType());
    }

    @Test
    void testToString() {
        assertEquals("X", v1.toString());
        assertEquals("Y", v2.toString());
        assertEquals("Number", v3.toString());
    }


    @Test
    void setRepresentation() {
        v2.setRepresentation("Why");
        assertFalse(v2.getRepresentation()=="Y");
        assertTrue(v2.getRepresentation()=="Why");
    }


}