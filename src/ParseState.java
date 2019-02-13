import java.util.List;
import java.util.Objects;

public class ParseState {
    private final boolean success;

    private final Node node;

    private final List<Token> remainder;

    final static ParseState FAILURE = new ParseState(false,null,null);

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
        return Objects.requireNonNull(remainder, "remainder cannot be null");
    }

    final boolean hasNoRemainder(){
        try {
            return this.getRemainder().isEmpty();
        }
        catch (NullPointerException e){
            return true;
        }
    }

    public static ParseState build(Node node, List<Token> remainder){
        return new ParseState(true, Objects.requireNonNull(node,"Node cannot be null"),
                                           Objects.requireNonNull(remainder, "remainder cannot be null"));
    }
}

//Edits
/*
Moved FAILURE to top of list with other class fields
Simplified build
 */
