import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

final class SymbolSequence {

    private final List<Symbol> production;

    static final SymbolSequence EPSILON = new SymbolSequence(new ArrayList<Symbol>());

    private SymbolSequence(List<Symbol> production){
        this.production = production;
    }

    public static SymbolSequence build(List<Symbol> production){
        return new SymbolSequence(Objects.requireNonNull(production, "Production cannot be null"));
    }

    static final SymbolSequence build(Symbol... symbols){
        ArrayList<Symbol> production = new ArrayList<>();
        for (Symbol symbol: symbols) {
            if(symbol == null)
                throw new NullPointerException("Symbols cannot be null");
            production.add(symbol);
        }
        return new SymbolSequence(production);
    }

    @Override
    public String toString(){
        return production.toString();
    }

    ParseState match(List<Token> input){
        List<Token> remainder = Objects.requireNonNull(input, "Input cannot be null");
        List<Node> children = new ArrayList<>();
        for (Symbol symbol:production) {
            ParseState temp = symbol.parse(remainder);
            if(temp == ParseState.FAILURE){
                return ParseState.FAILURE;
            }
            children.add(temp.getNode());
            remainder = temp.getRemainder();
        }
        return ParseState.build(InternalNode.build(children),remainder);
    }


}
