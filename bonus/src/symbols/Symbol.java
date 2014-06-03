package symbols;

/**
 * This class models a lexical symbol. 
 * If the type is VALUE it will also carry an integer value.
 * 
 * @author Uno Holmer 
 * @version 2008-05-06
 */

public class Symbol {
    SymbolTypes type;
    int value;
    
    public Symbol(SymbolTypes type,int value) {
        this.type = type;
        this.value = value;
    }
    
    public Symbol(SymbolTypes type) {
        this(type,0);
    }

    public SymbolTypes getType() {
        return type; 
    }
    
    public int getValue() { 
        return value; 
    }
}
