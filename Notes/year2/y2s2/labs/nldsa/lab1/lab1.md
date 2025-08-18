# Lab 1 NLDSA

## Question 1

```py
# Selection Sort

for i = 1..(n-1) # O(n)
            m = i # O(n) x O(1)
            for j = (i+1)..n # O(n^2)
                if A(m) < A(j) # O(n^2) x O(1)
                m = j # O(n^2) x O(1)
        r = A(m) # O(n) x O(1)
        A(m) = A(i) # O(n) x O(1)
        A[i] = x # O(n) x O(1)
```

- This algorithm loops through all the elements in an array, finds the lowest value
  and moves it to the front of the array and does this iteratively until the array
  is completely sorted
- As we can see above the algorithm selection sort involves both a standard linear loop
  where O(n) is the worst and best case performance and then within this loop
  we are presented with a second loop resulting in O(n^2)
- From my comments below the calculations would be as follows:

$$
O(n) + O(n)*O(1) + O(n^2) + O(n^2)*O(1) + O(n^2)*O(1) + O(n)*O(1) + O(n)*O(1) + O(n)*O(1) = O(n^2)

$$

- Therefore the best and worst case runtime for selection sort is O(n^2) due to the
  hierarchy of of asymptotic functions.

## Question 2

- Using big tita notation we must order the following functions:

1. 5n - 3 -> 𝚯(n) + 𝚯(1) = **𝚯(n)** -x  2
2. 42 -> 𝚯(1) = **𝚯(1)** - x 1
3. n^2 - 2n + 1 -> 𝚯(n) + 𝚯(1) + 𝚯(n^2) = **𝚯(n^2)** - 4
4. 3n^3 - 2n^2 + 7 -> 3𝚯(n^3) +  =**𝚯(n^3)** 8
5. 4n + 3logn^2 -> = **𝚯(logn^2)** 7
6. 7nlogn + 14 - 𝚯(nlogn) + 𝚯(1) = **𝚯(nlogn)**  3
7. 2n^2 + nlogn - 𝚯(n^2) + **𝚯(n^2)** 5
8. 4n^2 +2n^2logn-3 - **𝚯(n^2logn)** 6

- Currently this list is in no particular order and using Tita notation we must reorder this list in the order of growth
- For the most part tita notation is very similar to big O notation except for the fact that it looks at functions in a tight bound sense in that c1g(n) <= f(n) <= c2 g(n).
- Generally though we focus on the lowest order value and devise the -O- from that
- For example, 4n^2 + 2n + 3 will equal -O-(n^2)
- As seen above I have assigned the asymptotic analysis and will now order them where 1 grows the slowest and 8 grows the fastest.

1. 42
2. 5n-3
3. 7nlogn + 14
4. n^2 - 2n + 1
5. 4n + 3logn^2
6. 4n^2 +2n^2logn-3
7. 4n + 3logn^2
8. 3n^3 - 2n^2 + 7

## Question 3


| A           | B        | Noation |
| ------------- | ---------- | --------- |
| 7           | 2n       |         |
| n^2         | 3n       | Item1   |
| nlogn       | n^2      | Item1   |
| n           | nlogn    | Item1   |
| n^2+100n    | 100n^2   | Item1   |
| logn        | 10logn^2 | Item1   |
| n^3         | n^2logn  | Item1   |
| sumni * i=1 | n^2      | Item1   |
