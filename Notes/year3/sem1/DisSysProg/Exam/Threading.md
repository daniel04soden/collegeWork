# What are threads?

- **Threads** are a unit of execution within a process and the smallest unit of execution that can be scheduled by an operating system.
## Threads vs binaries vs processes

- **Binaries** are dormant programs on a storage medium. They are ready to execute but are not yet loaded/running
- **Process** are an OS abstraction representing a binary loaded and running 


### Traditional View of a process
1. Context
2. Code
3. Data
4. Stack

### Alternative view of a process:
1. Thread
2. Code
3. Data
4. Kernel Context


## Processes with multiple threads

- Multiple threads can be associated with a process as each thread has its own logical control flow and share the same code, data and kernel context as well as a thread id.
![[Pasted image 20251215163923.png]]


# Advantages of threading:
## In short
1. Efficiency - Programs can better utilise system resources such as the CPU becasue another line of execution can grab the CPU when one line of execution is blocked
2. Problem solving - How would you write a program to show animations, play music, display documents and download files from the network at the same time (like a browser) without mulit-threading


## Detailed advantages
1.  **Programming Abstraction** threading can break down programs into units of execution
2. **Parallelism**  multiple threads can run on multiple processors at the same time
3. **Blocking IO** - With multithreading some threads may be blocked waiting for IO while others can continue
4. **Context Switching** Cheaper overhead in switching from thread to thread than process to process

# Parallel vs Concurrent

## Parallel
- Two threads are running in parallel when they are both executing at the same time using different CPUs (moreso cores)

## Concurrent

- Two concurrent threads are both in progress or trying to get some CPU time for execution at the same time but not necessarily being executed simultaneously on different CPUs
## Concurrency

- Two threads run concurrently if their logical flows overlap in time otherwise they are sequential

# Lifecycle of a thread

1. New - After it is created
2. Ready - After it is started
3. Running - When it is selected for execution
4. Waiting - While running, when for some reason it chooses to sleep,wait or perform an IO operation, once this wait is over the thread returns to the ready state
5. Dead - After completion

# Threads in Code (Java)

```java
public class TestingThreading extends Thread {
  public static void main(String[] args) {
    TestingThreading test = new TestingThreading();
    test.start();
    System.out.println("Different code brah");
  }

  public void run() {
    System.out.println("This code is being run on a thread");
  }
}
```

- The thread class in java allows one to create and manage threads, each thread is a separate instance of this class, in the run method above the program can create other objects or initiate other threads
- When the start method is called, one of its actions is to call the run method. It is possible to create and start several instances of threadx that execute concurrently. 
- Another way of creating a thread is to implement the runnable interface.

```java
public class TestThreadingImpl implements Runnable {
  public static void main(String[] args) {
    TestThreadingImpl ry = new TestThreadingImpl();
    Thread ty = new Thread(ry);
    ty.start();
    System.out.println("Different code brah");
  }

  public void run() {
    System.out.println("thread output");
  }
}

```

## Another example:

```java
public class Join implements Runnable {
  String attr;

  public Join(String attr_) {
    this.attr = attr_;
  }

  public void run() {
    System.out.println(this.attr);
  }

  public static void main(String[] args) {
    Thread t1 = new Thread(new Join("e1"));
    t1.start();
    Thread t2 = new Thread(new Join("e2"));
    t2.start();

    while (true) {
      try {
        t1.join();
        t2.join();
        break;
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

  }
}
```

- In the example above we see there are the two threads we create while also there is the main thread, the main thread creates and starts t1 and t2, the two threads start running in parallel
- It is important to understand that the t1 and t2 threads have been running in parallel but the main thread that started them needs to wait for them to finish before it can continue
# Context switching

- Context switching is the process by which the OS switches the CPUs attention from one task to another, ensuring multitasking. 
- A thread context switch is where the OS switches the CPUs attention from one thread to another allowing for multithreading in a sense. Threads use concurrency which may appear to be running at the same time, when in actuality the illusion of parallelism is created through fast context switching.
- The computer stores the context of the thread/task it is switching to and from so that it can be returned to later.
# Deadlock
- Deadlocks occur when multiple threads block each other while waiting for locks held by one another.