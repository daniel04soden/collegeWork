

# Recursive Depth first algorithm

- Sometimes it is needed to process all vertices of a tree in order 
- This structure of trees allows us to derive a very simple algorithm for traversing all nodes
- The call stack is used to keep track of the position in the tree
- Starting from the root the following algorithm will visit all nodes
```python

def VISIT(x):

if x = nil: # Base case do nothing - By the time it gets to the end...
return   # Does x actually = x+1? or are we using this in another function


else:
	output or process value[x]  # Process current node if exists
	visit left[x]
	visit right[x]

```

## Order of traversal

 - In the previous example the root node is processed before any of its descendants
 - This is called pre-order traversal of the nodes
 - We can also have in order traversals where the nodes are processed after the left sub tree but before the right sub tree.
 
```python

def VISIT(x):

if x = nil: 
return   


else:
	visit left[x]
	output or process value[x]  
	visit right[x]

```

- Or after all descendants have been processed this is called post order traversal

```python

def VISIT(x):

if x = nil: 
return   


else:
	visit left[x]
	visit right[x]
	output or process value[x]  

```