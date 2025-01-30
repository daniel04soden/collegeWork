
# Subgraphs and Equivalence

- Subgraphs are a subset of its vertices and edes.

- The subgraph is induced by a  set of vertices V \` C V is obtained by keeping all edges wehre possible
- Two graphs are isomorphic if their structure looks the same


# Important special cases
## DAG
- DAG: Directed acyclic graph 
- There is no way to start and stop at the same node
- From any node you can move forward in a node but then you cannot move back


## Tree
- A tree is also known as an un-directed acyclic graph with a single connected component
## Forest 

- An undirected acyclic graph comprising multiple connnected components is called a forest

## Clique
- A clique is a graph where every vertex is connected to every other vertex.
-  Then the number of edges here is $|E| = |V||V-1 = O(V^2)$

## Euler path

- A Euler path is a path which visits every vertex exactly once in case the first and last vertex are connected it is then known as an Euler circle.

## Planar graph

- Is one which can be drawn on a 2D plane without any edge crossing
- If not possible, it is known as a Non-planar graph.

# Representations of graphs

- There two common representations of graphs  
- the adjacency-lists representation is based on linked list
```java
LinkedList<Edge>[] neighbours;
```
- The adjacency matrix representation is based on matrices
```java
int[][] adj
```

## Adjacency-list

- Use linked lists to store the neighbours for each vertex

```java
LinkedList<Edge>[] neighbours;
```

$$
G = (V,E)
V = {1,2,3,4,5}

E={(1,2),(1,5),(2,3),(2,4),(2,5)(3,4),(4,5),(4,4)}
$$


- How do we find if something is connected?
	- Search the linked list
	- We go to the node and traverse the linked list in a path
	- Loop checking if it is equal
	- Once reached desired value n return
- For undirected graphs we need acknowledge that each edge appears in two lists
- Make sure when value added we need to add (2,5) and (5,2) not just 2,5.
- **Directed and undirected included in assignment**

- Weights can be stored in the nodes corresponding to the edges
-  Labelling arrows in dumb terms

## Adjacency Matrix


- Using a VxV matrix to store binary indicators if pairs of vertices are connected
### Directed graphs
- Reminder idea: Like genetic tables in biology 
- For 4,5 we have a binary at x = 4 and y = 5  identifying the edge.
- Must faster
- Faster than LinkedList??
- **The adjacency matrix provides fast edge lookups and is well-suited for dense graphs, but it consumes more memory**
- Weight can be stored directly in the adjacency matrix if required 
- No one forbidding us from 0 weights..
### Undirected graphs

- For undirected graphs the adjacency matrix is symmetric 
- Therefore for 4,5 we add the binary a their axes, then we need to add the inverse for 5,4 

# Comparison

## Memory requirements

- **Reminder: User XOR for variable swapping for this**
- Adjacency lists store each edge in a linked list node therefore the requried memory is  $$ O(V+E) $$
- Whereas for an adjacency matrix it needs to be fully allocated for each vertex pair thus the required memory is $$O(V^2)$$
- Reminder: Big O we ignore constants - Important for undirected graphs as correspondence occurs 
- Adjacency lists are more suitable for sparse graphs where $|E| << |V^2|$
- If sparse we choose list, if not we use matrix in terms of considering memory
- However for speed we are no matter what, better off using the matrix.

## Asymptotic runtime

- For testing if an edge E requries us to search the adjacency list of vertex u therefore the worst case runtime is  $O(V)$
- However for the same test in an adjacency matrix, it only requires a single lookup therefore the runtime for this operation is O(1)


- **Adjacency matrices** are more suitable for dense graphs and algorithms with frequent edge lookups. 
- Whereas our lists would again be fore more sparse graphs 