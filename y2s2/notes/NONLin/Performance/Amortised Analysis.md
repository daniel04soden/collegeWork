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

## Accounting method

- While the aggregate method assigns equal average cost T(n)/n to all operations it is sometimes easier for the sake of argument to assign different amortised costs for each operation
- The keyidea is to overcharge some operations in order to pre pay the cost of other operations in the sequence.
- The difference betweeen the actual cost c and the assigned ammortised cost can then be associated as credit with the elements stored in the data structure
- As long as the total credit remains positive for any sequence, the amortised cost can be used as an upper bound for the real cost
- If we charge a return fare of 2 ffor every element we push onto the stack then we can make all pop operations free as their cost has laready been covered
- Each element in the stack carries 1 credit to pay for its removal
### Potential method

- While the accounting methods assigns credit to each element stored in the data structure, the pre paid credit could also be associated with the data structure as a whole
- The potential method formalises this idea by defining a potential funciton that maps the data structures current state onto a real number, representing the restored total credit at time after the ith operation of the sequence
- The amortised cost with respsect to the potential function is then defined as the actual cost plus the charge in potential cuased by that operaiton
- The total amortised cost is now the actual cost plus the difference in potential between the initial and the final state of the data structure.
- If we can define a potential function for the state of the ds so that (End potential larger than start potential )ON >= Oo then the total amortised cost is an upper bound for the actual cost.
- Since the length of the sequence is unknown we need to show that for every operation the potential falls below the initial potential
- It is easy to find a potential that assigns the end as 0p because then we only have to prove that every immediate potential is non negative
- Now the total amortised cost is always an upper bound for the real cost

- With such a function identified, all that remains is to calculate the amortised cost for each of the operations


#### Example

- A potenttial funciton fo rth estack could be the number of elements stored in the ds
	- We can now calculate the amortised cost for each operation in turn
		- Push increases the number of elements by one at an actual cost of ci = 1
		- Pop decreases the number of elements by one therefore at an actual cost of ci = 1
		- Multi pop decreases the number of elements by k\` = min(k,potentialfunc i-1) at an actual cost of ci = k\`


# Dynamic table

## Adding elements
- Static arrays are pre-allocated so the max numbers is known in advance
- A dynamic table overcomes this issue by re allocating a larger array in case more space is required and the number of elements stored eceeds the pre allocated capacity
- Each time an expansion occurs, all elements must be copied into the newly allocated array
- If we cdouble the size of the ds on each expansion the algorithh for inseritng new elements into a dynamic table looks as follows

```python
tableInser(T,x):
	if |T| = num[T]:
		allocate new table with double the zie of t
		for i = 1..num[T]:
		A[i] = A(T)[i]
		free(memory)
		A[t] = a`
	insert x into table a[t]
	num[t] =  num[t] + 1
	

```

- Why a good idea
- Amortised cost of insert new element is O(1)
- We need to find a potnetial functyion that always saves up enough credit to pay for the next expansion
- If an expansion coccurs becuase num[T] = size[T] then the poetntial should have built up num[T] credit since the last expansion ot pay for copying all elements into the new array
- the previous expansion occured when it contained size|T| elements so the number of inserts without expansion sinc ehtne is size[T] - sum[T]/2 = size[T]/2
- The potneital icnrease per operation should be potentiali - potnetiali-1 = num[T]/size[T] = 2

- The ponential functoin should also reset back to zero after each expansion so, nothing that th eotal num of insert operatoins is the number of elements num[T] currently in the table the follwing ponteital function can eb used
$$
potential[T] = 2num[T] - size[T]
$$

## Deleting elements

- We hav eonly considered adding elements
- Ifwe also delete elements from the tbale the laod factor can become very small
- We could revert the process and rudece the size of the array each time the laod factor falls below some threshold
- It is attemtping to use the same threashold fo rht eocntraction thatn for the expnasion
- However this does ont result in worst case optimal behaviour for sequences with inserts and deletes that can trigger consecutive expansions and contractions each time requiring us to copy the full array

- The insert procudre does not have enough time to build up the potnetial to pay for the enxt expansion nor does the delete procedure have enough time to pay for the contraction

- To avoid this behaviour a contraciton must only be triggerd if the load factor falls below a(T)<= 1/4 wehreas an expasnions is triggered as before when the load factor reaches a(T) >= 1
- The enw table is still allocated ti have the size of the slots though so that the load factor after contractionincreases to a(T) = 1/2

- The algorithm for deleting an element from a dynamic table then looks as follows

```python
Table-Delete(T):
	remove element form the table of size T
	num|T| = num |T| -1
# Continue pseduo code at home lol
```


- Inserting an element increases our size ie num|T|
- An expansion is triggered when the laod factor a[T] = num[T]/size[T] = 1
- In the case of size t is increased the laod factor is changed to  ahalf

- Deleting an element decreases the num[T]
- a contraction is triggered when the laod factor is the num[T]/size[T] = 1/4
- In this case size[T] is descreased to bring the load factor down to a[T] = 1/2
- A dynamic table therefore always keeps the laod factor betweeen 1/4 <=a[T] <= 1

- To show both insert and delete operations on dynamic tables can be performed in an amortised constant time we need a potential function that increases.