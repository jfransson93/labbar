import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author Olle & Patrik
 */
public class CollectionOps {


	/**
	 * Prints out a collections
	 * @param l collection of some type (elements must have .toString)
	 */
	public static <T> void print(Collection<T> l) {
		// StringBuilder for iterative build of string
		StringBuilder str = new StringBuilder();
		for(T elm : l) {
			// Adds "," if needed
			if(str.length() > 0)
				str.append(",");
			
			// Appends elm
			str.append(elm.toString());
		}
		
		// Prints
		System.out.format("[%s]", str);
	}
    
    /**
     * Reverses the order of elements in a list
     * @param l list that shall be reversed
     * @return l (the reversed list)
     */
	public static <T> List<T> reverse(List<T> l) {
		
		// Swap places in list for pairs ( skips the center element if odd size)
		for(int i = 0; i < l.size() / 2; i++ ) {
			// Index for element at the end of the list
			int j = l.size() - 1 - i;
			
			// Swap places in list for element i and j
			T tmp = l.get(i);
			l.set(i, l.get(j));
			l.set(j, tmp);
		}
		
		// returns list
		return l;
	}

	/**
	 * @param c1
	 * @param c2
	 * @param comp
	 * @return true if all elms of c1 is smaller than all of c2
	 */
	public static <T> boolean less(Collection<T> c1, Collection<T> c2, Comparator<T> comp) {
		// If max of c1 is less than min of c2 then all elms of c1 is strictly less than all of c2
		return comp.compare(Collections.max(c1,comp), Collections.min(c2, comp)) < 0;
	}
    
    // Example
    public static <T1,T2> Collection<T2>
    map(UnaryOp<T1,T2> functor,Collection<T1> c) 
    {
        // Determine the dynamic type of the collection
        Class<? extends Collection> cls = c.getClass();
        try {
            // Create an object of the same dynamic type as c
            Collection<T2> result = cls.newInstance();
            // Copy the elements and apply op to them
            for ( T1 x : c )
                result.add(functor.op(x));
            return result;   
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Filter a collection
     * @param functor logic for what stays and what goes
     * @param c the collection who will get filtered
     * @return a new collection with the filtered elements
     */
    public static <T1> Collection<T1> filter(UnaryPred<T1> functor, Collection<T1> c) {
    	 // Determine the dynamic type of the collection
        Class<? extends Collection> cls = c.getClass();
        try {
            // Create an object of the same dynamic type as c
            Collection<T1> result = cls.newInstance();
            // Copy only the elements who passes the filter
            for ( T1 x : c ) {
            	if(functor.pred(x))
            		result.add(x);		
        	}
            return result;   
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
