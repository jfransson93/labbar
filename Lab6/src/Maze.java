import java.util.ArrayList;
import java.util.List;


public class Maze extends Board {
	
	private ExtendedGraph graph;
	
    public Maze( int rows, int cols ) {
    	super(rows,cols);
    }
    
    public void create() {

    	// Resets maze
    	graph = new ExtendedGraph();
    	
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
    			int id1 = getCellId(p1);
    			int id2 = getCellId(p2);
    			int s1 = set.find(id1);
    			int s2 = set.find(id2);
    			
    			if( s1 != s2 ) {
    				
    				set.union( s1, s2 );
        			numberOfSets--;
        			
        			graph.addEdge(id1, id2, 0);
    				graph.addEdge(id2, id1, 0);
        			
        			// Notifies observers
        			Pair<Integer, Point.Direction> p = new Pair<Integer, Point.Direction>(id1, dir);
        			setChanged();
        			notifyObservers(p);
    			}
    		}
    	}
    }
    
    
    
    public void search() {
    	// Fetches the shortest path
    	List<Integer> path = graph.getPath(maxCell - 1);
    	
    	for( Integer cellId : path ) {
    		// Notifies observers
        	setChanged();
        	notifyObservers(cellId);
    	}
    }
    
}
