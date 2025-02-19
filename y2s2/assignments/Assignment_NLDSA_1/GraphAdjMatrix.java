import java.util.LinkedList;

public class GraphAdjMatrix extends AbstractGraph {

    private final double[][] adjMatrix;

    public GraphAdjMatrix(int noOfVertices, boolean directed) {
        super(noOfVertices, directed);
        adjMatrix = new double[noOfVertices - 1][noOfVertices - 1];
    }

    public void addEdge(int source, int destination, double weight) {
        this.adjMatrix[source][destination] = weight;

        if (!this.directed) { // Undirected
            this.adjMatrix[destination][source] = weight;
        }
    }

    public void removeEdge(int source, int destination) {
        this.adjMatrix[source][destination] = 0; // 0 or null??? isn't it nan if its connected
        // Remember: counter not needed - archaic way of doing number of edges
        if (!this.directed) {
            this.adjMatrix[destination][source] = 0;
        }
    }

    public double getWeight(int source, int destination) {
        return adjMatrix[source][destination];
    }

    public int[] getNeighbours(int vertex) {}

    public int getDegree(int vertex) {
        throw new RuntimeException("Not yet implemented!");
    }

    public boolean isPath(int[] nodes) {
        int n = nodes.length;
        int pathCount = 1;
        for (int i = 0; i < n - 1; i++) {
            if (adjMatrix[i][i + 1] == nodes[i + 1]) {
                pathCount++;
            } else {
                break;
            }
        }
        return pathCount == n;
    }

    public int getNoOfEdges() {
        int countNoOfEdges = 0;
        for (int i = 0; i < getNoOfVertices(); i++) {
            for (int j = 0; j < getNoOfVertices(); j++) {
                if (adjMatrix[i][j] > 0) {
                    countNoOfEdges++;
                } else {
                    continue;
                }
            }
        }
        if (!directed) {
            countNoOfEdges = countNoOfEdges / 2;
        }
        return countNoOfEdges;
    }
}
