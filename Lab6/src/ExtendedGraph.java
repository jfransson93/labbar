import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


public class ExtendedGraph extends Graph {
	
    public void addEdge( int sourceName, int destName ) {
        addEdge(sourceName, destName, 0);
        addEdge(destName, sourceName, 0);
    }
	
	public List<Integer> getPath( int destName ) {
		unweighted(0);
		
		Vertex v = vertexMap.get( destName );
        if( v == null )
        	throw new NoSuchElementException( "Destination vertex not found" );
        List<Integer> l = new ArrayList<Integer>();
		return getPath( v, l );
	}
	
	private List<Integer> getPath( Vertex dest, List<Integer> l ) {
		if( dest == null )
			return l;
		
		l.add(dest.name);
		return getPath( dest.prev, l);
	}
}
