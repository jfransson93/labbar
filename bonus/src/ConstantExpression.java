public class ConstantExpression implements Expression {
    private int value;
    public ConstantExpression( int value) {
        this.value = value;
    }
	@Override
	public int getValue() {
		return this.value;
	}
	@Override
	public void prettyInfix() {
		System.out.print( getValue() );
	}
	@Override
	public void prettyPostfix() {
		System.out.print( getValue() + " " );
	}
	@Override
	public void prettyPrefix() {
		System.out.print( " " + getValue() );
	}
}
