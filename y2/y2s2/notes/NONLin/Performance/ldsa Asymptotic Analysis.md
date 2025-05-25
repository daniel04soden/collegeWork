
# Assessing Algorithm Performance

## How do we asses the performance of an algorithm?

- The most straightforward way is to look at the time it takes to produce a result 
- However other issues may include, compiling,implementation,space/memory used, hardware utilised by end users etc.
- This will mean we need to account for a variety of factors and one algorithm that is fast may take up a large amount of memory.

- The key to studying the fundamental properties of an algorithm is abstraction
- This magical random access machine abstracts from the actual physical aspects of computation and focuses on instructions 
- The number of times each instruction is executed depends on the algorithm and the input data.

- For example in insertion sort, the algorithm has the following characteristics depending on the length of input and the number of comparisons executed.

```ruby

for j = 2..n # O(n) - n
	k = A[j] # O(1) - n -1
	i = j-1 # O(1) - n -1
	while i>0 && A[i]>k # O(n) - sum of n i=2 ti
		A[i+1] = A[i] # O(1) - same sum -1 
		i-- # O(1) - same sum -1
	A[i+1] = k # O(1) - n -1


```
O(n^2)
Our runtime is as follows (Can't type that out)
![[Screenshot_20250118_155800.png]]


- Our number of comparisons needed (t) depends on the content of the input array which can't be predefined.

- If we want to specify the runtime depending on only one free variable n we must consider different scenarios.
- If the list is sorted , t is 1 and the best case runtime is achieved.
- As we haven't defined the runtime c(i) of the basic instructions beyond being constant.
 - Worst case is array inverted, if so the function becomes quadratic. ie
$$
 T(n) = an^2 + bn +c
$$
- If the list is in reverse order then ti = i. 

## How do we actually asses performance.
- Usually we are more interested in a worst case analysis as it provides an upper bound that is not specific to any particular input and can be considered a performance guarantee
- The performance is usually character int terms of a single free variable n corresponding to the input size. What n describes exactly must be clear from the context 
- While most of the time are analysing the running time of the algorithms the same techniques ca be used for other resources such as an algorithms memory footprint
- The actual runtime in seconds depends on many factors on a real machine, therefore we are using the more abstract concept of order of growth instead which only considers very large instances n-> infinity so that any fixed overhead costs becomes insignificant for the analysis and ignore the absolute units of time in favour of focusing the general show how T(n) increasing while increasing n.

### Order of growth

- We have seen that insertion sort has a worst case runtime on the random access machine of 

$$
 T(n) = an^2 + bn +c
$$

- For the large values of n  at some point n the constant offset c will become insignificant to the linear term bn
- At some point n2 the linear term bn will also become insignificant to the quadratic term an^2
- Therefore performance is dominated by the quadratic term an^2 for large enough input sized.
- The constant factor a describes how fast the ram can execute the associated basic instructions
- If we want to compare computation efficiency this absolute unit of time is meaningless compared to even higher order terms or larger inputs and be abstracted to a=1 
- Then the order of growth of insertion sort is n^2 only ignoring all lower order terms and any constant factors.

- This abstraction focuses on the general scalability of the algorithm however on smaller inputs this may not have as much of an impact.

# Runtime notation
## O- Notation

- To formalise this we define the set of ucntoins f(n) with an symptotic upper bound g(n) as:
- f(n) <= cg(n)
  - this is the set of functions f(n) an element of O(g(n)) that for sufficiently large n are always below g(n) multiplied by a suitable positive factor c (constant)
$$
 T(n) = an^2 +bn +c = O(n^2)
$$
- We can write the worst case asymptotic runtime of the insertion sort algorithm is better than quadratic.
## Omega notiation

- Lower bound notation
- Same formula except we are looking for where c(g(n)) <= f(n)
- This is now the set of functions f(n) that for sufficiently large n are always above g(n) multiplied by a suitable positive factor c
- For example the best case asymptotic runtime for the insertion sort algorithm is at least linear
- omega(n^2)

## Tita notation
- This is looking for a rannge rather than for a certain bound ( somewhere in the middle.
- ie c1(g(n)) <= f(n) <= c2g(n)
- For insertion sort the worst tita runtime is shown as -O-(n^2)

## Note for notation

- We must be precise in that the worst case for insertion sort is O(n^2) for which it would be worse, however also O(n^3) or O(n^4) because these are just less tight upper bounds
- Also there is at least one instance for each input length for which the runtime is Omega(n^2) again it also Omega(n) or omega(logn) beacause these are just lower bounds
- Since the lower and upper bound are the same in this case the worst case runtime is most precisely characterised as -0-(n^2)
- it is not -0-(n^3) or -0-(n) because there are no instance for which the worst case runtime would be omega(n^3) or O(n)

- The best case runtime is O(n) as there is at least one instance for which this runtime is attained
- It is also Omega(n) because the algorithm never uses less steps


What characterisation is most appropriate depends on what's important:
- The worst case upper bound O(n^2) is a maximum runtime guarantee for all inputs
- The worst case tight bound tita n squared is more precise guaranteeing the worst case can happen
- The best case lower bound omega n is the minimum required runtime regardless of the input


- Small notation focuses on all whereas most upper notation is for some constant
- The bound must hold for any constant factor
- It becomes insignificant compared to g(n) as f(n) becomes larger.
- "if f is o of g it grows as fsat as g does..."
- Back to Maths for computer science (Transitivity,Reflexivity,Symmetry)

## Comparing functions asymptotically
- When characterising runtime of algorithms we usually find Monomials, Logarithms and exponentials
- 123 = -O-(1), 3(n) = -O-(n), 4n^2 = -O-(n^2)
- 7logen^4 = -O-(logn)  - Doesn't matter about logen or log10n for runtime
- After a while ignore constants
- 5(2^n) = -O-(2^n)

- **Monomials terms are always asymptotically larger than logarithmic terms**
- **Exponential are larger than almost everything and usually indicate infeasibility**
- **O(1) > O(logn) > O(nlogn) > O(n^2) > O(n^2) > O(n^2logn) > O(n^3) > O(2^n)**

- For sums of functions we use the above comparison chart  and ignore lower order term
- We identify the largest term in a sum and ignore all constant factors. 
- Ignore addition and minuses

- To characterise the worst case performance of an iterative algorithm , we analyse the loop nesting to identify the most dominant line
- We ignore constants after summing them
- When looking at an individual line basis, we ignore O(1) as it is a constant in this scenario.
