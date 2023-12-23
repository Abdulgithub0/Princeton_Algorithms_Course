import java.util.Iterator;

/**********************************************************************************
 * @Description - implementation of a generic Deque.
 * 	Dequeue: A double-ended queue or deque (pronounced “deck”)
 * 	is a generalization of a stack and a queue that supports adding
 * 	and removing items from either the front or the back of the data structure.
 * 
 * @Author - Bello Abdulsamad
 ***********************************************************************************/


public class Deque<Item> implements Iterable<Item> {

    private Node first, last;
    private int totalItems = 0;

    private class Node {
        Item value;
        Node next;
    }

    private class ItemIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
	    if (!hasNext()) { throw new java.util.NoSuchElementException("all items has been iterated"); }
            Item item = current.value;
            current = current.next;
            return item;
        }
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return totalItems;
    }

    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("empty argument is unsupported");
        totalItems++;
        if (totalItems == 1) {
            first = new Node();
            first.value = item;
            last = first;
        } else {
            Node oldFirst = first;
            first = new Node();
            first.value = item;
            first.next = oldFirst;
        }
    }

    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("empty argument is unsupported");
        totalItems++;
        if (totalItems == 1) {
            last = new Node();
            last.value = item;
            first = last;
        } else {
            Node oldLast = last;
            last = new Node();
            last.value = item;
            oldLast.next = last;
        }
    }

    public Item removeFirst() {
        if (totalItems > 0) {
            Item value = first.value;
            first = first.next;
            totalItems--;
            return value;
        }
        throw new java.util.NoSuchElementException("Cannot remove from an empty Deque");
    }

    public Item removeLast() {
        if (totalItems > 0) {
            if (totalItems == 1) {
                Item val = first.value;
                first = null;
                last = null;
                totalItems--;
                return val;
            } else {
                Node slast = first;
                while (slast.next.next != null) {
                    slast = slast.next;
                }
                Item val = slast.next.value;
                slast.next = null;
                last = slast;
                totalItems--;
                return val;
            }
        }
        throw new java.util.NoSuchElementException("Cannot remove from an empty Deque");
    }

    public Iterator<Item> iterator() {
        return new ItemIterator();
    }

    public static void main(String[] args) {
        Deque<String> tester = new Deque<>();
        tester.addFirst("Hello");
        tester.addLast("World");
	System.out.println(tester.size());
        System.out.println(tester.removeFirst());
        System.out.println(tester.removeLast());
	System.out.println(tester.size());
	System.out.println(tester.isEmpty());
	tester.addLast("are you");
	tester.addFirst("How");
	tester.addLast("?");
	for (String s: tester) {
		System.out.println(s);
	}
	System.out.println(tester.size());
	System.out.println(tester.isEmpty());
    }
}
