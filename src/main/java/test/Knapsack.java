package test;


import instructions.IAbstractInstruction;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * @author Marek Grzes, University of Waterloo, 2011.
 * @version last modified 25/01/2014
 *
 */

/**
 * An example dynamic programming solution to the 0-1 knapsack problem.
 *
 * Note that the fractional knapsack problem can be solved using the greedy algorithm (see Cormen pp. 425).
 *
 */
public class Knapsack {
	public static void main(String[] args) {
		String inputFileName = args[0];
		String outputFileName = args[1];
		
		List<Integer> vi = null;
		List<Integer> wi = null;
		
		PrintStream out = null;
		try {
			out = new PrintStream(new FileOutputStream(outputFileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.setOut(out);
		
		File file = getFileFromResources(inputFileName);
		String line;
		BufferedReader buffReader = null;
		try {
			buffReader = new BufferedReader(new FileReader(file));
			line = buffReader.readLine();
			vi = createArrayFromString(line);
			line = buffReader.readLine();
			wi = createArrayFromString(line);
			buffReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// define items here
		int n = vi.size();
		// set capacity
		int W = 10;
		// V[i, capacity w] will store the maximum combined value of any subset of items {0,1,...,i} of combined size at most w.
		int[][] V = new int[n][W+1];
		// keep[i, capacity w] is true when item i is part of an optimal solution to the sub-problem which has to choose
		// from {0-i} items when capacity is w; that's the reason why it is a dynamic programming algorithm; the solution
		// to the original problem has to be recovered going back from the full problem (i.e., from keep[n-1, W]) to sub-problems;
		boolean[][] keep = new boolean[n][W+1];
		for ( int i = 0; i < n; i++ ) {
			for ( int w=0; w <= W; w++ ) {
				keep[i][w] = false;
			}
		}
		
		// the main algorithm starts here

		// (1) first compute maximum value for all w < W when only first item 0 can be taken, i.e., we consider the
		//     first sub-problem when only the first item can be taken;
		for ( int w = 0; w <= W; w++ ) {
			if ( wi.get(0) <= w) {
				V[0][w] = wi.get(0);
				keep[0][w] = true;	// set true, which means that item 0 can be taken when left capacity is exactly w
			} else {
				V[0][w] = 0;
			}
		}

		// (2) then compute maximum value for all w < W when any subset of items 0-i can be taken
		for ( int i = 1; i < n; i++ ) {
			// for every i, we solve a sub-problem where we choose from {0-i} for every capacity w
			for ( int w = 0; w <= W; w++) {
				if ( wi.get(i) <= w && vi.get(i) + V[i-1][w-wi.get(i)] > V[i-1][w] ) {
					// take item i, at capacity w, because the value of i plus the value of the best previous subset under
					// capacity (w-wi[i]) is better then the value of the previous subset under current w; at this
					// point we know that considering all subsets of {0-i}, i should be taken when w is the capacity.
					// we do not know the subset of {0-(i-1)} but it can be recovered from keep using backward traversal
					// on i because subsets were improved when i was growing;
					V[i][w] = vi.get(i) + V[i-1][w-wi.get(i)];
					// set true which means that item i belongs to the optimal subset of items {0-i} when capacity that is left is exactly w
					keep[i][w] = true;
				} else {
					V[i][w] = V[i-1][w];
				}
			}
		}
		
		// print the result
		System.out.println("Selected items (selected in reverse order):");
		int K = W;
		int wsel = 0;
		int vsel = 0;
		// need to go in the reverse order; what happens here is that given K and i, we have to check if i is part of
		// an optimal subset given capacity K, if yes, we take i, and we continue recovering the optimal subset by
		// reducing K (which means switching to a smaller sub-problem) and finding the next element for reduced capacity
		for ( int i = n - 1 ; i >= 0; i-- ) {
			if ( keep[i][K] == true) {
				System.out.println(i + "\tv=" + vi.get(i) + "\tw=" + wi.get(i));
				wsel += wi.get(i);
				vsel += vi.get(i);
				K = K - wi.get(i);
			}
		}
		
		System.out.println("The overall value of selected items is " + vsel + " and weight " + wsel);
		System.out.println("Maximum weight was " + W);
	}
	
	private static File getFileFromResources(String fileName){
		ClassLoader cLoader = Knapsack.class.getClassLoader();
		File file = null;
		Queue<IAbstractInstruction> instructionsQueue = new PriorityQueue<IAbstractInstruction>();
		try {
			file = new File(new URI(cLoader.getResource(fileName).toString()));
		}  catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return file;
	}
	private static List<Integer> createArrayFromString(String s){
		List<Integer> listoReturn = new ArrayList<Integer>();
		StringTokenizer tokenizer = new StringTokenizer(s);
		while(tokenizer.hasMoreTokens()){
			listoReturn.add(Integer.parseInt(tokenizer.nextToken()));
		}
		return listoReturn;
	}

}