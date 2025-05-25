# What are trees?

-  **Tree**: Connected undirected acyclic graph.
- Number of edges is always no of edges = Vertices -1 -> -1 comes from root
- Any two vertices are always connected by a unique simple path
- Removing any edge with always create a **forest of two trees**
- Adding an edge anywhere in the tree will **ALWAYS create a circle**
- **Nodes**: vertices in a tree
- **Root**: Single node in a tree is distinguished as a root 
- Every node is by **definition both an ancestor as well as a descendant of itself.** If it should be excluded then we talk about the proper anscestors/descendants
- Nodes on the path towards the root up are **ancestors** 
- Nodes on the path away the root down are called **descendants**
- Immediate proper ancestor of a node is the **parent** 
- Root has no parents (batman)
- An immediate proper descendants of a given node are it's children,grandchildren and great grandchildren.
- If a node has any children then it is an internal node
- A node without children is a **leaf**
- Two or more nodes sharing a common parent are **siblings**
- The number of children is called the **degree** of a node

## Representations of trees
- Every node can be considered the root of a sub-tree induced by its descendants
- Allows for recursive structures, *pass value into a recursive call, treat it as a root*
### Recursive structure:
- A single root node
- Sub trees
- Children of the root node are the roots of the sub trees

```java
public class Tree<Node>{
	private class Node<T>{
	private T value;
	private Node<T>[] children;
	}
	private Node<T> root;
}
```

- An ordered tree is a structure where the order of the children matters (binary tree or binary search tree)
-  If the number of children in each node is the same then this is called a k-ary tree
- A binary tree is an ordered tree with exactly two sub trees in each node which are then referred to  as the left and the right child

# Traversing a binary tree

- Sometimes needed to process all vertices of a tree in order, the recursive structure of trees permits this to derive a very simple algorithm for traversing all nodes
- The call stack is used to keep track of the  position in the tree
### Steps:
1. Starting from the root
2. If x is null we finish, ie do nothing
3. Otherwise we output the value - if it exists
Remaining nodes are in the left and right sub tree which is called recursively 
4. Then we visit all on the left 
5. Then we visit all to the left

### In order snippet
```java
visit(x){
	if x == null
		return
	else:
		visit(left(x))
		print(x)
		visit(right(x))
}
```
- If returning do it like this
```java
visitInorder(x){
	if (x!=null){
	l = visitInorder(x.left)	
	r = visitInorder(x.right)	
	return l + [x] + r
	}
}
```
![[Pasted image 20250513184318.png]]
### Pre-order snippet
```java
visit(x){
	if x == null
		return
	else:
		print(x)
		visit(left(x))
		visit(right(x))
}
```
![[Pasted image 20250513184338.png]]
### Post-order snippet
```java
visit(x){
	if x == null
		return
	else:
		visit(left(x))
		visit(right(x))
		print(x)
}
```
![[Pasted image 20250513184355.png]]
# Binary search trees
- The worst case time for operations of insert delete search get min and get max for BST is Olog(n)

- In a binary search tree, nodes contain a unique and comparable key
- Pointers to left and right subtrees
- Sometimes useful values also include a value associated with the key
- A pointer back to the parent node
```c
struct Node{
	int key;
	int ?value;
	struct Node *left;	
	struct Node *right;	
	struct Node ?*parent;
}
```


## Searching in a binary tree
- in binary search trees, as implied in the name of the ADT, we are able to utilise binary searching. Anything greater than the root node is located on the right and anything located on the left sub trees is less than the root node. This then applies for the node downwards sub trees.

```c
// x being root node
// Key being key of new value
int search(x,k){
	if (x == null || k == x.key){
		return x;
	}else if(k < x.key){
	return search(x.left,key)
	}else{
	return search(x.right,key)
	}
}
```