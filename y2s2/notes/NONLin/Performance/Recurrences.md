# Chapters

- 4.3,.4,.5

# How to analyse the performance of recursive Algorithms?

- The performance of the iterative algorithms is mostly assessed by analysing its loops to determine how often the innermost instructions are executed
- What about recursive algorithms?
- It is possible to turn a tail recursive algorithm into a non recursive algorithm ie a Tail call Optimisation

- TCO is only applicable if the recursion is limited one single call at the end of the procedure
- However if this is not the case, if there are multiple recursive calls within the procedure other techniques must be used to evaluate the performance of an algorithm

- The overall runtime of a recursive procedure depends on the depth of the call stack and how many recursive calls occur in parallel within the procedure.
- What is remaining runtime of the procedure for dividing and combining without considering recursion
- Can the base case be solved efficiently

## Assumptions

- Although recursion is not limited to divide and conquer strategies many important algorithms can be forumlated within this framework
- Looking at the components of the divide and conquer schema we can identify some parameters that can be used to evaluate the performance.


- The base case assumes the instance to be small enough usually implying O(1)
- The problem is divded into smaller sub problems often of some fractional size n/b
- Each problem is then solved using A itself if the runtime of is T(n) then on the smaller instances this will take a T(n/b) to complete
- The work is done in the reduction step and we must quantify the performance as some funciton f(n). 


## Recurrence

- Putting this all together the runtime of a recursive d&c algorithm can often be characterised by a reccurence equation
$$
T(n) = aT(n/b) + f(n)
$$


- The overall runtime is determined by :
	- How many times recursion is called
	- By what fraction 1/b the sub problem is smaller than then the original problem each time
	- The driving function f(n) that describes the runtime required for each instance

 - All that remains to be done is finding the solution to the recurrence equation

## Substitution Method


- Sometimes it is possible to guess the correct solution and then show its correct
- We know merge sort is -O-(nlogn)
- However for new algorithms no one will be able to tell us the correct solution adn we have to make an educated guess for ourselves


### How to make a good guess

- Basis on similar problems
- Use a recursive tree
- Decouple upper and lower boundsm it is often easer to show that T(n) = omege(g(n)) and T(n) = O(g(n) seperately instead of T(g) = -O-(n) 
- 