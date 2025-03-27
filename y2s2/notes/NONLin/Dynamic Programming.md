Matrix Chain multiplicatoin
- Two matrices a and b are calcualted by multiplying each row of A with each column of B 
- The result is a matrix of C E R ^pxq
- Ie the number of columns of A and the number of rows of B must be the same
- In a simple sense C=AB is calucalted as p\*q\*r 
- There are more sophisticated divide and conquer algorithms for this
- In a simple implementation it is a triply nested for loop

Multiplying more than two Matrices
- Now lets look at the problem of multiplying more than two matrices
- For this, the number of rows and columns of the subsequent matrices in the chain must match 
- Matrix multiplication is associative so the result does NOT depend on how we set parenthesis
- We can thus clacualte the matrix chain by B1 = A1A2 first then C = B1 A3
- Or B2 = A2A3 first and then C = A1B2

- How we calc C = (A1,A2), A3 = A1(A2,A3) doesn't matter for the result but the runtime??
- Depending on what order we place our brackets for this multiplication, you may get a vastly up to 10x difference in runtime 
- Therefore how we set parenthesis in matrix-chain multiplication matters


Finding the optimal Parenthesization

- For the dynamic programming paradigm to work the following questions need to be answered:
	1. **What is the optimisation problem to be solved?**
	2. **Does this formulation exhibit optimal substructures?**
	3. **Does the problem have a recursive form?**
	4. **How can overlapping subproblems be exploited?**

What is our problem?

- Given the dimensions of the matrices, the goal is to find the minimum number of atomic multipplcaitions requried to calcualte the matrix chain product
```python
A1*...*An
```

Does this formulation exhibit optimal substructures?
- If the optimal brackets contain a multiplation between the kth and (k+1)th element
- then the overall optimal solution can be constructred from the optimal solutions for hte two sub chains A1x...An and Ak+1...An
- Why? - Because if any of the two sub cahins would be computable with less atomic multiplications then we should have used this in the optimal solution in the first place.

