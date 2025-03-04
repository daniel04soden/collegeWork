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
    int size = 0; // Initialise the future neighbours array allocation
    double[] edges = new double[noOfVertices]; // Making the array for all potential edges on vertices
    for (int vertexNo = 0; vertexNo<noOfVertices; vertexNo++) { // Looping over said array
      if (!Double.isNaN(getWeight(vertex, vertexNo))) { // If its not a NaN increase our neighbour list size
        edges[vertexNo] = vertexNo;
        size++;
      }else{ // Otherwise add to edges but don't increase size, won't be in future list
        edges[vertexNo] = Double.NaN; 
      }
    }
    int[] neighbours = new int[size]; // Making list of neighbours here after determining size
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
    int[] myNeighbours = getNeighbours(vertex);
    int degree = myNeighbours.length;
    if (!directed) {
      for (int i = 0; i < noOfVertices; i++) {
        if (!(Double.isNaN(getWeight(i, vertex)))) {
          degree++;
        }else{
          continue;
        }
      }
    }
    return degree;
  }

  public boolean isPath(int[] nodes) {
    int n = nodes.length;
    for (int i = 0; i < n-1; i++) {
      int currentSource = nodes[i];
      int nextDest = nodes[i+1];
      if (Double.isNaN(getWeight(currentSource, nextDest))) {
        return false; 
      }
    }
    return true;
  }

  public int getNoOfEdges() {
    int countNoOfEdges = 0;
    for (int i = 0; i < noOfVertices; i++) {
      for (Edge e : neighbours[i]) { // Loop over the neighbours at the index of the source vertex
        if (!(Double.isNaN(e.weight))) {
         countNoOfEdges++; 
        }else{
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
