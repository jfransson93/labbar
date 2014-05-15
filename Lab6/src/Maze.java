import java.util.ArrayList;
import java.util.List;


public class Maze extends Board {
	
	public List<Pair<Point, Point>> connectedCells;
	public List<Integer> path;
	
	private ExtendedGraph graph;
	
    public Maze( int rows, int cols ) {
    	super(rows,cols);
    }
    
    public void create() {

    	// Resets maze
    	reset();
    	
    	// Disjoint set to help create the maze
    	DisjointSets set = new DisjointSets(maxCell);
    	int numberOfSets = maxCell;
    	
    	while( numberOfSets > 1 ) {
    		int cellId = (int) (Math.random() * maxCell); // Random cell
    		
    		// Initializes two points
    		Point p1 = new Point(getRow(cellId), getCol(cellId));
    		Point p2 = new Point(getRow(cellId), getCol(cellId));
    		
    		// Select a random direction
    		int random = (int) (Math.random() * 4);
    		Point.Direction dir;
    		if( random == 0 ) {
    			dir = Point.Direction.UP;
    		} else if( random == 1) {
    			dir = Point.Direction.RIGHT;
    		} else if( random == 2) {
    			dir = Point.Direction.DOWN;
    		} else {
    			dir = Point.Direction.LEFT;
    		}
    		
    		// Moves p2 one step from p1 in a random direction
    		p2.move(dir);
    		
    		// Check if p2 is a valid point and then investigates
    		// if p1 and p2 should be connected, and do so if valid
    		if( isValid(p2) ) {
    			int s1 = set.find(getCellId(p1));
    			int s2 = set.find(getCellId(p2));
    			
    			if( s1 != s2 ) {
    				connectCells( p1, p2 );
    				set.union( s1, s2 );
        			numberOfSets--;
    			}
    		}
    	}
    	
    	// Notifies observers
    	setChanged();
    	notifyObservers(this);
    }
    
    
    
    public void search() {
    	// Fetches the shortest path
    	path = graph.getPath(maxCell - 1);
    	
    	// Notifies observers
    	setChanged();
    	notifyObservers(this);
    }
    
    private void connectCells( Point p1, Point p2 ) {
		Pair<Point, Point> p = new Pair<Point, Point>(p1, p2);
		connectedCells.add(p);
		
		int a = getCellId(p1);
		int b = getCellId(p2);
		graph.addEdge(a, b, 0);
		graph.addEdge(b, a, 0);
    }
    
    private void reset() {
    	connectedCells = new ArrayList<Pair<Point, Point>>();
		graph = new ExtendedGraph();
		path  = null;
    }
    
}
