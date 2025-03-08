import java.util.LinkedList;

public class GraphAdjList extends AbstractGraph {

    private record Edge(int destination, double weight) {}

    private LinkedList<Edge>[] neighbours; // Array of linkedlists which
                                           // conatain different edges

    // Constructor
    public GraphAdjList(int noOfVertices, boolean directed) {
        super(noOfVertices, directed); // Graph attributes
        this.neighbours = new LinkedList[noOfVertices]; // Neighbours are the
                                                        // linkedlist of edges
        for (int i = 0; i < noOfVertices; i++) {
            neighbours[i] = new LinkedList<Edge>(); // Filling up the array with linkedlists of edges
        }
    }

    public void addEdge(int source, int destination, double weight) {
        if (!directed) { 
          // Ensuring no duplicate values
            boolean isDuplicate = neighbours[source].contains(new Edge(destination, weight)); 
            if ((source == destination) || isDuplicate) {// Prevenitng self
                                                         // loops
                return;
            } else {
              // If all conditions okay, add undirected edge
                neighbours[destination].add(new Edge(source, weight));
            }
        }
        // Add directed edge
        neighbours[source].add(new Edge(destination, weight));
    }

    public void removeEdge(int source, int destination) {
      // first do undirected removal, we happen regardless
        for (Edge e : neighbours[source]) { // Loop over the neighbours at the index of the source vertex
            if (e.destination == destination) { // If destination found
                neighbours[source].remove(e); // If so, remove the edge from source to destination
            }
        }
      // Then check for undirected removal
        if (!directed) {
            for (Edge e : neighbours[destination]) {
                if (e.destination == source) { // If source found
                    neighbours[destination].remove(e); // If so, remove the edge from destination to source
                }
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
        for (int vertexNo = 0; vertexNo < noOfVertices; vertexNo++) { // Looping over said array
            if (!Double.isNaN(getWeight(vertex, vertexNo))) { // If its not a NaN increase our neighbour list size
                edges[vertexNo] = vertexNo;
                size++;
            } else { // Otherwise add to edges but don't increase size, won't be in future list
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
        int degree = 0; // Init degree
        // Asserting degree as neighbours for undirected graphs since no
        // in,out degrees - just whats connected to vertex ie neighbour
        int[] myNeighbours = getNeighbours(vertex);
        if (!directed) {
            degree += myNeighbours.length;
        } else {
            int inDegree = 0;
            int outDegree = myNeighbours.length; // Outdegree is the neighbours 

            for (int i = 0; i < noOfVertices; i++) {
                if (!(Double.isNaN(getWeight(i, vertex)))) {
                    inDegree++; // checking anything coming in
                }
            }
            degree = inDegree + outDegree; // Degree is the sum of in and out
        }
        return degree;
    }

    public boolean isPath(int[] nodes) {
        int n = nodes.length; // Same logic as matrix
        for (int i = 0; i < n - 1; i++) {
            int currentSource = nodes[i];
            int nextDest = nodes[i + 1];
            // Simply compare weight of current and next node in potential path
            if (Double.isNaN(getWeight(currentSource, nextDest))) { 
                return false; // False if not existenet (doublenan)
            }
        }
        return true;
    }

    public int getNoOfEdges() {
        int countNoOfEdges = 0; // Init as 0
        for (int i = 0; i < noOfVertices; i++) {
            for (Edge e : neighbours[i]) { // Loop over the neighbours at the index of the source vertex
                if (!(Double.isNaN(e.weight))) {
                    countNoOfEdges++; // If not double nan, increment
                } else {
                    continue; // ignore
                }
            }
        }
        if (!directed) {
            countNoOfEdges = countNoOfEdges / 2; // if undirected, divide by 2 
        }
        return countNoOfEdges;
    }
}
