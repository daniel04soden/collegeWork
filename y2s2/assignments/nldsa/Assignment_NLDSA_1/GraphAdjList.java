import java.util.LinkedList;

public class GraphAdjList extends AbstractGraph {

  private record Edge(int destination, double weight) {
  }

  private final LinkedList<Edge>[] neighbours;

  public GraphAdjList(int noOfVertices, boolean directed) {
    super(noOfVertices, directed);
    this.neighbours = new LinkedList[noOfVertices];
    for (int i = 0; i < noOfVertices; i++) {
      neighbours[i] = new LinkedList<Edge>(); // Filling up the array with linkedlists of edges
    }
  }

  public void addEdge(int source, int destination, double weight) {
    if (!directed) { // If undirected
      if (!(source >= destination)) {                 
        neighbours[destination].add(new Edge(source, weight)); // At the index of the destination add an edge towards the source
      } else {
        return; // If the source is greater than or equal to the destination in a non-directed graph, break
      }
    }
    neighbours[source].add(new Edge(destination, weight)); // Regardless - add an edge to the destination from the source
  }

  public void removeEdge(int source, int destination) {
    for (Edge e : neighbours[source]) { // Loop over the neighbours at the index of the source vertex
      if (e.destination == destination) { // If destination found
        if (!directed) { // Check if we're working with an undirected graph
          neighbours[destination].remove(source); // If so, remove the edge from destinatoin to source
        }
        neighbours[source].remove(destination); // Regardless, remove the edge from source TO destination
      }
    }
  }

  public double getWeight(int source, int destination) {
    for (Edge e : neighbours[source]) { // Loop over the neighbours at the index of the source vertex
      if (e.destination == destination) { // If the destination is found
        return e.weight; // Return its weight
      } else {
        continue; // Otherwise keep going
      }
    }
    return Double.NaN; // If nothing is found return NaN
  }

  public int[] getNeighbours(int vertex) {
    int[] testing = { 1, 2, 3 };
    return testing;
  }

  public int getDegree(int vertex) {
    return 2;
    // // throw new RuntimeException("Not yet implemented!");
  }

  public boolean isPath(int[] nodes) {
    return true;
    // throw new RuntimeException("Not yet implemented!");
  }

  public int getNoOfEdges() {
    return 2;
    // throw new RuntimeException("Not yet implemented!");
  }
}
