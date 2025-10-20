# Priority queues {#priority-queues-1}

- A priority queue is an ADT to storea aset S of elements supporting the
  following elements:
  - S=CREATE(A)
    - Creates a new priority queue containing all elements of the array
      A.
  - Insert(S,x)
    - Adding a enw element in the set ie S= S U {x}
  - x = EXTRACT-MINIMUM(S)
    - Returns element from S with the smallest key ie x E s so that all
      other elements are larger and remove it from the set ie S-={x}.
  - Decrease-Key(x,y):
    - For an element with key\[x\]\>v change the value of the key to the
      new value key\[x\] = v, potentially moving it up the queue to be
      extracted earlier by the above operation

## Priority queues in operating systems

- Process scheduling is used in opearting systems, these are implemented
  often with priority based queues

## Priority queues in applicaitons

- Calendar reminders (MLF)
- TODO list
- Issue tracking in agile development tools, Jira

# Binary heaps

- Layered Logical structure
  - Lets consider a linear data structure with random access such as an
    array.
  - Then the addresses to access the data can be logically structured
    into layers so that each alwyas has twice the size of the previous
    layer.
    - 2^n^-1 elements in the array
  - The height of a datapoint in this structure is the distance to the
    lowest layer
  - The relationship between the height h f the topmost first element
    and teh numer of elements n is n+1= 2^h^+1 or h = log2(n+1) - 1

# Complete binary trees

- This layered logical structure can be visualised as a compelete binary
  tree.

## Maintaining the heap property

- When a vlue of a single node violates the heap but both of its
  descendants are also heaps

  - Exchanging this element with its smallest descendant fixes the heap
    property for this node
  - However, the problem is potentially pushed further down the line
  - If this is the case we must recursively exchange the value with its
    smallest descendant again until we reach the lowest layer of the
    heap.

# Creating a binary heap

- To run the heapify procedure on any node of all its descnedants must
  already be heaps

- The nodes at the bottom of the heap, trivially satisfy the heap
  property already.

- Going backward from there we can use the heapify method to establish
  the heap property node by node

- After the top node has been heapified the entire array satisfies the
  heap property

# Heapsort

- Heaps can be used directly for sorting

## Heapsort code

- n = heapsize\[A\] = length\[A\]
- CREATE-HEAP(A) For i = n down to 2 exchangeA\[1\] \<--\> A\[i\]
  heapsize\[A\] = heapsize\[A\] - 1 HEAPIFY(A,1)
