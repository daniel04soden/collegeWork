import java.util.LinkedList;

public class GraphAdjList extends AbstractGraph {

    private record Edge(int destination, double weight) {

  }

    private final LinkedList<Edge>[] neighbours;

    public GraphAdjList(int noOfVertices, boolean directed) {
        super(noOfVertices, directed);
    }

    public void addEdge(int source, int destination, double weight) {
        // TODO: Task 2-B
    }

    public void removeEdge(int source, int destination) {
        // TODO: Task 2-C
        throw new RuntimeException("Not yet implemented!");
    }

    public double getWeight(int source, int destination) {
        // TODO: Task 2-D
        throw new RuntimeException("Not yet implemented!");
    }

    public int[] getNeighbours(int vertex) {
        // TODO: Task 2-E
        throw new RuntimeException("Not yet implemented!");
    }

    public int getDegree(int vertex) {
        // TODO: Task 2-F
        throw new RuntimeException("Not yet implemented!");
    }

    public boolean isPath(int[] nodes) {
        // TODO: Task 2-G
        throw new RuntimeException("Not yet implemented!");
    }

    public int getNoOfEdges() {
        // TODO: Task 2-H
        throw new RuntimeException("Not yet implemented!");
    }
}
