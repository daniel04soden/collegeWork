package assignment;

import java.util.LinkedList;

public class GraphAdjMatrix extends AbstractGraph {
	private final double[][] adjMatrix;
	int n = len(adjMatrix); // Should this be the length??

	public GraphAdjMatrix(int noOfVertices, boolean directed) {
		super(noOfVertices, directed);
		if (!directed){
			this.noOfVertices = noOfVertices*2;
		}

		for (int i = 0; i < n; ++i) { // what is n...
			for (int j = 0; j < n; ++j) { // n...
				adjMatrix[i][j] = 0;

				if (!directed){
					adjMatrix[j][i] = 0;
				}

			}
		}
	}

	public void addEdge(int source, int destination, double weight) {
		this.adjMatrix[source][destination] = 1;
		// Add edge counter
		if (!this.directed){
		this.adjMatrix[destination][source] = 1;
		// Add edge counter - Would Edges  actually be divided by two ?
		}
	}

	public void removeEdge(int source, int destination) {
		this.adjMatrix[source][destination] = 0; // 0 or null???
		// remove edge counter
		if (!this.directed){
		this.adjMatrix[destination][source] = 0;
		// remove edge counter
		}
	}

	public double getWeight(int source, int destination) {
		// TODO: Task 1-D
		throw new RuntimeException("Not yet implemented!");
	}

	public int[] getNeighbours(int vertex) {
		// TODO: Task 1-E
		throw new RuntimeException("Not yet implemented!");
	}

	public int getDegree(int vertex) {
		// TODO: Task 1-F
		throw new RuntimeException("Not yet implemented!");
	}

	public boolean isPath(int[] nodes) {
		// TODO: Task 1-G
		throw new RuntimeException("Not yet implemented!");
	}

	public int getNoOfEdges() {
		if (!this.directed){

		}
	}
}
