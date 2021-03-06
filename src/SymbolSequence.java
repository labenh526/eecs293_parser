import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

final class SymbolSequence {

    private final List<Symbol> production;

    static final SymbolSequence EPSILON = new SymbolSequence(new ArrayList<>());

    private SymbolSequence(List<Symbol> production){
        this.production = production;
    }

    public static SymbolSequence build(List<Symbol> production){
        return new SymbolSequence(Objects.requireNonNull(production, "Production cannot be null"));
    }

    static final SymbolSequence build(Symbol... symbols){
        ArrayList<Symbol> production = new ArrayList<>();
        for (Symbol symbol: symbols) {
            production.add(Objects.requireNonNull(symbol,"Symbols cannot be null"));
        }
        return new SymbolSequence(production);
    }

    @Override
    public String toString(){
        return production.toString();
    }

    ParseState match(List<Token> input){
        List<Token> remainder = Objects.requireNonNull(input, "Input cannot be null");
        InternalNode.Builder builder = new InternalNode.Builder();
        //List<Node> children = new ArrayList<>();
        for (Symbol symbol:production) {
            ParseState temp = symbol.parse(remainder);
            if(!temp.isSuccess()){
                return ParseState.FAILURE;
            }
            builder.addChild(temp.getNode());
            remainder = temp.getRemainder();
        }
        return ParseState.build(builder.build(),remainder);
    }
}

//Edits
/*
 */
