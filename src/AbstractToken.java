public abstract class AbstractToken implements Token{

    @Override
    public abstract TerminalSignal getType();


    public final boolean matches(TerminalSignal type){
        if (this.getType()==type){
            return true;
        }
        else {
            return false;
        }
    };
}
