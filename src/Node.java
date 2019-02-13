import java.util.List;

public interface Node {

    List<Token> toList();
    List<Node> getChildren();
    boolean isFruitful();

}
