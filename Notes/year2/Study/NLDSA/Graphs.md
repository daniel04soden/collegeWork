# What are graphs?

- **Abstract data type used to model connections**
- They have a finite set of **vertices** and a set of **edges** that
  connect these pairs of vertices
- Eg: A social network where profiles can be modelled as vertices and
  the contacts as edges.

## Directed vs un-directed graphs

- Directed graphs where the **edges are ordered pairs of vertices** ie
  (1,2) (2,1) has to be declared explicitly

- Un-directed graphs **are where edges are two element subsets of
  vertices** ie (1,2) implies (2,1).

- For example with V = {1,2,3,4,5,6}

  - The edge set of : \| {1,2} {2,1} {2,4}, {2,5}\| defines a directed
    graph.
  - The edge set of : \| {1,2} {2,4}, {2,5}\| defines an un-directed
    graph.

- Self loops are not possible in un-directed graphs. All connections are
  symmetric.

- In certain scenarios we may also need weighted graphs which are edges
  with assigned values.

- Every directed graph implies an un-directed graph by creating edges
  for every edge that is not a self loop and ignoring the direction

- Every un-directed graph implies an directed graph by creating edges in
  either direction for every un-directed edge.

- **Reachable**: A path exists containing either vertex

- All vertices reachable from each other in an un-directed graph form a
  connected component of the graph \## Edges

- An edge is called an incident from or leaving the vertex you and is
  incident to or entering the vertex v

- Two vertices connected are called adjacent or neighbours

- The degree is the number of incident edges (simple terms, amount of
  connections it has)

- There is an in-degree (how many arrows going towards it) and an out
  degree, how many is it itself connecting to. This is not possible in
  un-directed graphs, instead it is just the degree. \## Paths and
  Cycles

- **Path** from a vertex to another farther over vertex is a sequence of
  vertices so that the intermediate vertices are connected by an edge.

- Path p is said to contain these vertices

- It is simple if all of them are unique ie never going the same path
  twice

- A sub path is a continuous subsequence of its vertices

- ie 2,5,4 is a sub-path of 1,2,5,4

- Cycles are paths which contain at least one edge and the first and
  last vertex are the same ie v0 = vn

- A graph that does not contain any cycles is **acyclic**

- A vertex is considered reachable if a path exists between v and v\'

- All vertices reachable from each other in an un-directed graph form a
  connected component of a graph

- A sub-graph is a subset of its vertices and edges

- The subgraph is **induced** when a set of vertices is obtained by
  keeping all edges where possible

- **Isomorphic** is when two graphs structure looks the same

- **DAG** = directed acyclic graph

- Undirected acyclic graph with a single connected component is known as
  a tree (more on them later)

- On that, if this graph has multiple connected components ie trees, it
  is called a **forest**.

- A graph where all vertices are connected to each other, are called a
  clique

- An euler path is a path visiting every single exactly once in case of
  the first and last vertex, then it is a euler circle.

- A planar graph is a graph which can be drawn on a 2d plane without any
  edges crossing. \# Graph representations in programming

- Now the main question is how do we represent these abstract data
  types?

- Graphs are commonly implemented in two ways, through linked lists
  where we have an array of linked lists and the connected nodes in this
  linked list correspond to the edges.

- Or we can also use an adjacency matrix or more commonly known as a 2d
  array

- In a adjacency matrix it is commonly filled with zeros or minus ones
  and

### What I need to understand for both:

- How to draw both representations = Drawings below
- How to construct one from the other \## Graph adjacency list - Linked
  list

``` java
LinkedList<Edge>[] neighbours
```

- Sample structure of an adjacency list
- More suitable for sparse graphs where there are far less vertices than
  there are edges
- O(V+E) \![*Pasted image 20250513171807.png*]{.spurious-link
  target="Pasted image 20250513171807.png"} \## Graph adjacency matrix -
  2d array

``` java
int[][] adj
```

- Sample structure of an adjacency matrix
- More suitable for dense graphs and algorithms with frequent edge
  lookups \![*Pasted image 20250513171857.png*]{.spurious-link
  target="Pasted image 20250513171857.png"}
