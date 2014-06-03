
public class BonusUppg { 
	 
	/**
	 * @param amount the amount to be changed 
	 * @param val the different values arranged in ascending order 
	 * @param n the number of different values 
	 * @return number of ways to change amount with given coin values
	 */
	public static int change( int amount, int[] val, int n ) {
		
		/* When amount < 0 you've changed too much, so it's
		 * not a valid change.
		 * If n <= 0 it means you've tried to change with no
		 * coins to do the change with.. also illegal */
		if( amount < 0 || n <= 0 )
			return 0;
		
		/* amount == 0 you've made a valid change */
		if( amount == 0 )
			return 1;
		
		/* Continue down the rabbit hole of exchanges possibilities,
		 * -> check for more possibilities when removing the largest coin value.
		 * -> check for possibilities when using lesser valued coins  */
		return change( amount - val[n-1], val, n ) + change( amount, val, n-1 );
	}
	
	/**
	 * Print every permutation of given string
	 * @param str String to be permutated
	 */
	public static void permutations( String str ) {
		// Calls recursive helper function
		printPermutations("", str);
	}
	
	/**
	 * Prints out every permutation of given string recursivly
	 * @param pre string that builds up during recursive calls
	 * @param str string to be permutated
	 */
	private static void printPermutations(String pre, String str) {
		
		/* Base case: If str is empty, print out pre */
		if( str.length() <= 0 ) {
			System.out.println(pre);
			return;
		}
		
		/*
		 * Loops over characters in str and make reqursive calls
		 * to get every permutation of string.
		 */
		for( int i = 0; i < str.length(); i++ )
			printPermutations( pre + str.charAt(i), str.substring(0,i) + str.substring(i+1) );

	}
	
	// Main method to test implementation
	public static void main(String[] args) {
		
		int[] sekValues = { 1, 5, 10, 20, 50, 100, 500, 1000 };
		System.out.println(change(12, sekValues, sekValues.length));
		
		permutations("abc");
		
	}
}
