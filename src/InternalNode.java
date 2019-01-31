import java.util.ArrayList;
import java.util.List;
public final class InternalNode implements Node {

    private final List<Node> children;

    private InternalNode(List<Node> children) {
        this.children = children;
    }

    @Override
    public final List<Token> toList() {
        return toListRecursive(new ArrayList<Token>());
    }

    private List<Token> toListRecursive(List<Token> leafList) {
        for (Node node: getChildren()) {
            if (node instanceof LeafNode) {
                leafList.add(((LeafNode) node).getLeaf());
            } else {
                return ((InternalNode)node).toListRecursive(leafList);
            }
        }
        return leafList;
    }

    public List<Node> getChildren() {
        return children;
    }

    public static final InternalNode build(List<Node> children) {
        if (children == null) {
            throw new NullPointerException();
        } else {
            InternalNode internalNode = new InternalNode(children);
            return internalNode;
        }
    }



}
