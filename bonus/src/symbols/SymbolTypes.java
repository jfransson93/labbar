package symbols;

// For more information about enumeration types, 
// refer to Skansholm, Java direkt, chap. 3.8

/**
 * This class define the basic abstract symbol alphabet and, if applicable,
 * corresponding characters in the concrete input syntax. 
 * 
 * @author Uno Holmer 
 * @version 2008-05-12
 */

public enum SymbolTypes { 
    EXP('^'), 
    MULT('*'), 
    DIV('/'), 
    MOD('%'), 
    PLUS('+'),
    MINUS('-'), 
    VALUE, 
    LPAREN, 
    RPAREN, 
    EOL, 
    ERROR;

    public final char opChar;
    
    private SymbolTypes() {
        opChar = '\0';
    }
    
    private SymbolTypes(char opChar) {
        this.opChar = opChar;
    }
    
    public static boolean isBinary(SymbolTypes x) {
        return x.compareTo(EXP)>= 0 && x.compareTo(MINUS) <= 0;
    }
}