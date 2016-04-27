import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int testCount = Integer.parseInt(in.nextLine());

		// for each test case:
		for (int i = 0; i < testCount; i++) {
			// grab V & E count
			int vCount = in.nextInt();
			int eCount = in.nextInt();

			boolean[][] matrix = new boolean[vCount][vCount];

			// fill out the adjacency matrix
			for (int j = 0; j < eCount; j++) {
				int s = in.nextInt() - 1;
				int t = in.nextInt() - 1;

				matrix[s][t] = true;
				matrix[t][s] = true;
			}

			int source = in.nextInt() - 1;

			int[] shortestPaths = new int[vCount];
			for (int j = 0; j < shortestPaths.length; j++) {
				shortestPaths[j] = Integer.MAX_VALUE;
			}

			HashSet<Integer> visitedNodes = new HashSet<Integer>();
			LinkedList<LinkedList<Integer>> queue = new LinkedList<LinkedList<Integer>>();
			LinkedList<Integer> curr = new LinkedList<Integer>();
			curr.add(source);
			curr.add(0);
			queue.add(curr);
			while (!queue.isEmpty()) {
				curr = queue.poll();

				// extract node # and depth from the queue
				int node = curr.poll();
				int depth = curr.poll();

				// add all adjacents to queue
				for (int j = 0; j < vCount; j++) {
					if (node != j && matrix[node][j] && !visitedNodes.contains(j)) {

						curr.add(j);
						curr.add(depth + 1);
						
						if (shortestPaths[j] > depth + 1){
							shortestPaths[j] = depth + 1;
						}
						queue.add(curr);
					}
				}
				
				// add node to visited nodes set
				visitedNodes.add(node);
			}
			
			StringBuffer out = new StringBuffer();
			for (int j = 0; j < shortestPaths.length; j++){
				if (j == source)
					continue;
				int dist = shortestPaths[j];
				if (dist == Integer.MAX_VALUE){
					out.append(" -1");
				}else{
					dist *= 6;
					out.append(" " + dist);
				}
			}
			
			System.out.println(out.toString().trim());
		}
		in.close();

	}

}
