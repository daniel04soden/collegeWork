- Adjacency list
- Adjacency matrix
	- Representations
	- Drawing them
- Asymptotic anal
	- Assigning omega,o or tita to two elements
	- determining from an algo
	- Tight,Lower, upper = Average, worst, best
- Binary search tree:
	- Post,pre,inorder
	- Insert
	- Delete
	- Transplant
	- Search
	- Successor
	- Pre-deccesor
```java
// Search binary tree
def search(x,k){
	if (x == nil || x==key){
		return x
	}else if (k < x.key){
		search(x.left)
	}else{
		search(x.right)
	}
}
```
```java
// Insert binary search tree
def insert(x,(k,v)){
	if k == x.key{	
	x = v	
	}else if (key < x.key){
		if x.left == null{
			x.left = k	
		}else{
			insert(x.left,(k,v))
		}
	}else{
		if x.right==null{
		x.right = k
		}else{
			insert(x.right,(k.v))	
		}
	}
}
def sucessor(x){
	if right !=nil{
		return max(x.right)	
	}
	
	y = x.parent
	
	while y!= nil and x == y.right{
	x = y
	y = y.parent
	}
	return y;
}

// Depth binary search tree
def depth(x){
int depth = 0;
if x == null{

	return depth;
}else{

	if (x.left!=null){
	return depth(x.left)+1
	}else{
	return depth(x.right)+1
	}
}
return depth;
}
```

```java
// Minimum and Maximum in binary search tree
def getMin(x){
while x.left !=null{
	x = x.left
}
return x
}
def getMax(x){
	while x.right!=null{
	x = x.right;
	}
	return x;
}

def sucessor(x){
	if x.right !=nil{
		return minimum(x.right)
	}else{
	y = x.parent
	while y!=nil && x == y.right{
	x = y
	y = y.parent	
	}
	return y;
	}
}

def predecessor(x){
	if x.left !=nil{
		return maximum(x.left)	
	}else{
	y = x.parent
	while y!=nil && x == y.left	{
	x = y
	y = y.parent	
	}
	return y;
	}

}
```

```java
def transplant(u,v){ // disconnecting
	if u.parent==nil{ // if u is inherently the root - as a root cant have a parent
	root = v	
	}else{
		if u == u.parent.left{ // if u is on the left - replace it with v
			u.parent.left = v	// u gone
		}else{ // if u is on the right, replace it with v
			u.parent.right = v	 // u gone
		}
	}if v!=nil{
	v.parent = u.parent
	}
}
```

```java
def delete(z){
if z.left ==null{ // If leaf node
transplant(z,z.right)
}else if (z.right ==null){ // if leaf node
transplant(z,z.left)
}else{ //otherwise
	y = min(z.right) // sucessor of z
	if y!= z.right{
		transplant(y,y.right)	
		y.right = z.right
		y.parent.right = y
	}
	tranplant(z,y)
	y.left= z.left
	y.left.parent = y
	}
}
```


- Red black trees
	- Properties:
		- Black node for root
		- Red has black children
		- Black weight the same across descendants
		- Leaf nodes always black
- Backtracking:
	- N-queens
	- Colouring? - old paper
	- HDD Allocation  
		- Explanation:
			- NP hard problems 	
			- Choice
			- Verify
			- Back track 
			- or Return
- Graph traversal
	- DFS - Stack based
	- BFS -  Queue based  - can be used for shortest path unweighted graph
- Connected components:
	- Where you can get from one node to another - un-directed graph
	- Disjoint sets
		- make-set
		- find-set
		- union - representatives
		- Two ways to represent disjoint set
			- Linked-list back to first and next
			- Tree - root node is rep - keep track of rank
				- Rank is how DEEP the tree is
	- Strongly connected components
		- Directed graphs where you can get to every vertex from one component to another 

- Dynamic Programming
	- Conditions
		- Recursive formulation
		- Overlapping subproblems
		- Optimal substructure
	- Bottom up  - Rod cutting
	- Top to bottom
	- Tabulation - Hungry monkey, LCS
- Shortest paths
	- Dijkstras - no negative weights
		- Init q with infinty
		- 
	- Bellman fords - stop infinite loops of negativity
	- Bellman DAG? - no need to check for infinite (negative circle weight) - Directed acyclic graph
- Min span trees
	- Spanning tree - euler path - no cycles  
	- Min kruskals tree - multiple smaller ones to get the biggest spanning tree
	- Pimms - one go building spanning tree
	- Kruskals - make set from graph and then sorts edges non-descending, go through in new-increasing order,if not connected via reps, union and add to A set
	- Prims - Graph - go through vertices - root null - rest infinite - while Q is not empty, loop through neighbours - if distance between u and v is less than v, let v be w(u,v), then reduce key - using min heap
- All pairs shortest path
	- Matrix multiplication
	- Floyd- algorithm - dense graphs
	- Johnson's algorithm - sparse graphs