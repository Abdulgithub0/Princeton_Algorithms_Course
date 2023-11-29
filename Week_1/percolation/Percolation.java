/*********************************************************************************************************************************
 * Percolation -  Given a composite systems comprised of randomly distributed insulating and metallic materials: 
 * 		what fraction of the materials need to be metallic so that the composite system is an electrical conductor? 
 *
 * 		Given a porous landscape with water on the surface (or oil below), under what conditions will the water be able 
 * 		to drain through to the bottom (or the oil to gush through to the surface)? 
 * 		Scientists have defined an abstract process known as percolation to model such situations.

   The model -  We model a percolation system using an n-by-n grid of sites. Each site is either open or blocked.
   		A full site is an open site that can be connected to an open site in the top row via a chain of neighboring
		(left, right, up, down) open sites. We say the system percolates if there is a full site in the bottom row.
		
  		For the insulating/metallic materials example:
		The open sites correspond to metallic materials, so that a system that percolates has a metallic path from
		top to bottom, with full sites conducting.
   		
		For the porous substance example, the open sites correspond to empty space through which water might flow,
		so that a system that percolates lets water fill open sites, flowing from top to bottom.)			 *
 * 										
 * @author  Bello Abdulsamad													 *
 *																 *
 *																 *
 *																 *
 *																 *
 *																 *
 *																 *
 *********************************************************************************************************************************/

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    
    private WeightedQuickUnionUF grid;
    private int width, height;
    private boolean[] isOpenOrClose;
    private int totalOpenSite = 0;

    // creates n-by-n grid, with all sites initially blocked (Not open) except for the virtual root top and bottom
    public Percolation(int N)
    {
	    if (N <= 0) throw new IllegalArgumentException("Total number of site should be geater 0");
	    grid = new WeightedQuickUnionUF(N * N + 2);
	    width = height = N;
	    isOpenOrClose = new boolean[N * N + 2];
	    isOpenOrClose[0] = true;
	    isOpenOrClose[width * height + 1] = true;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col)
    {
	    validate(row, col);

	    int newOpenSite = getIndex(row, col);
	    if (!isOpen(row, col))
	    {
		    totalOpenSite++;
		    isOpenOrClose[newOpenSite] = true;
	    }

	    // Does newOpenSite has "X" adjacent site?  If yes, is it open? 
	    // Case: X = Bottom
	    if (row < height && isOpen(row + 1, col)) grid.union(newOpenSite, getIndex(row + 1, col));
	    
	    // Case: X = Top
	    if (row > 1 && isOpen(row - 1, col)) grid.union(newOpenSite, getIndex(row - 1, col));
	    
	    // Case: X = left
	    if (col < width && isOpen(row, col + 1)) grid.union(newOpenSite, getIndex(row, col + 1));
	    
	    // Case: X = right
	    if (col > 1 && isOpen(row, col - 1)) grid.union(newOpenSite, getIndex(row, col - 1));

	    // create virtual top for all topmost row sites --> The reserve site at 0 index will be used as their root
	    if (row == 1) grid.union(newOpenSite, 0);

	    // Virtual bottom for all bottom-most row sites --> using site found at index N * N + 1 as root
	    if (row == height) grid.union(newOpenSite, height * height + 1);
    }

    // check if a given site is open?
    public boolean isOpen(int row, int col)
    {
	    validate(row, col);
	    return isOpenOrClose[getIndex(row, col)];
    }

    // is a given site full ?  --> i.e connected to the virtual top
    public boolean isFull(int row, int col)
    {
	    validate(row, col);
	    return grid.find(getIndex(row, col)) == grid.find(0);
    }

    // returns the total number of open sites so far.
    public int numberOfOpenSites()
    {
	    return totalOpenSite;
    }

    // does the system percolate?
    public boolean percolates()
    {
	    return grid.find((width * height + 1)) == grid.find(0);
    }

    // validate row and column parameters
    private void validate(int row, int col)
    {
	    if (row <= 0 || row > height)
	    {
	    	throw new IllegalArgumentException("Row value is out of range");
	    }

	    if (col <= 0 || col > width)
	    {
	    	throw new IllegalArgumentException("Column value is out of range");
	    }
    }

    // decompose 2D/grid into 1D for easy searching
    private int getIndex(int row, int col)
    {
	    return ((row - 1) * width + col);
    }

    // test client
    public static void main(String[] args)
    {
	    
	    // test 20 by 20 grid
	    Percolation percolate = new Percolation(20);
	    while (!percolate.percolates())
	    {
		    int row = StdRandom.uniformInt(20) + 1;
		    int col = StdRandom.uniformInt(20) + 1;
	
		    percolate.open(row, col);
		    //System.out.printf("row: %d and col: %d\n", row, col);
		    //System.out.println("is fun ? : " + percolate.isFull(row, col));
	    }
	    System.out.println("Total open sites:  " + percolate.numberOfOpenSites() + "\nPercolation threshold:  " +  percolate.numberOfOpenSites() / (20.0 * 20.0));
   	    
    }
}
