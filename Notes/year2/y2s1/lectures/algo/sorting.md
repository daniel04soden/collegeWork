## Insertion Sort Algorithm:

- Formal Problem state
  - Input = An array of comparable objects
  - Output = An orderd permutation of the input array

```{=org}
#+begin_example c
```
For j = 2..n

k=A\[j\] i=j-1 // Looking at previous card

while i\>0 && A\[i\]\>k: A\[i+1\] = A\[i\] i = i-1 A\[i+1\] = k

```{=org}
#+end_example
```
# Basic sorting algorithms

## Comparison based in place sorting

- Consider a list of objects to be sorted in place A1...An without using
  additional memory.

- this means we cannot create any new lists to copy elements there in
  the process; we must rely on the space allocated for list already and
  can only swap the positions.

- We also do not use algos that relay on recursion as it would create a
  stack of local variables and therefore potetntially allocate more than
  a constant amount of memory.

- We asssume that the values of the keys are not acccesible directly in
  this scenario and that the only mechnaism to determine their order is
  by means of mutual pairwise comparison

- To determine if two objects are in the correct order, some relatiomn
  ai \< aj is used which must be

  - reflexive (v\<=v)
  - anti symmetric (v\<= w then v=w or w\>v)
  - Transitive ie if v\<=w and w\<=x then v\<=x

- Note that the relation can be a comparison between any type of objects
  and is not restricted to numbers only

- Assuming numbers here can lead to more time efficient algorithms

  - To capture the requirements all sorting algos in this lecture will
    operate on a simple abstract data type

- It should be able to find its length (number of objects to be
  sorted),comapre itself and other elements (Compare two objects an and
  aj and return a boolean) and swapping two elements (exchange the two
  objects ai and aj in the list)

- It can be implemented easily using array type static Data Structures,
  but other implenetation using dynamic linked lists wiht slight tweaks
  to the loops inside the algorithms are possible as well

## Bubble sort

- \"Queueing in alphabetical order\"
- Oh yeah I\'m A okay ill swap with you oh im Ab not Ac so im earlier
  etc
