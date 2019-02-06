import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public final class InternalNode implements Node {

    private final List<Node> children;

    private String childString = "";

    private InternalNode(List<Node> children) {
        this.children = Collections.unmodifiableList(children);
    }

    @Override
    public final List<Token> toList() {
        List<Token> tempList = new ArrayList<>();
        for (Node child : children) {
            tempList.addAll(child.toList());
        }
        return tempList;
    }

    public List<Node> getChildren() {
        return children;
    }

    public static final InternalNode build(List<Node> children) {
        /* TODO: Immutable list */
        if (children == null) {
            throw new NullPointerException();
        } else {
            InternalNode internalNode = new InternalNode(children);
            return internalNode;
        }
    }

    @Override
    public String toString() {
        if (childString.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append('[');
            for (Node node : children) {
                sb.append(node.toString());
                sb.append(',');
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append("]");
            childString = sb.toString();
        }
        return childString;
    }



}
