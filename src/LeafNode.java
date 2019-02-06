import java.util.ArrayList;
import java.util.List;
public final class LeafNode implements Node{

    private final Token leaf;

    private LeafNode(Token leaf) {
        this.leaf = leaf;
    }

    public Token getLeaf() {
        return leaf;
    }

    public static final LeafNode build(Token newLeaf) {
        if (newLeaf == null) {
            throw new NullPointerException();
        } else {
            LeafNode leaf = new LeafNode(newLeaf);
            return leaf;
        }
    }

    @Override
    public List<Token> toList() {
        List<Token> leafTree = new ArrayList<>();
        leafTree.add(getLeaf());
        return leafTree;
    }

    @Override
    public String toString() {
        return leaf.toString();
    }
}
