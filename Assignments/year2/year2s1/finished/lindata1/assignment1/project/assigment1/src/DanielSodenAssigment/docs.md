# ArrayStack Docs

- An array stack or a stack implementation of an array uses an array to
  represent a data structure in which only the top-most element can be
  accessed.

  - New items are always put on top of the stack and therefore when an
    item is removed it is also only taken from the top

## Initializing the ArrayStack

``` java
public class ArrayStack implements Stack<Object> {

    private Object[] S
    private int top;
    private int capacity;
    private int n;
    public ArrayStack(int capacity_){
     this.capacity = capacity_;
     this.S = new Object[capacity];
     this.top = -1; // Pointer to -1 (the current position of the value)
     this.n = capacity - 1 // Length of the array 
    }

}

```

- As we can see above the code implements an array stack by first
  creating an array Object S with an integer pointing to the top of the
  stack

- We then create both a capacity and length to keep track of both the
  current amount of values (n) on the stack and the maximum capacity.

  - Top is set to -1 as these are modeled around 1 based index methods
    and must be translated accordingly
  - The length is set to the max capacity -1 to account for the fact we
    are working in 0 based arrays.

## Pushing a value on top of the stack

``` java
public void push(Object x){
    if(top>= n){
        System.out.println("Stack overflow helppppp");

    }else{
        top++
        Object[top] = x;
    }
}

```

- If the top value is already greater than or equal to the current
  length we have to throw a stack overflow as we have reached the
  capacity
  - Otherwise we must increment the top value pointer and assign the
    value at the index of top to x.

## Popping a value off the top of the stack

``` java

public Object pop(){
    if (top == -1){
        return null // If the array is empty where the top is at index -1
            // Return null object

    }else{
        top--
         return S[top+1] // Give back the value right under the previous top
    }

}
```

- When an element is now removed from the top of the stack, the top
  pointer is decrimented
  - We then return the value at the index right before the previous top
    ie S\[top+1\]

## Peeking at a value at the top of the Stack

- To peek at the value at the top we must return the value for S at the
  index Top
  - We use the top pointer to identify this value

``` java
public Object peek(){
    return S[top];
}
```

## Checking whether or not the Stack has no values in it

``` java
public boolean empty(){
    return top == -1;
}
```

# Array Implementation of a queue

## Initialization of the Array Queue

``` java
public class ArrayQueue implements Queue<Object>{

    // Fields
    private Object[] Q;
    private int tail;
    private int head;
    private int n

       public ArrayQueue(int capacity){
        this.Q = new Object[capacity];
        this.n = capacity -1;
        this.head = -1;
        this.tail = -1
    }

}


```

- Queues can also be implemented using an array, this is done by
  creating a tail and head pointer pointing to the end and front of the
  array respectively

## Enqueue a value onto the array queue

- This method will add a new value to the end of the array by
  incrementing the pointer to the tail value and assigning a new object
  at this pointer

  ``` java
  public void enqueue(Object x){
      if(empty()){
          head=0;
          tail;
          }
      Q[tail]=x;
          if (tail == n){
              tail = 0

              }else{
              tail++;

              }
      }
  ```

## Dequeue a value off the array queue

``` java
public Object dequeue(){
    Object x = Q[head]; // Initializing Value at the index head
    if (head == n) {
        head = -1;
    } else {
        head++;
    }
    return x;
}

```

- Here the element at the head ie top of the queue is removed and the
  pointer for the head is increased
  - If necessary the head pointer will wrap around again.

## Checking if the Array Queue is empty

``` java
public boolean checkEmpty(){
    return head == -1; || head == tail
}

```

## Looking at the next element in the array

``` java
public Object next(){
    Object x = Q[head];
    if empty(){
        return null;
            }else{

    return x;
    }
}
```

- This method returns the next value up in the queue which is the value
  at the pointer of the head
- It uses the empty method to check and if so it will return a null
  object

# Double Linked List

## Initializing the Double Linked List

