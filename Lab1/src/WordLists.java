import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

// Author(s): Olle & Patrik
// Email: olle.lindeman@gmail.com, patrik.m.olsson@gmail.com
// Date:  23/3 2014

public class WordLists {
	private Reader in = null;
	private Map<String, Integer>  words;

	public WordLists(String inputFileName) throws IOException {
		in = new BufferedReader(new FileReader(inputFileName));
		words = new TreeMap<String,Integer>();
		String word = null;
		while((word = getWord()) != null) {
			if(words.containsKey(word)) {
				// Adds one to count
				words.put(word, words.get(word) + 1);
			} else {
				words.put(word, new Integer(1));
			}
		}
	}
	
	private boolean isPunctuationChar(char c) {
	    final String punctChars = ",.:;?!";
	    return punctChars.indexOf(c) != -1;
	}
	
	private String getWord() throws IOException {
		int state = 0;
		int i;
		String word = "";
		while ( true ) {
			i = in.read();
			char c = (char)i;
			switch ( state ) {
			case 0:
				if ( i == -1 )
					return null;
				if ( Character.isLetter( c ) ) {
					word += Character.toLowerCase( c );
					state = 1;
				}
				break;
			case 1:
				if ( i == -1 || isPunctuationChar( c ) || Character.isWhitespace( c ) )
					return word;
				else if ( Character.isLetter( c ) ) 
					word += Character.toLowerCase( c );
				else {
					word = "";
					state = 0;
				}
			}
		}
	}
	
	private String reverse(String s) {
		String reverse = "";
		for(int i = 1;i <= s.length();i++)
			reverse += s.charAt(s.length() - i);
		return reverse;
	}
	
	private void computeWordFrequencies() throws IOException {
		// TreeMap for alfabetic order of keys
	    String output = "";
		for (String key : words.keySet()) {
	    	output += String.format("%s\t%d\n", key, words.get(key));
	    }
		writeToFile("alfaSorted.txt", output);
		
	}
	
	private void computeFrequencyMap() throws IOException {
		// Map for saving lists of word, with Integer as index 
		Map<Integer,TreeSet<String>> mp = new TreeMap<Integer,TreeSet<String>>(
			// Comparator for integer in desc order
			new Comparator<Integer>() {
				public int compare(Integer a, Integer b) {
					return -1 * a.compareTo(b);
				}
			});
		
		
		// Loops over all words and adds them to correct treeset
		for(Map.Entry<String,Integer> entry : words.entrySet()) {
			// If key already in use, add word to treeset,
			// else init treeset and put in map.
			if(mp.containsKey(entry.getValue())) {
				mp.get(entry.getValue()).add(entry.getKey());
			} else {
				TreeSet<String> set = new TreeSet<String>();
				set.add(entry.getKey());
				mp.put(entry.getValue(), set);
			}
		}

		// Formats output
		String output = "";
		for(Integer i: mp.keySet()) {
			output += String.format("%d:\n", i);
			for(String word: mp.get(i)) {
				output += String.format("\t%s\n", word);
			}
		}

		// Writes to file ;)
		writeToFile("frequencySorted.txt", output);
		
	}
	

	private void computeBackwardsOrder() throws IOException {
		// Adds all word to TreeSet but in reverse
		TreeSet<String> list = new TreeSet<String>();
		for(String w : words.keySet()) {
			list.add(reverse(w));
		}
		
		// Formats output
		String output = "";
		for(String w : list) {
			output += String.format("%s\n", reverse(w));
		}
		
		// Writes to file
		writeToFile("backwardsSorted.txt", output);
	}
	
	private void writeToFile(String filename, String str) throws IOException {
		Writer writer = new FileWriter(filename);
		writer.write(str);
		writer.close();
	}

	public static void main(String[] args) throws IOException {
		WordLists wl = new WordLists("provtext.txt");  // arg[0] contains the input file name

		wl.computeWordFrequencies();
		wl.computeFrequencyMap();
		wl.computeBackwardsOrder();
		
		System.out.println("Finished!");
	}
}



















