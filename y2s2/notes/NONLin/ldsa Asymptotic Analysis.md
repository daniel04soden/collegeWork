
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

for j = 2..n 
	k = A[j]
	i = j-1
	while i>0 && A[i]>k
		A[i+1] = A[i]
		i--
	A[i+1] = k


```

Our runtime is as follows (Can't type that out)
![[Screenshot_20250118_155800.png]]


- Our number of comparisons needed (t) depends on the content of the input array which can't be predefined.

- If we want to specify the runtime depending on only one free variable n we must consider different scenarios.
- If the list is sorted , t is 1 and the best case runtime is achieved.
- As we haven't defined the runtime c(i) of the basic instructions beyond being constant.
