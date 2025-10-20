# Introduction to threads

- Each process runs in its own address space and be independently
  scheduled for execution.
- A thread is a sequence of execution within a process - known as a
  lightweight process - Single flow of control.
- The creation and management of multiple units of execution within a
  single process
- A thread does not have its own address space but uses the memory and
  other resources of the process in which it executes

## Comparing threads to...

- Binaries - dormant programs on a storage medium - ready to execute but
  not yet loaded/running

- Process - an os abstraction representing a binary loaded and running

- Threads are unit of execution within a process; The smallest unit of
  execution that can be scheduled by an OS process scheduler.

- Process - Process context + code + data and the stack

## Processes with multiple threads

- Multiple threads can be associated with a process.
  - Each thread has its own logical control flow (sequence of PC values)
  - Each thread shares the same code, data and kernel context
  - Each thread has its own thread id

## Advantages of threading

- The ****resources**** required for a thread are substantially less
  than those required for a process

- If using a vm like the jvm, these ****threads are managed and
  scheduled automatically**** for execution.

- The ****time**** required to perform a context switch is far less than
  what is required for performing such a change between prcoesses

- ****Programming abstraction**** - can break down programs into units
  of execution

- Parallelism - multiple threads can run on multiple processors at the
  same time.

- Improved responsiveness - Evon on a uniprocessor system, the amount of
  time a user waits for a system to return for more input is reduced

- Blocking IO - with multithreading, some threads may be blocked waiting
  for io while others can continue.

- Context switching - Cheaper overhead in switching from thread to
  thread than process to process

- Memory - More efficient at sharing memory yet have multiple units of
  execution

# Parallelism vs Concurrency

## Parallel

- Two threads are said to run in parallel when they are both executing
  at the same time using different CPUs.

## Concurrent

- Two concurrent threads are both in progress or trying to get some cpu
  time for execution at the same time but not necessarily beign executed
  simultaneously on different CPUs.

<!-- -->

- Two threads run conncurrently if their logical flows overlap in time

- Otherwise they are sequential.

# Lifecycle of threads

- A threads lifecycle moves through several states from its creation to
  its ultimate death:

1.  New - After it is created
2.  Ready - After it is started
3.  Running - When it is selected for execution
4.  Dead - After completion
5.  Waiting - When for some reason, it chooses to sleep wait or perform
    an io operation. Once this wait is over the thread returns to the
    ready state.

# Java\'s Thread class

- The thread clss in the java.lang package allows you to create and
  manage threads.

- Each thread is a separate instance of this class

``` java
// Extends syntax
public class ThreadX extends Thread
{

    public void run(){// Thread logic
    }
}


```

- In the run method above, the program can create other objects or
  initiate other threads.

``` java
Thread tx = new Threadx();
tx.start();
```

- The application code below can start an instance of a thread with the
  following code

``` java
public class RunnableY implements Runnable
{

    public void run(){// Thread logic
    }
}

RunnableY ry = new 
```
