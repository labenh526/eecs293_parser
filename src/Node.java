import java.util.List;
import java.util.Optional;

public interface Node {

    List<Token> toList();
    List<Node> getChildren();
    boolean isFruitful();
    boolean isOperator();
    boolean isStartedByOperator();
    Optional<Node> firstChild();
    boolean isSingleLeafParent();
}
