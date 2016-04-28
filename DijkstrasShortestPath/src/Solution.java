import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int testCount = in.nextInt();

		Vertex[] vertexes;
		boolean[] visited;
		PriorityQueue<Vertex> pq;

		Comparator<Vertex> cmp = new EdgeComparator();

		// loop through all test cases
		for (int test = 0; test < testCount; test++) {
			int vCount = in.nextInt();
			int eCount = in.nextInt();

			vertexes = new Vertex[vCount];
			visited = new boolean[vCount];
			pq = new PriorityQueue<Vertex>(vCount, cmp);

			// loop through input and add all edges
			for (int e = 0; e < eCount; e++) {
				parseEdge(in, vertexes, pq);
			}

			int source = in.nextInt();



			// loop through until the PQ is empty
			while (!pq.isEmpty()) {
//				Integer[] curr = pq.poll();
//				int node = curr[0];
//
//				int[] adjacents = matrix[node];
//
//				// loop through all neighbors
//				for (int i = 0; i < adjacents.length; i++) {
//					// if there exists an edge from s to i
//					if (adjacents[i] > 0) {
//						if ()
//					}
//				}

			}
		}
	}

	private static void parseEdge(Scanner in, Vertex[] vertexes, PriorityQueue<Vertex> pq) {
		int s = in.nextInt();
		int t = in.nextInt();
		int weight = in.nextInt();

		Vertex node1 = vertexes[s];
		Vertex node2 = vertexes[t];
		
		node1.addNeighbor(s, weight);
		node2.addNeighbor(t, weight);

		pq.add(node1);
		pq.add(node2);
	}
}

class EdgeComparator implements Comparator<Vertex> {
	@Override
	public int compare(Vertex o1, Vertex o2) {
		return (o1.distance - o2.distance);
	}
}

class Vertex {
	public HashMap<Integer, Integer> adjacents;
	public int distance;
	
	public Vertex(){
		this.adjacents = new HashMap<Integer, Integer>();
		this.distance = Integer.MAX_VALUE;
	}
	
	public void addNeighbor(int n, int weight){
		this.adjacents.put(n, weight);
	}
	
	public void setDistance(int n){
		this.distance = n;
	}
}
