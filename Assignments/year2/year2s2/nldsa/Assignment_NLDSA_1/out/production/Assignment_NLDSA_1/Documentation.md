# Non-Linear Data Structures and algorithms - Assignment 1

## Graph Adjacency List

### Matrix Constructor

- We being by init the Matrix in which

``` java
private final double[][] adjMatrix;

public GraphAdjMatrix(int noOfVertices, boolean isDirected){
    super(noOfVertices,isDirected);
    for(int i = 0; i<adjMatrix.length; i++){
    for(int j = 0; j<adjMatrix.length; j++){
      adjMatrix[i][j];
    }
    }
```

- In the above constructor we create a matrix of empty values to signify
  the lack of edges between the
