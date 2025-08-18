# Graph Traversal Algos

- Order in which things are processed
 
## Breadth First search - Queue

### Idea

- Walks along the edges to discover all vertices reachable from S
- Discovery is in order of increasing distance from s
- Therefore related to the shortest path problem
- Create tree ds where in the graph where we know the shortest path back to source node

#### Assumptions
1. During the search procedure each node v stores a colour with the following interpretation:
    - White (not discovered)
    - Grey (discovered, needs processing)
    - Black (discovered, no more processing)
2. The distance to the source
3. Parent node indicating the way back to the source

- The algorithm uses a FIFO queue Q to manage the grey vertices for processing

### Applications
- Search engines (web crawlers)
- Social Networks (friends of friends)
- Navigation systems (nearby snapchat)
- Networking (peer to peer routing) - what are your neighbours, their neighbours
- Garbage collection (managing memory) - which objects are still pointed to and are now not reachable


## Depth First search - Stack

## Topological sort 

