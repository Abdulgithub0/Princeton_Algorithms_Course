import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;

public class RandomWord {
   public static void main(String [] args){
   float i = 1.0f;
   String champion = null;
   String currentWord = null;
   while (!StdIn.isEmpty())
   {
       currentWord = StdIn.readString();
       if (StdRandom.bernoulli(1.0/i))
	   champion = currentWord;
       i++;
  }
  StdOut.println(champion);
  }
}
