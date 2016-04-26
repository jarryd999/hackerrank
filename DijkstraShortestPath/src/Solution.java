import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int testCount = in.nextInt();

		// for each test case:
		for (int i = 0; i < testCount; i++) {
			// grab V & E count
			int vCount = in.nextInt();
			int eCount = in.nextInt();

			boolean[][] matrix = new boolean[vCount][vCount];

			// fill out the adjacency matrix
			for (int j = 1; j < vCount; j++) {
				int s = in.nextInt();
				int t = in.nextInt();

				matrix[s][t] = true;
				matrix[t][s] = true;
			}
			
			int source = in.nextInt();
			
			int[] shortestPaths = new int[vCount - 1];
			for (int j = 0; j < shortestPaths.length; j++){
				shortestPaths[j] = Integer.MAX_VALUE;
			}
			
			HashSet<Integer> visitedNodes = new HashSet<Integer>();
			LinkedList<Integer> queue = new LinkedList<Integer>();
			queue.add(source);
			while (!queue.isEmpty()){
				int curr = queue.poll();
				visitedNodes.add(curr);
				
				for (int j = 0; j < vCount; j++){
					if (matrix[curr][j] && !visitedNodes.contains(j)){
						queue.add(j);
					}
				}
			}
		}

	}

}
