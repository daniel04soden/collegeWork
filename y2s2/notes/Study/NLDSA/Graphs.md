# What are graphs?

- **Abstract data type used to model connections**
- They have a finite set of **vertices** and a set of **edges** that connect these pairs of vertices
- Eg: A social network where profiles can be modelled as vertices and the contacts as edges.

## Directed vs undirected graphs 

- Directed graphs where the **edges are ordered pairs of vertices** ie (1,2) (2,1) has to be declared explicitly
- Undirected graphs **are where edges are two element subsets of vertices** ie (1,2) implies (2,1).

- For example with V = {1,2,3,4,5,6}
	- The edge set of : | {1,2} {2,1} {2,4}, {2,5}| defines a directed graph.
	- The edge set of : | {1,2} {2,4}, {2,5}| defines an undirected graph.

- Self loops are not possible in undirected graphs. All connections are symmetric.
- In certain scenarios we may also need weighted graphs which are edges with assigned values.
- Every directed graph implies an undirected graph by creating edges for every edge that is not a self loop and ignoring the direction
- Every undirected graph implies an directed graph by creating edges in either direction for every undirected edge.
- **Reachable**: A path exists containing either vertex
- All vertices reachable from each other in an undirected graph form a connected component of the graph
## Edges

- An edge is called an incident from or leaving the vertex you and is incident to or entering the vertex v
- Two vertices connected are called adjacent or neighbours
- The degree is the number of incident edges (simple terms, amount of connections it has)
- There is an in-degree (how many arrows going towards it) and an out degree, how many is it itself connecting to. This is not possible in undirected graphs, instead it is just the degree.
## Paths and Cycles
- **Path** from a vertex to another farther over vertex is a sequence of vertices so that the intermediate vertices are connected by an edge.
- Path p is said to contain these vertices
- It is simple if all of them are unique ie never going the same path twice
- A sub path is a continuous subsequence of its vertices
- ie 2,5,4 is a sub-path of 1,2,5,4
- Cycles are paths which contain at least one edge and the first and last vertex are the same ie v0 = vn
- A graph that does not contain any cycles is **acyclic**

# Graph representations in programming

## Graph adjacency list - Linked list

## Graph adjacency matrix - 2d array