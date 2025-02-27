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
      
    }
  }

  public double getWeight(int source, int destination) {
    for (Edge e: neighbours[source]) {
       
    }
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
    int n = nodes.length;
    for (int i = 0; i < n-1; i++) {
      int currentSource = nodes[i];
      int nextDest = nodes[i+1];
      if (neighbours.get) {
        return false; 
      }
    }
    return true;
  }

  public int getNoOfEdges() {
    int countNoOfEdges = 0;
    for (int i = 0; i < noOfVertices; i++) {
      for (int j = 0; j < noOfVertices; j++) {
        if (!(Double.isNaN(adjMatrix[i][j]))) {
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
