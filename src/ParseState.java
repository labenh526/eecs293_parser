import java.util.List;

public class ParseState {
    private final boolean success;

    private final Node node;

    private final List<Token> remainder;

    private ParseState(boolean success, Node node, List<Token> remainder){
        this.success=success;
        this.node=node;
        this.remainder=remainder;

    }

    public boolean isSuccess() {
        return success;
    }

    public Node getNode() {
        return node;
    }

    public List<Token> getRemainder() {
        return remainder;
    }

    final boolean hasNoRemainder(){
        return this.getRemainder().isEmpty();
    }

    final static ParseState FAILURE = new ParseState(false,null,null);

    public ParseState build(Node node, List<Token> remainder){
        if(node==null){
            throw new NullPointerException("Node cannot be null");
        }
        if(remainder==null) {
            throw new NullPointerException("remainder cannot be null");
        }
        return new ParseState(true, node, remainder);
    }



}
