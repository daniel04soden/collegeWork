## Algorithm

- A finite set of rules that gives a sequence of operations for solving a specific type of problem

### Features of Algorithms

- Finiteness -  Must terminate after a finite number of steps
- Definiteness - Each step must be precisely defined for each case
- Input - It has zero or more inputs and are taken from a specified set of objects
- Output - Algos have one or more ouputs - specified relation to their inputs
- Effectiveness - Must be sufficiently basic and occur in a finite length of time.

## Random access Machines 

- A random access Machine is an abstract machine model that operates on number of any arbitrary size, has infinitely many addressable memory cells and executes instructions in order and one at a time. 
- Each instruction and memory access is assumed to take constant time 
- In most cases a random access machine are good approximations of real computers for the purpose of analyzing algorithm correctness and asymptotic performance.


# Arrays 

- Arrays are the most fundamental linear data structure which area also called lists or vectors
- They are store objects or identical size consecutively in memory
- On ram they can be accessed directly via the array index in constant time 
- ie x = A[3] or A[3] = x
- The array index can be a variable itself ie enabling pointer operations
- Since array indexes are integers, the random access machine model also allows for operations such as pointer arithmetic.
- Possibility of 0 and 1 based element arrays
- The chosen convention chosen depends on the context like in java we use zero based as it is a fully fledged language whereas lua is an embedded scripting language which uses one based arrays
- The arrays capacity/length must be allocated ie static memory allocation
- 
## Primitive data types

- The ram model assumes integer numbers are stored in each cell however these numbers can be constructed as primitive data types such as Integers booleans characters etc
- Records are a fixed number of multiple cells aggregated into a single value.
- This is sometimes known as a struct or tuple
- Using pointer arithmetic records can conceptually be treated as single array elements
- 

