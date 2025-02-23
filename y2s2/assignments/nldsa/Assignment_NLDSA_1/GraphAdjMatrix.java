import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

public class GraphAdjMatrix extends AbstractGraph {

  private final double[][] adjMatrix;

  public GraphAdjMatrix(int noOfVertices, boolean directed) {
    super(noOfVertices, directed);
    this.adjMatrix = new Double[noOfVertices][noOfVertices];
    for (int i = 0; i < this.adjMatrix.length; i++) {
      for (int j = 0; j < this.adjMatrix.length; j++) {
       this.adjMatrix[i][j] = Double.NaN;
      }      
    }
  }

  public void addEdge(int source, int destination, double weight) { 
    adjMatrix[source][destination] = weight; // If this wasnt a weighted graph value could be 1 or true?
    if (!this.directed) { // Undirected
      adjMatrix[destination][source] = weight; // If undirected, set the same as the weight..?
    }
  }

  public void removeEdge(int source, int destination) {
    adjMatrix[source][destination] = Double.NaN; // 0 or null??? isn't it nan if its connected
    // Remember: counter not needed - archaic way of doing number of edges
    if (!directed) {
      adjMatrix[destination][source] = Double.NaN;
    }
  }

  public double getWeight(int source, int destination) {
    return adjMatrix[source][destination];
  }

  public int[] getNeighbours(int vertex) {
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
    return neighbours;
  }

  public int getDegree(int vertex) {
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
    return degree;
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
