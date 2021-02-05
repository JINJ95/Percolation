package a01;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	private int N;
	private int T;
	private double[] thresholds;
	
	public PercolationStats(int N, int T) {
		if(N < 1 || T < 1) throw new IllegalArgumentException();
		this.N = N;
		this.T = T;
		thresholds = new double[T];
		
		for(int i = 0; i < T; i++) {
			
			thresholds[i] = findThresholds();
		}
		
	}
	
	public double mean() {
		return StdStats.mean(thresholds);
	}
	
	public double stddev() {
		return StdStats.stddev(thresholds);
	}
	
	public double confidenceLow() {
		return mean() - (1.96 * stddev())/Math.sqrt(T);
	}
	
	public double confidenceHigh() {
		return mean() + (1.96 * stddev())/Math.sqrt(T);
	}
	
	public static void main(String[] args) {
		//System.out.println(StdRandom.uniform(5));
		
		System.out.println((double)5/4);
		
		PercolationStats percStats = new PercolationStats(200,100);
		
		System.out.println((double)5/4);
		
		System.out.println(Arrays.toString(percStats.thresholds));
		System.out.println(percStats.confidenceLow());

	}
	
	private double findThresholds() {
		Percolation percolation = new Percolation(N);
		int counter = 0;
		
		while(!percolation.percolates()) {
			int i = StdRandom.uniform(N);
			int j = StdRandom.uniform(N);
			
			if(!percolation.isOpen(i, j)) {
				percolation.open(i, j);
				counter++;
			}
		}
		//System.out.println(counter);
		//System.out.println(N);
		//System.out.println(counter/(N*N));
		return (double)counter/(N*N);
	}
}
