import java.util.List;
public final class LeafNode implements Node{

    private /*final*/ Token leaf;

    private LeafNode(Token leaf) {
        this.leaf = leaf;
    }

    public Token getLeaf() {
        return leaf;
    }

    public static final LeafNode build(Token newLeaf) {
        return null;
    }

    @Override
    public List<Token> toList() {
        return null;
    }

    @Override
    public String toString() {
        return null;
    }
}
