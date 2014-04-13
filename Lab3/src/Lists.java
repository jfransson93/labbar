/**
 * A collection of utility functions for C style primitive list handling.
 *
 * @author(s)
 * @version 2013-xx-yy
 */
public class Lists {
   
    // Create an empty list (a null terminated list head).
    public static ListNode mkEmpty() {
        return toList("");
    }
    
    // Returns true if l refers to a null terminated list head, false ow.
    public static boolean isEmpty(ListNode l) {
        if ( l == null )
            throw new ListsException("Lists: null passed to isEmpty");
        return l.next == null;
    }
    
    // Two lists are equal if both are empty, or if they have equal lengths
    // and contain pairwise equal elements at the same positions.
    public static boolean equals(ListNode l1,ListNode l2) {
        if ( isEmpty(l1) && isEmpty(l2) )
            return true;
        else if ( isEmpty(l1) || isEmpty(l2) )
            return false;
        else { // both lists are non-empty
            ListNode p1 = l1.next, p2 = l2.next;
            while ( p1 != null && p2 != null ) {
                char c1 = p1.element, c2 = p2.element;
                if ( p1.element != p2.element )
                    return false;
                p1 = p1.next;
                p2 = p2.next;
            }
            return p1 == null && p2 == null;
        }
    }
    
    // Se f�rel. OH
    public static ListNode toList(String chars) {
        ListNode head, ptr1;     // head pekar alltid p� listans huvud
        head = new ListNode();   // Listans huvud (inneh�ller ej data)
        head.next = null;
        ptr1 = head;             // ptr pekar p� sista noden

        // Bygg en lista av tecken
        for ( int i = 0; i < chars.length(); i++ ) {
            ptr1.next = new ListNode();          // Addera en ny nod sist
            ptr1 = ptr1.next;                    // Flytta fram till den nya noden
            ptr1.element = chars.charAt(i);      // S�tt in tecknet
            ptr1.next = null;                    // Avsluta listan
        } 
        return head;
    }
    
    // Se f�rel. OH
    public static ListNode copy(ListNode l) {
        if ( l == null )
            throw new ListsException("Lists: null passed to copy");
        ListNode head,ptr1,ptr2;
        head = new ListNode();             // Kopian
        head.next = null;
        ptr1 = head;

        ptr2 = l.next;  // f�rsta listelementet i originallistan
        while ( ptr2 != null ) {
            ptr1.next = new ListNode();    // Ny nod i kopian
            ptr1 = ptr1.next;              // Flytta fram
            ptr1.element = ptr2.element;   // Kopiera tecknet
            ptr1.next = null;              // Avsluta
            ptr2 = ptr2.next;              // Flytta fram i originallistan
        }
        return head;
    }
    
    // Se f�rel. OH
    public static ListNode removeAll(ListNode l,char c) {
        if ( l == null )
            throw new ListsException("Lists: null passed to removeAll");
        ListNode p = l;
        while ( p.next != null ) {
            ListNode temp = p.next;      // Handtag p� n�sta nod
            if ( temp.element == c )     // Skall den tas bort?
                p.next = temp.next;      // L�nka f�rbi
            else
                p = p.next;              // Nej, g� vidare *
        }
        // * p f�r ej flyttas om den efterf�ljande noden togs bort!
        return l;
     }
    
    // ---------------- Uppgifter ----------------- 
    
    // Testmetod: JunitListTest.testToString()
    public static String toString(ListNode l) {
    	if ( l == null )
            throw new ListsException("Lists: null passed to toString");
    	String str = "";
        ListNode p = l.next;
        while ( p != null ) {
            str += p.element;
            p = p.next;
        }
        return str;
    }
    
    // Testmetod: JunitListTest.testContains()
    public static boolean contains(ListNode head,char c) {
    	if ( head == null )
            throw new ListsException("Lists: null passed to contains");
    	boolean found = false;
    	ListNode p    = head.next;
    	while(p != null && !found) {
    		if( p.element == c )
    			found = true;
    		p = p.next;
    	}
    	return found;
    }
    
    // Testmetod: JunitListTest.testCopyUpperCase()
    public static ListNode copyUpperCase(ListNode head) {
    	if ( head == null )
            throw new ListsException("Lists: null passed to copyUpperCase");
    	// Skapar ett nytt huvud f�r den nya listan
    	ListNode newHead = new ListNode();
    	// Skapar tv� pekare f�r som ska vandra �ver resp. lista
    	ListNode newPointer = newHead;
    	ListNode oldPointer = head.next;
    	// Loopar �ver den gamla listan
    	while(oldPointer != null) {
    		// Om element i gamla listan �r stor bokstav:
    		if( oldPointer.element >= 'A' && oldPointer.element <= 'Z' ) {
    			// Skapar en ny listNode som f�r v�rdet av gamla element
    			ListNode p = new ListNode();
    			p.element = oldPointer.element;
    			// G�r next f�r nya listans pekare till den nya ListNoden
    			newPointer.next = p;
    			newPointer = newPointer.next;
    		}
    		// G�r vidare i gamla listan
    		oldPointer = oldPointer.next;
    	}
        return newHead;
    }
    
    // Testmetod: JunitListTest.testAddFirst()
    public static ListNode addFirst(ListNode l,char c) {  
    	if ( l == null )
            throw new ListsException("Lists: null passed to addFirst");
    	addNode(l, c);
    	return l;
    }
         
    // This is a private utility method.
    private static ListNode getLastNode(ListNode head) {
    	ListNode p = head;
    	while( p.next != null ) {
    		p = p.next;
    	}
        return p;
    }
    
    private static void addNode(ListNode p, char c) {
    	ListNode newListNode = new ListNode();
    	newListNode.element  = c;
    	newListNode.next     = p.next;
    	p.next = newListNode;
    }
   
    // Testmetod: JunitListTest.testAddLast()
    public static ListNode addLast(ListNode l,char c) {
    	if ( l == null )
            throw new ListsException("Lists: null passed to addLast");
    	addNode(getLastNode(l), c);
        return l;
    }
    
    // Testmetod: JunitListTest.testConcat()
    public static ListNode concat(ListNode l1,ListNode l2) {
    	if ( l1 == null || l2 == null )
            throw new ListsException("Lists: null passed to concat");
    	ListNode p = getLastNode(l1);
    	p.next  = l2.next;
    	l2.next = null;
        return l1;
    }
    
    // Testmetod: JunitListTest.testAddAll()
    public static ListNode addAll(ListNode l1,ListNode l2) {
    	if ( l1 == null || l2 == null )
            throw new ListsException("Lists: null passed to addAll");
    	getLastNode(l1).next = copy(l2).next;
        return l1;
    }
      
    // Testmetod: JunitListTest.testReverse()
    public static ListNode reverse(ListNode head) {
    	if ( head == null )
            throw new ListsException("Lists: null passed to reverse");
    	ListNode prev = null;
    	ListNode p = head.next;
    	while( p != null ) {
    		ListNode l = new ListNode();
    		l.element = p.element;
    		l.next = prev;
    		prev   = l;
    		p = p.next;
    	}
    	ListNode newHead = new ListNode();
    	newHead.next = prev;
        return newHead;
    }
}
