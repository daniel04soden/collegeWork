public class GraphAdjMatrix extends AbstractGraph {

    private final double[][] adjMatrix;

    public GraphAdjMatrix(int noOfVertices, boolean directed) { // Compact way to represent sparse graphs
        super(noOfVertices, directed); // Assigning the universal graph attributes
        adjMatrix = new double[noOfVertices][noOfVertices];
        for (int i = 0; i < adjMatrix.length; i++) { // Filling up the edges as Double NaN to assert no edges
            for (int j = 0; j < adjMatrix.length; j++) {
                adjMatrix[i][j] = Double.NaN;
            }
        }
    }

    public void addEdge(int source, int destination, double weight) {
        if (!directed) { // Undirected
            if (source != destination) { // Accounting for a self loop
                this.adjMatrix[destination][source] = weight; // Assigning the oppposite weight
            } else {
                return; // Instant break out of function - ie if its undirected and self loop, nothing added
            }
        }
        this.adjMatrix[source][destination] = weight; // Natural behaviour assumes directed,from source to destination
    }

    public void removeEdge(int source, int destination) {
        this.adjMatrix[source][destination] = Double.NaN; // By default, remove from source to destination
        if (!this.directed) { // Remove other side if undirected
            this.adjMatrix[destination][source] = Double.NaN;
        }
    }

    public double getWeight(int source, int destination) {
        return adjMatrix[source][destination]; // Simple return of value from
        // source to destination
    }

    public int[] getNeighbours(int vertex) {
        double[] edges = new double[noOfVertices]; // Making the array for all potential edges on vertices

        for (int vertexNo = 0; vertexNo < noOfVertices; vertexNo++) { // Looping over said array
            if (!Double.isNaN(adjMatrix[vertex][vertexNo])) { // If its not a NaN increase our neighbour list size
                edges[vertexNo] = vertexNo; // Edges vertexNo assigned
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
        int degree = 0;
        if (!directed) { // If undirected
            for (int i = 0; i < noOfVertices; i++) {
                if (
                    !Double.isNaN(adjMatrix[i][vertex]) ||
                    !Double.isNaN(adjMatrix[vertex][i]) // Or rather than
                    // individual checks,
                    // works for
                    // undirected
                ) { // Either direction - stops double counting
                    degree++;
                }
            }
        } else {
            for (int i = 0; i < noOfVertices; i++) {
                // Two separate if checks so that they are independently looked for, ie
                // say if one is met, it wont ignore the other
                if (!Double.isNaN(adjMatrix[vertex][i])) { // Checks source to (out degree)
                    // destination first
                    degree++;
                }
                if (!Double.isNaN(adjMatrix[i][vertex])) { // Then increases (in degree)
                    // only if the
                    // otherside also
                    degree++;
                }
            }
        }
        return degree;
    }

    public boolean isPath(int[] nodes) {
        int n = nodes.length;
        for (int i = 0; i < n - 1; i++) {
            int currentSource = nodes[i];
            int nextDest = nodes[i + 1]; // For comparison and readability
            // If double nan found between array of vertices, path is false
            if (Double.isNaN(adjMatrix[currentSource][nextDest])) {
                return false;
            }
        }
        return true; // If double nan never found - through comparison has to
        // be a path
    }

    public int getNoOfEdges() {
        int countNoOfEdges = 0;
        for (int i = 0; i < noOfVertices; i++) {
            for (int j = 0; j < noOfVertices; j++) {
                if (!(Double.isNaN(adjMatrix[i][j]))) { // If not double nan,
                    // has to be an edge
                    countNoOfEdges++;
                } else {
                    continue;
                }
            }
        }
        if (!directed) {
            countNoOfEdges = countNoOfEdges / 2; // if undirected, edges simply
            // divided by 2
        }
        return countNoOfEdges;
    }
}
