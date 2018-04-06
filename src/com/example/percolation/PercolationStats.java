package com.example.percolation;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private int N;
    private int T;
    private double[] p;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int N, int T){
        if(N <= 0 || T <= 0)
            throw new IllegalArgumentException();

        this.N = N;
        this.T = T;
        p = new double[T];

        for(int run=0; run<T; run++){
            Percolation grid = new Percolation(N);
            while(!grid.percolates()){
                int row = StdRandom.uniform(0, N);
                int col = StdRandom.uniform(0, N);

                //if already open, skip and continue
                if(!grid.isOpen(row, col))
                    grid.open(row, col);
                else
                    continue;
            }
            p[run] = (double)grid.numberOfOpenSites() /
                     (double)(N*N);
        }
    }

    // sample mean of percolation threshold
    public double mean(){
	    return StdStats.mean(this.p);
	}

    // sample standard deviation of percolation threshold
    public double stddev(){
        return StdStats.stddev(this.p);
	}

    // low endpoint of 95% confidence interval
    public double confidenceLow(){
        double confidenceLow;
        confidenceLow = mean() - 1.96 * stddev() /
                Math.sqrt((double) T) ;
        return confidenceLow;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh(){
        double confidenceHigh;
        confidenceHigh = mean() + 1.96 * stddev() /
                Math.sqrt((double) T);
        return confidenceHigh;
    }

   // test client (see below)
   public static void main(String[] args){
        int N =  Integer.parseInt(args[0]);
        int T =  Integer.parseInt(args[1]);
        PercolationStats test = new PercolationStats(N,T);

        System.out.println("mean =" + test.mean());
        System.out.println("stddev =" + test.stddev());
        System.out.println("95% confidence interval = " +
                test.confidenceLow() + "," + test.confidenceHigh());

   }
}
