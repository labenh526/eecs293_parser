import org.junit.Test;

import static org.junit.Assert.*;

class LeafNodeTest {

    Token leaf1, leaf2, leaf3;

    LeafNode lN1, lN2, lN3;


    @Test
    void build() {
        //assertThrows(NullPointerException.class, () -> {LeafNode.build(null);});
        assertEquals(leaf1,LeafNode.build(leaf1).getLeaf());
        assertEquals(leaf2,LeafNode.build(leaf2).getLeaf());
        assertEquals(leaf3,LeafNode.build(leaf3).getLeaf());
    }

    @Test
    void getLeaf() {
        lN1 = LeafNode.build(leaf1);
        lN2 = LeafNode.build(leaf2);
        lN3 = LeafNode.build(leaf3);
        assertEquals(leaf1, lN1.getLeaf());
        assertEquals(leaf2, lN2.getLeaf());
        assertEquals(leaf3, lN3.getLeaf());
    }

    @Test
    void toList() {

    }

    @Test
    void testToString() {
    }
}