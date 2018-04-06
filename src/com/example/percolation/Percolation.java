package com.example.percolation;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

	private WeightedQuickUnionUF grid;
	private int N;
	private boolean[] states;
	
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int N){

    	// N must be positive
		if(N <= 0) 
			throw new IllegalArgumentException();

        // Init grid and states
        this.N = N;
    	int NN = N * N;
    	grid = new WeightedQuickUnionUF(NN+2);
    	states = new boolean[NN + 2];

		states[0] = states[NN + 1] = true;// top and bottom
    	for (int i = 1; i<=NN; i++)
    		states[i] = false; //all grids
    	
    		
    }
	//
	private boolean InBound(int row, int col){
		if (row < 0 || row >= N || col < 0 || col >= N)
			return false;
		return true;
	}

	private int xy_n(int row, int col){
		return(row * N + col + 1); //0 is bottom, N*N+1 is top
		
	}
    // opens the site (row, col) if it is not open already
    public void open(int row, int col){
    
		// Must be within bound
		if (InBound(row, col) == false)
			throw new IndexOutOfBoundsException("Out of bound");

		//Open cell	
		int ind = xy_n(row, col);
		states[ind] = true;

		//Connect it to neighbors
		int neigh_row, neigh_col, neigh_ind;
		
		for(int m=-1;m<2;m+=2){
			for(int n=-1;n<2;n+=2){
			
				neigh_row = row + m;
				neigh_col = col + n;
				neigh_ind = xy_n(neigh_row, neigh_col);
				
				if(InBound(neigh_row, neigh_col))
				    if(states[neigh_ind])
					    grid.union(ind, neigh_ind);
			}
		}

		//Connect to top/bottom
		if(row == 0) 
			grid.union(0, ind);

		if(row == (N-1))
			grid.union(ind, N*N+1);
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
		int ind = xy_n(row, col);
		return states[ind];
    
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
		int ind = xy_n(row, col);
		return grid.connected(N*N+1,ind);
    
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
		int count = 0;
		for(int i=1;i<=N*N;i++){
			if(states[i] == true)
				count++;
		}
		return count;
    
    }

    // does the system percolate?
    public boolean percolates(){
        return grid.connected(0, N*N+1);
    }

    // unit testing (required)
    public static void main(String[] args){

        Percolation grid = new Percolation(10);
        grid.open(0,0);
        grid.numberOfOpenSites();
        grid.percolates();
        System.out.println("Unit Test to be added");
    }
}
