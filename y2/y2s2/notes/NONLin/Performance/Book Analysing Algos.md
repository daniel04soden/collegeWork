# Defining how we analyse

- Predicting the resources the algorithm requires
- Eg memory, bandwidth, energy consumption.
- Time is most common measure for algorithms.
- By analysing several candidate algorithms for a problem, you can often identify the most efficient one. There might be more than just one viable candidate in terms of time but for say space another may be more efficient ie we have many factors to consider.
- We use the random access machine as a pseudo model for what we are running these algorithms on. IE we can't simply pull out two laptops and run the algorithms, there are too many factors outside of the instructions that affect performance.
- The RAM model assumes each instruction takes the same amount of time as any other instruction and that each data access takes the same as any other data access (Indexing vs standard retrieval)
- You may need to employ mathematical tools like combinatorics, probability theory.

# Asymptotic Notation
- Definition: the running time of an algorithm are defined in terms of functions whose domains are the set of natural numbers.
- These are convenient for describing worst case run time which is mainly defined only on integer input sizes. 
- We must always see some growth after some input.
## Comparing functions asymptotically
- When characterising the asymptotic runtime of algorithms, we usually encounter a combination of 
	1. Monomials 
	2. Logarithms
	3. Exponential ie
$$
	 123 = -O-(1), 3n = -O-(n), 4n^2 = -O-(n^2)
$$

$$
	7log e n^4 = -O-(log n)
$$

$$
5*2^n = -O-(2^n)
$$
- Exponential > Monomials > Logarithmic
## -O- (Theta) Notation

- c1.g(n) <= f(n) <= c2.g(n)
- Tight bound (upper and lower bound)
- n^2 = -0-(n^2)


## Omega notation

- Definition f(n) >= c.g(n) 
- Lower bound of f(n)
- n^2 = Omega(n^2)

## w notation
- small omega notation
- f(n) > c.g(n)
- Strict lower bound
## Small o notation

- Definition f(n) < c.g(n) - Notice it cannot be equal
- Stricter upper bound
- n^2 = o(n^3)
## Big O-Notation.
- Definition f(n) <= c.g(n) 
- Meaning = Upper bound
- n^2 = O(n^2)
- In chapter 1 we found the worst case running time of insertion sort is T(n) = On^2
- What does this notation actually mean?
- SImplified analysis of an algorithms efficiency
- Complexity in terms of input size N 
- Not dependent on Random Access machine
- Works in time and space
- Measurement - Worst and best case

### Rules of big o

1. Ignores constants like O(n) if it runs 5n. 
2. Terms dominate others.
	1. O(1)
	2. O(logn)
	3. O(n)
	4. O(nlogn)
	5. O(n^2)
	6. O( 2^n )
	7. O(n!)
		- Ignore low order items (IE algorithms lower down)


## Different time complexities

### Constant time
``` rust
let x = 5 + (15*20); // O(1)
let y = 15 - 2; // O(1)
println!(x+y); // O(1)
```

- Although we have three instances of big O(1), our total time is calculated as follows
- Total time  = O(1) + O(1) + O(1) = 3(O(1))
- However given we ignore constants, it is still O(1)

### Linear time

``` python

def idk(n):
	for i in range(0,n): 
		print i; // O(1);
```

- The instruction in our loop is O(1) however we have required to loop from 0 to N so it becomes N*(O(1)) which equals O(N)

``` python
y = 5 + (15*20) # O(1)
for x in range (0,n): # O(n)
	print x
```

- Given we drop low order runtimes the sum of O(1) from y and O(N) from our loop the answer is still O(N)
- Say we had 
$$
O(n) + o(n^2) + O(logn), 
$$
- the answer would still be 
$$
O(logn)
$$
- As we drop low order runtimes

### Quadratic time

``` python
for i in range (0,n);
	for j in range(0,n);
		print(x*y);
```

- Here n is being looped n times resulting in O( n^2 )

## Exercises
1. 
``` python
x = 5 + (15*20) # O(1)
for i in range(0,n): # O(n)
	print i 
for j in range(0,n): #O(n^2)
	for y in range(0,n):
		print x*y 
```
- O(n^2)

2. 
``` c
if x > 0:
	// O(1)
else if x < 0:
	O(logn)
else:
	O(n^2)
```

- O(n^2)

- Usually look at worst case scenario for big O

