import java.util.*;

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

    @Override
    public boolean isFruitful() {
        return !children.isEmpty();
    }

    @Override
    public boolean isOperator() {
        return false;
    }

    @Override
    public boolean isStartedByOperator() {
        return getChildren().get(0).isOperator();
    }

    public static final InternalNode build(List<Node> children) {
        return new InternalNode(Objects.requireNonNull(children, "children must not be null."));
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
            if(!children.isEmpty()) {
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append("]");
            childString = sb.toString();
        }
        return childString;
    }

    public static class Builder{
        private List<Node> children = new ArrayList<>();

        public boolean addChild(Node node){
            return children.add(node);
        }

        public Builder simplify(){
           List<Node> newChildren = new ArrayList<>();
           boolean isPreviousOperator = false;
            for (Node child: children) {
                if(child.isFruitful())
                    if(child instanceof InternalNode && !child.getChildren().isEmpty() && child.isStartedByOperator() && !isPreviousOperator){
                        newChildren.addAll(child.getChildren());
                        isPreviousOperator=true;
                    }
                    else {
                        newChildren.add(child);
                        isPreviousOperator=false;
                    }
            }
            children=newChildren;

            if(isSingleInternalNode()){
                children = children.get(0).getChildren();
            }

            return this;
            }


        //Helper method
        private boolean isSingleInternalNode(){
            return children.size()==1 && children.get(0) instanceof InternalNode;
        }

        public InternalNode build(){
            this.simplify();
            return new InternalNode(children);
        }
    }

}

//Edits
/*

 */
