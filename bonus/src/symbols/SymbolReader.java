package symbols;

import java.io.PushbackReader;
import java.io.Reader;
import java.io.IOException;

/**
 * Symbol Reader class. (This is a variation of Weiss's Tokenizer class.)
 * 
 * @author Uno Holmer 
 * @version 2008-05-12
 */
// CONSTRUCTION: with a Reader object
// ******************PUBLIC OPERATIONS*********************************************
// Symbol readNextSymbol()   --> Get next symbol from the input stream
//     Converts characters in the concrete syntax to symbols in the abstract syntax
// ********************************************************************************

public class SymbolReader {
    private PushbackReader inStream;      // The input stream
    private char ch;                      // The most recently read input character

    /**
     * Constructor.
     * @param inStream the stream containing a program.
     */
    public SymbolReader(Reader inStream) {
        ch = '\0';
        this.inStream = new PushbackReader(inStream);
    }
    
    // Find the next symbol, skipping blanks and new lines, and return it.
    // Print error message if input is unrecognized.
    public Symbol readNextSymbol() {
        int value;
        
        // Skip blanks
        while( nextChar() && ( ch == ' ' || ch == '\n' ) )
            ;
        putBackChar();   
        if( nextChar() ) {
            switch( ch ) {
                case '^': return new Symbol(SymbolTypes.EXP);
                case '/': return new Symbol(SymbolTypes.DIV);
                case '%': return new Symbol(SymbolTypes.MOD);
                case '*': return new Symbol(SymbolTypes.MULT);
                case '(': return new Symbol(SymbolTypes.LPAREN);
                case ')': return new Symbol(SymbolTypes.RPAREN);
                case '+': return new Symbol(SymbolTypes.PLUS);
                case '-': return new Symbol(SymbolTypes.MINUS);
                default:
                    if ( Character.isDigit(ch) ) {
                        value = getInt();
                        if( value == -1 ) {
                            //cerr << "Parse error" << endl;
                            return new Symbol(SymbolTypes.EOL);
                        } else
                            return new Symbol(SymbolTypes.VALUE,value);
                    } else
                        return new Symbol(SymbolTypes.ERROR);
            }   
        }
        return new Symbol(SymbolTypes.EOL);
    }
    
            
    /**
     * Return an integer read from the input stream
     * Precondition: First digit is already read into ch
     */
    private int getInt() {
        String digits = "" + ch;         
        while ( nextChar() ) {
            if( ! Character.isDigit(ch) ) {
                putBackChar();
                break;
            }
            digits += ch;
        }
        return Integer.parseInt(digits);
    }

    /**
     * nextChar sets ch based on the next character in the input stream.
     * putBackChar puts the character back onto the stream.
     * It should only be used once after a nextChar.
     * Both routines adjust currentLine if necessary.
     */
    private boolean nextChar( ) {
        try {
            int readVal = inStream.read();
            if( readVal == -1 )
                return false;
            ch = (char) readVal;
            return true;
        }
        catch( IOException e ) { 
            return false; 
        }
    }

    private void putBackChar( ) {
        try { 
            inStream.unread( (int) ch ); 
        }
        catch( IOException e ) { }
    }
}

