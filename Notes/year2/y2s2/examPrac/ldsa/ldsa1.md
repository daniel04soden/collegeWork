1.  
2.  

``` py
node(){
   key
   left
   right
}
insert(root, v){
    if v nil || v == root.value
        root = v
    else if v > root:
        root.right = insert(root.right,v)
    else:
        root.left = insert(root.left,v)
    return x
    }
```

``` py
preOrderTraversal(x):
    if x != null:
        preOrderTravers(x.left);
        print(x.key)
        preOrderTravers(x.right);
```

4.  

- 1.  

  - Multiple sub-problems
  - There must be an optimal substructure
  - Memoization
  - Bottom up problem
  - Reccurence relation

- 2.  
