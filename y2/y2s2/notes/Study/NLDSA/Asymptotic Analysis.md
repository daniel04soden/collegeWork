# How to assess the performance of an algorithm?

- Most interested in the worst case analysis. 
- Provides upper bound not specific to any particular input and can be considered a performance guarantee
- The performance T(n) isually chracteristed in terms of a singel free variable n corresponding to the input size
- What n describes exactly must be clear from the context (for example T(V), time processing Vertices)
- While most of the time we are analysing the running time of the algorithms, the same techniques can be used for other important resources like memory footprint
- IE the concept of the order of growth which considers very large instances where n can go up to infinity so that any fixed overhead costs become insignificant for the analysis. Ignores absolute units of time in favour of focusing on the general shape of how T(n) increases with increasing n
## Order of growth table:

![[Pasted image 20250514134801.png]]

## O-Notation - Worst case
- The set of functions with an asymptotic upper bound setting on how slow your code can be:
	- O(g(n)) = 0 <= f(n) <= cg(n)
	- T(n) = an^2 +bn+c = O(n^2) - Worst case asymptotic run time of the insertion sort algorithm 
### o Notation - loose upper bound of f(n)
- Little o is the loose upper-bound of a particular function, rough estimate of the maximum order of growth whereas Big o may be actual order of growth
- 𝑜 𝑔 𝑛 = 𝑓(𝑛) ∀𝑐 ∈ ℝ+: ∃𝑛0 ∈ ℕ: 𝑛 ≥ 𝑛0 ⇒ 0 ≤ 𝑓 𝑛 < 𝑐𝑔 𝑛
- The bound must hold for **any** constant factor (not just some)

## Omega(Ω)-Notation - best case performance
- The set of functions with an asymptotic lower bound , the typical case performance for an algorithm:
	- Ω(g(n)) = 0 <= c(g(n)) <= f(n)
	- Best case for insertion sort is at least linear 
		- T(n) = a n + b = Ω(n)
### Little omega (w) - loose lower bound of f(n)
- Little w notation is used to describe the relationship between two functions when one grows strictly faster than the other ie if f(n)= w(g(n))  thne g(n) grows slower than f(n) as n approaches infinity.
## Θ-Notation - average case performance
- The set of functions with an asymptotic tight bound ie in the middle
- Θ(g(n)) = c1g(n)<= f(n)<= c2(g(n))
- It is easy to see that Θ 𝑔(𝑛) = 𝑂 𝑔 𝑛 ∩ Ω 𝑔(𝑛) - In other words the funciton can only be denoted as teta g(n) if an only if f(n)= O(g(n)) and f(n) = Ω (g(n))

- For insertion sort the worst case asymptotic runtime can be shown as omega(n^2)  so that the asymptotic tight bound is:
	- T(n)=Θ(n^2)

# Summary:

• The worst-case upper bound 𝑂(𝑛 2 ) is a maximum runtime guarantee for all inputs
• The worst-case tight bound Θ 𝑛 2 is more precise in also guaranteeing that the worst-case **can** happen - **Average**
• The best-case lower bound Ω(𝑛) is the minimum required runtime regardless of the input
## Hierarchy of increasingly asymptotically larger functions:

![[Pasted image 20250514140946.png]]

### Examples
#### Key notes
- Refer to hierarchy when deciding on notations - the largest growth order takes precedence
- Ignore constants such as 4n or 2nlogn - In real case scenarios roughly important but for measuring potential performance not really
### Example 1
1. 4n^2  + 2n + 3: n^2 +n : n^2 - Θ(n^2) - Average case performance is tita n squared
2. 2n+4nlogn - n + nlogn = Average case performance is titanlogn
3. 3n^2 + 4nlogn = n^2 + nlogn = titan^2
4. 7logn + 3n +2 = tita(n)
5. 2n^3 - 6n - 5 = tita(n^3)

### Example 2
```c
// Insertion sort
for j = 2..n{ // O(n)
	k = A[j] // O(n)*O(1)
	i = j-1 //  O(n)*O(1)
	while i> 0 && A[i] > k{ // O(n) * O(n) = O(n^2)
		A[i+1]	= A[i] // O(n^2)*O(1)
		i	= i-1 // O(n^2)*O(1)
	}
	A[i+1] = k O(n)*O(1)
}
```

- To derive our best/worst case here, we take all of the derived notations, sum them up so here it would be 4O(n) + 3O(n^2) = O(n) + O(n^2) as we ignore constants and then since O(n^2) has  a higher order of growth compared to O(n), we take O(n^2) as our final order of growth answer
