# Chapters;
- 16.1-16.4
# Assessing performance
- Data structures implement abstract data types defined by creators mutators and observers
- Each of these operations are implemented by algorithms that can be characterised on their own.
- Previously we used pop in stacks but now we will look at a multi pop operation. Imagine taking loads of plates/books off the top.

- Often we are more interested in how  data structure performs over its life cycle rather than individual operations in isolation, these will be used in scale not in a single program
- The worst case upper bound of multi pop is linear and will discuss techniques on how to derive more accurate result

- we need ot consider sequences of oeprations and to see if althrough some individual operations amy be expensive on average it is still small
- Worst case we msut evalauate all possible sequences of mutators
- Depending on the evolving state of the dat astructure.


- Averaging has no probabilisitc analysis over all inputs
- Instead we look at average cost of operations for worst case
## Aggregate method
- Directly applies this idea and drivers the amortised cost per operation as T(n)/n

-