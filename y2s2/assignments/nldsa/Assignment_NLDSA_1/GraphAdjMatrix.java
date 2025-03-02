public class GraphAdjMatrix extends AbstractGraph {

  private final double[][] adjMatrix;

  public GraphAdjMatrix(int noOfVertices, boolean directed) {
    super(noOfVertices, directed);
<<<<<<< HEAD
    this.adjMatrix = new Double[noOfVertices][noOfVertices];
    for (int i = 0; i < this.adjMatrix.length; i++) {
      for (int j = 0; j < this.adjMatrix.length; j++) {
       this.adjMatrix[i][j] = Double.NaN;
=======
    adjMatrix = new double[noOfVertices][noOfVertices];
    for (int i = 0; i < adjMatrix.length; i++) {
      for (int j = 0; j < adjMatrix.length; j++) {
        adjMatrix[i][j] = Double.NaN;
>>>>>>> 72fe975a8cd5ed2403e047f5c253ddd57f3c5d24
      }      
    }
  }

  public void addEdge(int source, int destination, double weight) { 
<<<<<<< HEAD
    adjMatrix[source][destination] = weight; // If this wasnt a weighted graph value could be 1 or true?
    if (!this.directed) { // Undirected
      adjMatrix[destination][source] = weight; // If undirected, set the same as the weight..?
=======
    if (!directed) { 
      if (source != destination) { // Accounting for a self loop 
        this.adjMatrix[destination][source] = weight;
      }else{
        System.out.println("\nSelf loop Detected!\n");
        return; // Instant break out of function - ie if its undirected and self loop, nothing added
      }
>>>>>>> 72fe975a8cd5ed2403e047f5c253ddd57f3c5d24
    }
    this.adjMatrix[source][destination] = weight;   
  }

  public void removeEdge(int source, int destination) {
<<<<<<< HEAD
    adjMatrix[source][destination] = Double.NaN; // 0 or null??? isn't it nan if its connected
=======
    this.adjMatrix[source][destination] = Double.NaN; 
>>>>>>> 72fe975a8cd5ed2403e047f5c253ddd57f3c5d24
    // Remember: counter not needed - archaic way of doing number of edges
    if (!directed) {
      adjMatrix[destination][source] = Double.NaN;
    }
  }

  public double getWeight(int source, int destination) {
    return adjMatrix[source][destination];
  }

  public int[] getNeighbours(int vertex) {
<<<<<<< HEAD
    int size = 0;
    for (int i = 0; i < noOfVertices; i++) {
     if (adjMatrix[vertex][i]!=Double.NaN) {
      size++; 
     } 
    }
    int[] neighbours = new int[size];
    int j = 0;
    for (int i = 0; i < size; i++) {
      if (adjMatrix[vertex][i]!=Double.NaN) {
        neighbours[i] = getWeight(vertex, i);
        j++;
      }else{
        continue;
      }
    }
=======
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

>>>>>>> 72fe975a8cd5ed2403e047f5c253ddd57f3c5d24
    return neighbours;
  }

  public int getDegree(int vertex) {
<<<<<<< HEAD
    int degree = 0;

    for (int i = 0; i < size; i++) {
      if (adjMatrix[i][vertex]!= Double.NaN) {
        if(!(adjMatrix[i][vertex] == adjMatrix[i][vertex]))
        degree++;
      }else{
        continue;
      }
    }
      if(!directed){
        degree = degree / 2;
      }
=======
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
>>>>>>> 72fe975a8cd5ed2403e047f5c253ddd57f3c5d24
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
