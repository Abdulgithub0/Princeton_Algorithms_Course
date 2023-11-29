import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	
	private Percolation simulation;
	private double[] fractions;
	private int totalSimulations;
	private int length;
	private final double CONFIDENCE_INTERVAL = 1.96;

	
	//perform independent trials on an n-by-n grid
	
	public PercolationStats(int n, int trials)
    	{
	    if (n <= 0 || trials <= 0) throw new IllegalArgumentException("Error: Both Length and Trials must be greater 0!");
	    fractions = new double[trials];
	    totalSimulations = trials;
	    length = n;
	    for (int i = 0; i < totalSimulations; i++)
	    {
		    simulation = new Percolation(length);
		    while (!simulation.percolates()) simulation.open(StdRandom.uniformInt(n) + 1, StdRandom.uniformInt(n) + 1);
		    fractions[i] = simulation.numberOfOpenSites() * 1.0  / (n * n);
		    simulation = null;
	    }
	} 
    // sample mean of percolation threshold
    public double mean()
    {
	    return StdStats.mean(fractions);
    }

    // sample standard deviation of percolation threshold
    public double stddev()
    {
	    return StdStats.stddev(fractions);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() 
    {
	    return mean() - CONFIDENCE_INTERVAL * stddev() / Math.sqrt(totalSimulations);
    }  


    // high endpoint of 95% confidence interval
    public double confidenceHi()
    {
	    return mean() + CONFIDENCE_INTERVAL * stddev() / Math.sqrt(totalSimulations);
    }

   // test client (see below)
   public static void main(String[] args)
   {
	   if (args.length <= 1)
	   {
		   System.out.println("Usage: java PercolationStats N Trials \n N represents the size of the grid.");
	   	   return;
	   }

	   int N = Integer.parseInt(args[0]), Trials = Integer.parseInt(args[1]);
	   PercolationStats compute = new PercolationStats(N, Trials);
	   System.out.println("mean \t\t\t = " +  compute.mean());
	   System.out.println("stddev \t\t\t = " +  compute.stddev());
	   System.out.println("95% confidence interval  = [" + compute.confidenceLo() + ", " +  compute.confidenceHi() + "]");
   }

}
