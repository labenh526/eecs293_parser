import java.util.List;
public final class InternalNode implements Node {

    private List<Node> children = null;

    private InternalNode(List<Node> children) {
        this.children = children;
    }

    @Override
    public List<Token> toList() {
        return null;
    }

    public List<Node> getChildren() {
        return children;
    }

}
