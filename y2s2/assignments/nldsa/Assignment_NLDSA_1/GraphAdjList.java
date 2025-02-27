import java.util.LinkedList;

public class GraphAdjList extends AbstractGraph {

  private record Edge(int destination, double weight) {

  }

  private final LinkedList<Edge>[] neighbours;

  public GraphAdjList(int noOfVertices, boolean directed) {
    super(noOfVertices, directed);
    this.neighbours = new LinkedList[noOfVertices];
    for (int i = 0; i < noOfVertices; i++) {
      neighbours[i] = new LinkedList<Edge>();
    }
  }

  public void addEdge(int source, int destination, double weight) {
    if (!directed) {
     if (!(source >=destination)) { 
      neighbours[destination].add(new Edge(source, weight));
     }else{
        return;
      } 
    }
    neighbours[destination].add(new Edge(source, weight));
  }

  public void removeEdge(int source, int destination) {
    for (Edge e:neighbours[source]) {
     if (e.destination == destination) {
        if (!directed) {
        neighbours[destination].remove(source);
        }
        neighbours[source].remove(destination);
     } 
    }
  }

  public double getWeight(int source, int destination) {
    for (Edge e: neighbours[source]) {
      if (e.destination == destination) {
        return e.weight;
      }else{
        continue;
      }
    }
    return Double.NaN;
  }

  public int[] getNeighbours(int vertex) {
    int size = 0; // Initialise the future neighbours array allocation
    double[] edges = new double[noOfVertices]; // Making the array for all potential edges on vertices
    for(Edge e:neighbours[vertex]){

      if (!Double.isNaN(adjMatrix[vertex][vertexNo])) { // If its not a NaN increase our neighbour list size
        edges[vertexNo] = vertexNo;
        size++;
      }else{ // Otherwise add to edges but don't increase size, won't be in future list
        edges[vertexNo] = Double.NaN; 
      }
    }

    int[] realNeighbours = new int[size]; // Making list of neighbours here after determining size
    int j = 0;
    for (int i = 0; i < noOfVertices; i++) { // Looping over original edge list
      if (!Double.isNaN(edges[i])) { // If its not a NaN add to neighbours
        neighbours[j] = i; // Index mathcing the vertexNo
        j++; // Increasing given the noOfVertices is actually different compared to size of neighbours
      } else {
        continue; // Otherwise ignore
      } 
    }

    return neighbours;
  }

  public int getDegree(int vertex) {
    // TODO: Task 2-F
    throw new RuntimeException("Not yet implemented!");
  }

  public boolean isPath(int[] nodes) {
    throw new RuntimeException("Silly");
  }

  public int getNoOfEdges() {
    throw new RuntimeException("Silly");
  }
}