- A linked list is a dynamic data structure in which allocates storage
  in RAM when needed

- The order is established not by the position of the value in memory
  but by a pointer in each element pointing to the previous and next
  value. It can be used to implement linear Abstract data types more
  efficiently

``` java

    public class DoubleLinkedList implements List<Object>{
        public ListNode(Object x) {
            key = x;
        }

        public Object key;
        public ListNode prev = null; // Pointer to previous data
        public ListNode next = null; // Pointer to next data

    private ListNode head; // Pointer to the value at the top of the linked list
    private ListNode tail; // Pointer to the value at the end of the linked list (At value length)
    private int length; // Current length of the doubly linked list (Not max capacity)
        public DoubleLinkedList(){
            this.tail = null;
            this.head = null;
            this.n = 0;

        }

}

```

- In this initialization we first make the constructor node for each
  value

  - Then from here we create the public fields for the node by assigning
    an Object as the datatype of the actual value and assigning list
    nodes as the previous and next values.

- We also create the constructor for the doublelinked list by creating
  the tail and head and assigning them values of null and putting the
  length as 0.

## Prepending a value to the double linked list

- Prepending or inserting a new value at the beginning of the list
  involves creating a List node pointer to the object x.
  - From here we check if the list is empty and if so the new pointer
    being passed in becomes the tail.
  - Otherwise the previous pointer before the head is assigned as the
    pointer being passed in.
  - The .next value for this new pointer is now the head and the head is
    then assigned to the newNode

``` java
public void prepend(Object x){
    ListNode newNode = new ListNode(x);
    if (empty()) {
          tail = newNode; // If the entire doublylinked list is empty then it can simply be assigned as the tail
      } else {
          head.prev = newNode; // Otherwise it becomes the previous pointer of the current head
      }

      newNode.next = head; // The new nodes next pointer is the current head
      head = newNode; // Now this node becomes the head
      length++; // the length is now increased by 1
}
```

## Appending a value to the double linked List

- Appending is the opposite of prepending in which we are simpply adding
  a value to the end of the double linked list
  - However this involves previous rather than next and the tail pointer
    - We must create the listnode pointer for the object x and check if
      its empty
      - If it is empty the head becomes this pointer (head or tail it
        doesn\'t matter tooooo much)

``` java
public void append(Object x) {
        // TASK 1.E
        ListNode newestNode = new ListNode(x); // Creating a new node of the value x
        if (empty()) { // If the linked list is already empty then just let it be the head
            head = newestNode;
        } else { // Otherwise the next node after the current tail is x
            newestNode.prev = tail; // Now the previous node of newestNode is tail
            newestNode.next = null;
            tail.next = newestNode;
        }
        tail = newestNode; // Finally the tail becomes the newNode and the length increases
        length++;
    }
```

## Deleting the first and last values:

- The primary logic for both deleting the first and last values in a
  doublelinked involves checking if it is empty and if so nothing is
  performed
  - Otherwise if the tail is the same as the head (ie theres only one
    value) we set both pointers as null.
- If it is greater than one value, the current value at the head
  (head.next.prev) becomes null and the head becomes head.next ie the
  second value in the list
  - The inverse is performed in delete last where we do the same checks
    but tail.prev.next becomes null and the tail.prev becomes the new
    tail

### First

``` java
public void deleteFirst() {
      // TASK 1.D
      if (empty()) { // If it is empty
      } else if (tail == head) { // If theres only one value in the list (head is the same as tail)
          head = null;
          tail = null;
      } else {
          head.next.prev = null; // The next and previous value of the first value is the first value
          head = head.next;
      }
  }

```

### Last

``` java
public void deleteLast() {
    // TASK 1.G
    if (empty()) { // If it is empty
    } else if (tail == head) { // If theres only one value in the list (head is the same as tail)
        head = null;
        tail = null;
    } else {
        tail.prev.next = null; // The next and previous value of the first value is the first value
        tail = tail.prev;
    }
}
```

## Returning the first and last values
