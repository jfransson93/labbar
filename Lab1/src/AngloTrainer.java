import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

// Author(s): Olle & Patrik
// Email: olle.lindeman@gmail.com, patrik.m.olsson@gmail.com
// Date: 23/3 2014

public class AngloTrainer {

	private static Random randomGenerator = new Random();

	private Set<String> dictionary;
	private int lengthOfLongestWord = 0;
	private String randomLetters;
	
	public AngloTrainer(String dictionaryFile) throws IOException {
	    loadDictionary(dictionaryFile);
	}
	
	public void start() {
	    randomLetters = randomLetters(lengthOfLongestWord);
	    System.out.format("The random letters are: %s\nTry to build as many words from these letters as you can!\n", randomLetters);
	    
	    // Sorts randomLetters for use in includes later
	    randomLetters = sort(randomLetters);
	    
	    // Finds all words with the randomLetters
	    TreeSet<String> correctWords = getAllWords(randomLetters);
	    
	    Scanner sc = new Scanner(System.in);
	    HashSet<String> usedWords = new HashSet<String>();
	    do {
	    	String word;
	    	try {
	    		word = sc.next();
	    	} catch(Exception e) {
	    		// Ends game if any exceptions i thrown (<ctrl-d>)
	    		break;
	    	}
	    	
	    	if(usedWords.contains(word)) {
	    		System.out.println("Already used that word!");
	    	} else {

		    	// Checks that the word only includes the right characters
		    	if(!includes(randomLetters, sort(word))) {
		    		System.out.println("Your suggestion had characters not found in the given random characters.");
		    		break;
		    	}
		    	// Checks if the given word is a correct one
		    	if(!correctWords.contains(word)) {
		    		System.out.println("Your suggestion was not found in the dictionary.");
		    		break;
		    	}
		    	
		    	usedWords.add(word);
		    	System.out.println("ok!");
		    	
	    	}
	    	
	    } while(true);
	    
	    // Display the correct words
	    System.out.println("I found:");
	    for(String word : correctWords) {
	    	System.out.println(word);
	    }
	}

	/**
	 * Finds all possible words in dictionary of given letters
	 * @param letters
	 * @return
	 */
	private TreeSet<String> getAllWords(String letters) {
		TreeSet<String> set = new TreeSet<String>();
		for(String word : dictionary) {
			if( includes(letters, sort(word)))
				set.add(word);
		}
		return set;
	}


	/**
	 * Sorts the characters in a word in alfabetic order
	 * @param word
	 * @return the sorted word
	 */
	private String sort(String word) {
		char[] chars = word.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
	}

	// use this to verify loadDictionary
	private void dumpDict() {
	    // Print out the dictionary at the screen.
        for(String s : dictionary) {
        	System.out.println(s);
        }
	}

	private void loadDictionary( String fileName ) throws IOException {
	    // Read the dictionary into a suitable container.
		this.dictionary = new TreeSet<String>();
	    // The file is a simple text file. One word per line.
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		String line;
		while((line = reader.readLine()) != null) {
			if( line.length() > lengthOfLongestWord)
				lengthOfLongestWord = line.length();
			this.dictionary.add(line);
		}
		reader.close();
	}

	private String randomLetters( int length ) {
	    // this makes vovels a little more likely
	    String letters = "aabcdeefghiijklmnoopqrstuuvwxyyz";  
	    StringBuffer buf = new StringBuffer(length);
	    for ( int i = 0; i < length; i++ ) 
		    buf.append( letters.charAt(randomGenerator.nextInt(letters.length())));
	
	    return buf.toString();
	}
	
	
	/* Def. includes	
	 * Let #(x,s) = the number of occurrences of the charcter x in the string s.
	 * includes(a,b) holds iff for every character x in b, #(x,b) <= #(x,a)
	 * 
	 * A neccessary precondition for includes is that both strings are sorted
	 * in ascending order.
	 */
	private boolean includes( String a, String b ) {
		if ( b == null || b.length() == 0 )
			return true;
		else if ( a == null || a.length() == 0 )
			return false;
		
		//precondition: a.length() > 0 && b.length() > 0
		int i = 0, j = 0;
		while ( j < b.length() ) {
			if (i >= a.length() || b.charAt(j) < a.charAt(i))
				return false;
			else if (b.charAt(j) == a.charAt(i)) {
				i++; j++;
			} else if (b.charAt(j) > a.charAt(i))
				i++;
		}
		//postcondition: j == b.length()
		return true;
	}
	
     // This is just for demonstration purposes.
	private void testIncludes() { 
		//                                            expected value
		System.out.println(includes("abc",""));		//t
		System.out.println(includes("","abc"));		//f
		System.out.println(includes("abc","abc"));	//t
		System.out.println(includes("abc","bcd"));	//f
		System.out.println(includes("abc","a"));	//t
		System.out.println(includes("abc","b"));	//t
		System.out.println(includes("abc","c"));	//t
		System.out.println(includes("abc","ab"));	//t
		System.out.println(includes("abc","bc"));	//t
		System.out.println(includes("abc","ac"));	//t
		System.out.println(includes("abc","abcd"));	//f
		System.out.println(includes("abc","abd"));	//f
		System.out.println(includes("abc","d"));	//f
		System.out.println(includes("",""));		//t
		System.out.println(includes("abc","ca"));	//f
		System.out.println(includes("abc","bac"));	//f
		System.out.println(includes("abc","abbc"));	//f
		System.out.println(includes("abbc","abc"));	//t
		System.out.println(includes(null,null));    //t
		System.out.println(includes(null,""));	    //t
		System.out.println(includes(null,"abc"));	//f
		System.out.println(includes("",null));		//t
		System.out.println(includes("abc",null));   //t
	}

    public static void main(String[] args) {
        try {
			AngloTrainer anglo = new AngloTrainer("dictionary.txt");
			anglo.start();
		} catch (IOException e) {
			System.out.println("Kunde inte šppna filen.." + e);
		}
    }
}












