import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;


/******************************************************************************************************
 * @Description - Implementation of generic randomized queue
 * 	Randomized queue. A randomized queue is similar to a stack or queue,
 * 	except that the item removed is chosen uniformly at random among items in the data structure.
 * 
 * @Author - Bello Abdulsamad
 *
 ******************************************************************************************************/


public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] Node;
    private int totalItems = 0;
    private int capacity = 2;

    private class ItemIterator implements Iterator<Item> {
        private int length = totalItems;

        public boolean hasNext() {
            return length > 0;
        }

        public Item next() {
            if (length == 0) {
                throw new NoSuchElementException("end of iteration");
            }
            return Node[StdRandom.uniformInt(length--)];
        }

        public void remove() {
            throw new UnsupportedOperationException("remove method is unsupported");
        }
    }

    // construct an empty randomized queue
    public RandomizedQueue() {
        Item[] arr = (Item[]) new Object[capacity];
	Node = arr;

    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return totalItems == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return totalItems;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("empty argument is unsupported");
        }
        if (totalItems == capacity) {
            capacity *= 2;
            Item[] newNodes = (Item[]) new Object[capacity];
            for (int i = 0; i < totalItems; i++) {
                newNodes[i] = Node[i];
            }
            Node = newNodes;
        }
        Node[totalItems++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (!isEmpty()) {
            int index = StdRandom.uniformInt(totalItems);
            Item val = Node[index];
            for (int i = index + 1; i < totalItems; i++) {
                Node[i - 1] = Node[i];
            }
            totalItems--;
            return val;
        }
        throw new NoSuchElementException("Randomizedqueue is already empty");
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (!isEmpty()) {
            int index = StdRandom.uniformInt(totalItems);
            return Node[index];
        }
        throw new NoSuchElementException("No store items to sample from");
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ItemIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> tester = new RandomizedQueue<>();
        tester.enqueue("Hello");
        tester.enqueue("world");
	tester.enqueue("is ok");
	tester.enqueue("randomized enough");
	tester.enqueue("?");
        System.out.println(tester.sample());
	System.out.println(tester.size());
        System.out.println(tester.dequeue());
	System.out.println(tester.size());
	for (String s: tester) { System.out.println(s); }
    }
}
