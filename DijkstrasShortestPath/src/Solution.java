import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int testCount = in.nextInt();

		Vertex[] vertexes;
		PriorityQueue<Vertex> pq;

		// Comparator<Vertex> cmp = new EdgeComparator();

		// loop through all test cases
		for (int test = 0; test < testCount; test++) {
			int vCount = in.nextInt();
			int eCount = in.nextInt();

			vertexes = new Vertex[vCount];

			for (int i = 0; i < vCount; i++) {
				vertexes[i] = new Vertex();
			}
			pq = new PriorityQueue<Vertex>(vCount);

			// loop through input and add all edges
			for (int e = 0; e < eCount; e++) {
				parseEdge(in, vertexes);
			}

			// grab the source vertex
			int source = in.nextInt() - 1;
			vertexes[source].distance = 0;

			// add vertexes to PriorityQueue
			fillPriorityQueue(pq, vertexes);

			// loop through until the PQ is empty
			while (!pq.isEmpty()) {

				// extract the min
				Vertex curr = pq.poll();
				int currDist = curr.distance;
				HashMap<Integer, Integer> adjacents = curr.adjacents;

				// loop through all neighbors
				Iterator<?> iterator = adjacents.entrySet().iterator();
				while (iterator.hasNext()) {
					Map.Entry pair = (Map.Entry) iterator.next();
					int vId = (int) pair.getKey();
					Vertex neighbor = vertexes[vId];
					int neighborDist = neighbor.distance;
					int edgeDist = (int) pair.getValue();

					// if distance b/w curr node & neighbor is less than
					// neighbor's
					// distance, update
					if (currDist != Integer.MAX_VALUE && neighborDist > currDist + edgeDist) {
						vertexes[vId].distance = currDist + edgeDist;
						pq.remove(vertexes[vId]);
						pq.add(vertexes[vId]);
					}
					iterator.remove();
				}
			}

			printResults(vertexes, source);

		}
	}

	/**
	 * Function:		fillPriorityQueue(pq, vertexes)
	 * Description:		Loops through the priority queue and fills it with Vertex objects
	 * @param pq		The PriorityQueue to fill
	 * @param vertexes	The array of Vertex objects to fill add to the PriorityQueue
	 */
	private static void fillPriorityQueue(PriorityQueue<Vertex> pq, Vertex[] vertexes) {
		for (int i = 0; i < vertexes.length; i++) {
			pq.add(vertexes[i]);
		}

	}

	/**
	 * Function:		printResults(vertexes, source)
	 * Description:		Loops through and prints the distance from the source vertex
	 * @param vertexes	The array of Vertex objects to loop through and print distances for
	 * @param source	The key of the Vertex object from which all distances are measured
	 */
	private static void printResults(Vertex[] vertexes, int source) {
		StringBuffer buff = new StringBuffer();
		// loop through and print all the distances
		for (int i = 0; i < vertexes.length; i++) {
			if (i == source)
				continue;
			Vertex v = vertexes[i];
			if (v.distance == Integer.MAX_VALUE)
				buff.append("-1 ");
			else
				buff.append(v.distance + " ");
		}

		System.out.println(buff.toString().trim());
	}

	/**
	 * Function:		parseEdge(in, vertexes, pq)
	 * Description:		Reads a line of input and adds to Vertex adjacency lists if the weight
	 * 					of the edge is less than the existing edge, or if no edge already exists
	 * 					between the two Vertex objects
	 * 
	 * @param in		The Scanner object to read input from		
	 * @param vertexes	The array of Vertex objects to modify
	 */
	private static void parseEdge(Scanner in, Vertex[] vertexes) {
		int s = in.nextInt() - 1;
		int t = in.nextInt() - 1;
		int weight = in.nextInt();
		int oldVal;
		Vertex node1 = vertexes[s];
		Vertex node2 = vertexes[t];

		if (node1.adjacents.containsKey(t)){
			oldVal = node1.adjacents.get(t);
		}else
			oldVal = Integer.MAX_VALUE;
		
		if (weight < oldVal) {
			node1.addNeighbor(t, weight);
			node2.addNeighbor(s, weight);
		}
	}
}

/**
 * Class:			Vertex
 * Description:		A representation of a graph Vertex which stores an adjacency list and it's
 * 					distance from a given source Vertex
 * @author jarryd
 *
 */
class Vertex implements Comparable<Vertex> {
	public HashMap<Integer, Integer> adjacents;
	public int distance;

	public Vertex() {
		this.adjacents = new HashMap<Integer, Integer>();
		this.distance = Integer.MAX_VALUE;
	}

	public void addNeighbor(int n, int weight) {
		this.adjacents.put(n, weight);
	}

	public void setDistance(int n) {
		this.distance = n;
	}

	@Override
	public int compareTo(Vertex o) {
		return (this.distance - o.distance);
	}
}
