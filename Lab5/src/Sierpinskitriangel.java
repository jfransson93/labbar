
public class Sierpinskitriangel extends Flake {
	public void draw(Turtle turtle,int n,double size) {
		this.turtle = turtle;
		turtle.turnTo(0.0);
		turtle.turn(-60.0);
		drawSide(n,size);
	}

	private void drawSide(int n,double size) {
		if ( n <= 0 ) {
			turtle.walk(size);
			turtle.turn(120);
			turtle.walk(size);
			turtle.turn(120);
			turtle.walk(size);
			turtle.turn(120);
		} else {
			double l = size/2.0;
			drawSide(n-1,l);
			turtle.jump(l);
			drawSide(n-1,l);
			turtle.turn(120.0);
			turtle.jump(l);
			turtle.turn(-120.0);
			drawSide(n-1,l);
			turtle.turn(-120.0);
			turtle.jump(l);
			turtle.turn(120.0);
		}
	}
}
