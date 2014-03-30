/**
 * Generic single buffer
 * @author Olle & Patrik
 * @param <T>
 */
public class SingleBuffer<T> {

	private T element = null;
	
	/**
	 * Adds a value to the buffer ( if empty )
	 * @param value
	 * @return true if successful, false if buffer already full
	 */
	public boolean put(T value) {
		
		// Returns false if the buffer is full
		if(!is_empty())
			return false;
		
		// Else stores the value in buffer
		element = value;
		return true;
	}
	
	/**
	 * Returns and removes element from the buffer
	 * @return null if empty, else the element currently in the buffer
	 */
	public T get() {
		// Saves value of element in temp and then empties element,
		// works also when buffers empty.
		T temp  = element;
		element = null;
		return temp;
	}
	
	/**
	 * @return true if buffer is empty, else false
	 */
	private boolean is_empty() {
		return element == null;
	}
}
