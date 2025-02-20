import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

public class GraphAdjMatrix extends AbstractGraph {

  private final double[][] adjMatrix;

  public GraphAdjMatrix(int noOfVertices, boolean directed) {
    super(noOfVertices, directed);
    adjMatrix = new Double[noOfVertices][noOfVertices];
    for (int i = 0; i < adjMatrix.length; i++) {
      for (int j = 0; j < max; j++) {
       adjMatrix[i][j] = Double.NaN;
      }      
    }
  }

  public void addEdge(int source, int destination, double weight) { 
    this.adjMatrix[source][destination] = weight; // If this wasnt a weighted graph value could be 1 or true?
    if (!this.directed) { // Undirected
      this.adjMatrix[destination][source] = weight; // If undirected, set the same as the weight..?
    }
  }

  public void removeEdge(int source, int destination) {
    this.adjMatrix[source][destination] = Double.NaN; // 0 or null??? isn't it nan if its connected
    // Remember: counter not needed - archaic way of doing number of edges
    if (!this.directed) {
      this.adjMatrix[destination][source] = Double.NaN;
    }
  }

  public double getWeight(int source, int destination) {
    return adjMatrix[source][destination];
  }

  public int[] getNeighbours(int vertex) {
    List<Integer> neighbours = new ArrayList<Integer>();

    int j = 0;
    for (int i = 0 ; i < noOfVertices - 1; i++) {
      if (!(Double.isNaN(neighbours.get(j)))) {
        neighbours.set(j,adjMatrix[vertex][j]);
        j++;
      }else{
        continue;
      }
    }

    Object[] objects = neighbours.toArray();
    return neighbours;
  }

  public int getDegree(int vertex) {
    throw new RuntimeException("Not yet implemented!");
  }

  public boolean isPath(int[] nodes) {
    int n = nodes.length;
    for (int i = 0; i < n - 1; i++) {
      if (!(adjMatrix[i][i + 1] == nodes[i + 1])) {
        return false;
      } 
    }
    return true;
  }

  public int getNoOfEdges() {
    int countNoOfEdges = 0;
    for (int i = 0; i < noOfVertices; i++) {
      for (int j = 0; j < noOfVertices; j++) {
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
