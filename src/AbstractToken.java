public abstract class AbstractToken implements Token{

    @Override
    public abstract TerminalSymbol getType();


    public final boolean matches(TerminalSymbol type){
        return this.getType() == type;
    };
}
