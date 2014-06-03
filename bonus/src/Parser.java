import java.io.Reader;
import symbols.*;

/**
 * Parse simple binary expressions with parantheses
 * 
 * @author LET375-16
 * @version 2014-06-03
 */
public class Parser {
    private SymbolReader symbolReader;
    
    public Parser( Reader inStream ) {
        symbolReader = new SymbolReader(inStream);
    }
    
    public Expression parse() throws SyntaxError {
        return parseExpression(symbolReader.readNextSymbol());
    }

	private Expression parseExpression(Symbol sym) throws SyntaxError {
		
		switch( sym.getType() ) {
			case LPAREN:
				return parseBinaryExpression(symbolReader.readNextSymbol());
			case VALUE:
				return new ConstantExpression( sym.getValue() );
			default:
				throw new SyntaxError("SyntaxError: number or '(' expected");
		}
		
	}

	private Expression parseBinaryExpression(Symbol sym) throws SyntaxError {
		// Parse left expression
		Expression left = parseExpression(sym);
		
		// Parse operator
		SymbolTypes op = symbolReader.readNextSymbol().getType();
		if( !SymbolTypes.isBinary(op) )
			throw new SyntaxError("SyntaxError: illegal operator encountered");
		
		// Parse right expression
		Expression right = parseExpression(symbolReader.readNextSymbol());
		
		// Parse end of expression ')'
		if( symbolReader.readNextSymbol().getType() != SymbolTypes.RPAREN )
			throw new SyntaxError("SyntaxError: ')' expected");
		
		return new BinaryExpression(left, op, right);
	}

}
