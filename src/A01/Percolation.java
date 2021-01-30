package a01;
import edu.princeton.cs.algs4.*;

import java.util.Arrays;

public class Percolation {
	private final int size;
	private final boolean grid [][];
	private int N;
	private WeightedQuickUnionUF wqu;
	private int open;
	
	public Percolation(int N) {
		if(N <= 0) throw new IndexOutOfBoundsException("N must be greater than 0");
		
		this.N = N;
		size = N * N;
		grid = new boolean[N][N];
		wqu = new WeightedQuickUnionUF(size + 2);
		open = 0;
		
	}
	
	public static void main(String[] args) {
		Percolation perc1 = new Percolation(3);
		
		//perc1.open(1, 2);
		System.out.println(Arrays.deepToString(perc1.grid));
		
		System.out.println();
		
		System.out.println(perc1.getIndex(0,0));
	}
	
	public void open(int i, int j) {
		//System.out.println(i + " " + j);
		inbounds(i, j);
		if(!grid[i][j]) {
			grid[i][j] = true;
			open++;
			System.out.println(open);
		}
		
		if(i == 0) wqu.union(getIndex(i,j), 0);
		//if(i == N - 1) wqu.union(getIndex(i,j), size + 1);
		if(i == N - 1 && wqu.connected(0, getIndex(i, j))) wqu.union(getIndex(i,j), size + 1);
		
		if(i > 0 && grid[i-1][j]) {
			wqu.union(getIndex(i,j), getIndex(i-1, j));
			if((i == N-2) && isOpen(i+1,j)) wqu.union(getIndex(i+1, j), size+1);
		}
		if(j > 0 && grid[i][j-1]) wqu.union(getIndex(i,j), getIndex(i, j-1));
		if(i < N-1 && grid[i+1][j]) {
			wqu.union(getIndex(i,j), getIndex(i+1, j));
			if(i == N-1) wqu.union(getIndex(i+1, j), size+1);
		}
		if(j < N-1 && grid[i][j+1]) wqu.union(getIndex(i,j), getIndex(i, j+1));
		
		
		//if(i == N - 1 && wqu.connected(0, getIndex(i, j))) wqu.union(getIndex(i,j), size + 1);
		//if(wqu.connected(getIndex(i,j),))
		
	}
	
	public boolean isOpen(int i, int j) {
		//System.out.println(i + " " + j);
		inbounds(i,j);
		return grid[i][j];
		
	}
	
	public boolean isFull(int i, int j) {
		inbounds(i,j);
		System.out.println(i + " " + j);
		return wqu.connected(0, getIndex(i,j));
		
	}
	
	public int numberOfOpenSites() {
		return open;
		
	}
	
	public boolean percolates() {
		return wqu.connected(0, size + 1);
		
	}
	
	private int getIndex(int i, int j) {
		inbounds(i,j);
		return N * i + j;
	}
	
	private void inbounds(int i, int j) {
		
		if(i < 0 || i >= N) {
			throw new IndexOutOfBoundsException("row index " + i + 
					" must be between 0 and " + (N - 1));
		}
		if(j < 0 || j >= N) {
			throw new IndexOutOfBoundsException("column index " + j +
					" must be between 0 and " + (N - 1));
		}
	}
}
