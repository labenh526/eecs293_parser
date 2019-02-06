import java.util.List;

interface Symbol {

    //parse input into a node, leaving a remainder if necessary
    public ParseState parse(List<Token> input);

}
