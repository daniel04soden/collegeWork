# Graphs 
- Located in 20.1 - Needed for assignments
- B.4 - Graphs
- Implement graphs
- Details in chapter

- Graphs are abstract data type to model connections
- Can be represented by either lists or matrices 
- A graph G = (V,E) is a datatype comprising of 
	- A finite set V of vertices
	- And a set of E edges that connect pairs of vertices
- In a social network the profiles can be modelled as vertices and the contacts as edges. 
- In communication networks routers and endpoints are often modelled as vertices and the communication links are the edges
- Maps can be abstracted so that locations are the vertices and connections are the edges

## Types of Graphs
1. Directed graphs where the edges are ordered pairs of vertices
2. Un-directed graphs where the edges are two element sub sets of vertices

- In directed graphs self loops ie edges form a vertex to itself as possible while in directed graphs all edges connect exactly two vertices
- in undirected graphs all connections are symmetric, this is not necessarily the case for undirected graphs
- For some applications, edges are assigned values in which the case the structure is called a weighted graph


### More definitions 
#### Vertices/Edges/Degrees
- Edges is an incident from or leaving the vertex u and is incident to or entering a vertex v
- Two vertices who are adjacent or neighbours if the edge u,v 

- The degree of a vertex is the number of incident edges
- In a directed graph the in degree and out degree can be distinguished
#### Paths/Cycles
- Path from a vertex vo to vertex vk is a sequence of vertices p (vo,v1...vk) so that all intermediate vertices are connected by an edge
- The path p is said to contain the vertices (vo to the vertex vk)
- A path is called simple if all vertices on the path are distinct.

- Cycles is a path where the first and last cycle are the same ie v0 = vk - Loop around essentially
- A graph that does not contain any cycles is acyclic

#### Reach-ability

- Every directed graph implies an undirected version by creating edges for every edge that is not a self loop ignoring the direction
- A vertex is reacable from v is written as v -> v\` if a path exists containing both v and v\`
- All vertices reachable form each other in an undirected graph form a connected component of the graph.

## Subgraphs and equivalence

- Subgraph - subset of vertices and edges.
- Subgraph induced by set of vertices is obtained by keeping all edges where possible
- Isomorphic if their structure looks the same
- Mapping is essentially the same
- Edge structure is the same
- Although they may not contain the same data, their structure is the same
