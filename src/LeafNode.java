import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public final class LeafNode implements Node{

    private final Token leaf;

    private LeafNode(Token leaf) {
        this.leaf = leaf;
    }

    public Token getLeaf() {
        return leaf;
    }

    public static final LeafNode build(Token newLeaf) {
        return new LeafNode(Objects.requireNonNull(newLeaf,"Token cannot be null"));
    }

    @Override
    public List<Token> toList() {
        return Arrays.asList(getLeaf());
    }

    @Override
    public String toString() {
        return leaf.toString();
    }
}

//Edits
/*
Added exception message to LeafNode
Shortened toList to only one line
Simplified build
 */