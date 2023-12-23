*Implementation of a generic data type for a deque and a randomized queue.
The goal of this assignment is to implement elementary data structures using arrays and linked lists, and to introduce you to generics and iterators.

```
Supported Api(s) for Deque Model

   public Deque()                           - construct an empty deque
   public boolean isEmpty()                 - is the deque empty?
   public int size()                        - return the number of items on the deque
   public void addFirst(Item item)          - add the item to the front
   public void addLast(Item item)           - add the item to the end
   public Item removeFirst()                - remove and return the item from the front
   public Item removeLast()                 - remove and return the item from the end
   public Iterator<Item> iterator()         - return an iterator over items in order from front to end
   public static void main(String[] args)   - unit testing

```

```
Supported Api(s) for RandomizedQueue Model

   public RandomizedQueue()                 - construct an empty randomized queue
   public boolean isEmpty()                 - is the queue empty?
   public int size()                        - return the number of items on the queue
   public void enqueue(Item item)           - add the item
   public Item dequeue()                    - remove and return a random item
   public Item sample()                     - return (but do not remove) a random item
   public Iterator<Item> iterator()         - return an independent iterator over items in random order
   public static void main(String[] args)   - unit testing

```
