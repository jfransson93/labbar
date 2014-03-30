import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {    
         ArrayList<String> names = new ArrayList<String>();

         // Test print for an empty list
         CollectionOps.print(names); System.out.println();

         // Test print for a list containing one element
         names.add("a");
         CollectionOps.print(names); System.out.println();

         // Test print for a list containing more than one element
         names.add("b");
         names.add("c");
         CollectionOps.print(names); System.out.println();
         
         // Test the return value from reverse
         CollectionOps.print(CollectionOps.reverse(names));
         System.out.println();
         // Test that reverse mutates its argument
         CollectionOps.print(names);
         System.out.println();

         // Write code to test less here
         Comparator<Integer> intcomp = new Comparator<Integer>() {
			@Override
			public int compare(Integer a, Integer b) {
				return a.compareTo(b);
			}	 
         };
         
         
         List<Integer> li1 = new ArrayList<Integer>();
         li1.addAll(Arrays.asList(1,2,3,4,10));
         List<Integer> li2 = new ArrayList<Integer>();
         li2.addAll(Arrays.asList(11,99,23,41,42));
         
         System.out.format("li1 < li2: %b\n", CollectionOps.less(li1, li2, intcomp));
         System.out.format("li1 > li2: %b\n", CollectionOps.less(li2, li1, intcomp));

         // Write code to test map here
             
         // Write code to test filter here
         CollectionOps.print(li1);
         CollectionOps.print(CollectionOps.filter(new IsEvenFunctor(), li1));
         CollectionOps.print(li1);System.out.println();
         CollectionOps.print(li2);
         CollectionOps.print(CollectionOps.filter(new IsEvenFunctor(), li2));
         CollectionOps.print(li2);
    }
}














