import symbols.*;

public class BinaryExpression implements Expression {
    private SymbolTypes op;
    private Expression leftOperand;
    private Expression rightOperand;

    public BinaryExpression( Expression leftOperand, SymbolTypes op, Expression rightOperand ) {
        this.leftOperand = leftOperand;
        this.op = op;
        this.rightOperand = rightOperand;
    }

	@Override
	public int getValue() {
		
		switch( op ) {
			case EXP:
				return (int) Math.pow(leftOperand.getValue(), rightOperand.getValue());
			case MULT:
				return leftOperand.getValue() * rightOperand.getValue();
			case DIV:
				if( rightOperand.getValue() == 0) {
					// Should maybe throw exception, but not specified in interface or assignment..
					System.out.println("\nIllegal division by zero!");
					return 0;
				}
				return leftOperand.getValue() / rightOperand.getValue();
			case MOD:
				if( rightOperand.getValue() == 0) {
					// Should maybe throw exception, but not specified in interface or assignment..
					System.out.println("\nIllegal modulus by zero!");
					return 0;
				}
				return leftOperand.getValue() % rightOperand.getValue();
			case PLUS:
				return leftOperand.getValue() + rightOperand.getValue();
			case MINUS:
				return leftOperand.getValue() - rightOperand.getValue();
		}
		
		return 0;
	}

	@Override
	public void prettyInfix() {
		System.out.print('(');
		leftOperand.prettyInfix();
		System.out.print( op.opChar );
		rightOperand.prettyInfix();
		System.out.print(')');
	}

	@Override
	public void prettyPostfix() {
		System.out.print('(');
		leftOperand.prettyPostfix();
		rightOperand.prettyPostfix();
		System.out.print( op.opChar );
		System.out.print(')');
		
	}

	@Override
	public void prettyPrefix() {
		System.out.print('(');
		System.out.print( op.opChar );
		leftOperand.prettyPrefix();
		rightOperand.prettyPrefix();
		System.out.print(')');
	}
}
