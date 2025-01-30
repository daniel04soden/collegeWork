Chapters: B.5, 10.3


# Properties of trees

- Graphs are structures that model connections between vertices 
- A connected undirected acyclic graph is called a (free) tree (can be rooted?)
## Properties

- |E| = V-1 ie the amount of edges is - 1 the amount of vertices
- Any two vertices are always connected by a unique simple path. 
- Removing any edge will always create a forest of two trees
- Adding an edge will always create a circle

- The vertices in a tree are called nodes.

## Rooted tree
- Often a single node in a tree is designated as the root
- Such a tree are usually visualised with the root node at the top and all other modes dangling from the root
- Trees are generally isomorphic...
- The benefit of visualisation is that it makes explicit the unique path connecting each node with the root
	- Nodes on the path down are ancestors
	- Nodes on the path away are called descendants

- Every node is by definition both an ancestor and a descendant

- Immediate ancestor is a parent
- Root has no parent
- All immediate proper descendants of a node are called its children
- IE Node(s) below is child

- If a node has an children it is an internal node 
- A node without children is an external node or leaf
- Two or more Siblings are nodes sharing common parents
- A sibling of the parent is an unc the parent of the parent is called the grandparent.

- The number of children is the degree of a node 
- This definition differs from the degree of the corresponding graph