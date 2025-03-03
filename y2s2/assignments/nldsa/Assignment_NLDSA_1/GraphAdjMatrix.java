public class GraphAdjMatrix extends AbstractGraph {
  private final double[][] adjMatrix;
  public GraphAdjMatrix(int noOfVertices, boolean directed) {
    super(noOfVertices, directed);
    adjMatrix = new double[noOfVertices][noOfVertices];
    for (int i = 0; i < adjMatrix.length; i++) {
      for (int j = 0; j < adjMatrix.length; j++) {
        adjMatrix[i][j] = Double.NaN;
      }      
    }
  }

  public void addEdge(int source, int destination, double weight) { 
    if (!directed) { 
      if (source != destination) { // Accounting for a self loop 
        this.adjMatrix[destination][source] = weight;
      }else{
        System.out.println("\nSelf loop Detected!\n");
        return; // Instant break out of function - ie if its undirected and self loop, nothing added
      }
    }
    this.adjMatrix[source][destination] = weight;   
  }



  public void removeEdge(int source, int destination) {
    this.adjMatrix[source][destination] = Double.NaN; 
    // Remember: counter not needed - archaic way of doing number of edges
    if (!this.directed) {
      this.adjMatrix[destination][source] = Double.NaN;
    }
  }



  public double getWeight(int source, int destination) {
    return adjMatrix[source][destination];
  }



  public int[] getNeighbours(int vertex) {
    int size = 0; // Initialise the future neighbours array allocation
    double[] edges = new double[noOfVertices]; // Making the array for all potential edges on vertices
    for (int vertexNo = 0; vertexNo<noOfVertices; vertexNo++) { // Looping over said array
      if (!Double.isNaN(adjMatrix[vertex][vertexNo])) { // If its not a NaN increase our neighbour list size
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

    int[] neighbours = getNeighbours(vertex);



    int degree = neighbours.length;

    if (directed) {


      for (int i = 0; i < noOfVertices; i++) {

        if (!(Double.isNaN(adjMatrix[i][vertex]))) {


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


      if (Double.isNaN(adjMatrix[currentSource][nextDest])) {


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
