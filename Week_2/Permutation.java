import edu.princeton.cs.algs4.StdIn;

/*************************************************************************************************
 * @description - A client program for Randomizedqueue data structure
 * 	 Permutation: a client program that takes an integer k [0, n] as a command-line argument;
 * 	 reads a sequence of strings from standard input using StdIn.readString();
 * 	 and prints exactly k of them, uniformly at random.
 * 	 Print each item from the sequence at most once.
 *
 *
 * @author: Bello Abdulsamad
 ****************************************************************************************************/

public class Permutation {
   public static void main(String[] args)
   {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> randomItems = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            randomItems.enqueue(StdIn.readString());
        }
        for (int i = 0; i < k; i++) {
            System.out.println(randomItems.dequeue());
        }
   }
}
