# Introduction

- Modern os manage multiple processes
- Each process run its own address space adn can be independently
  scheduled for execution
- A thread is a sequence of execution within a process known as a
  lightweight process
- It is a single flow of control
- The resources needed for a trhead are less than for what\'s needed in
  processes
- Several threads in one process
- JVM manages/schedules these for execution
- Time to perform context switches far less than what is required for
  performing such a. change between processes

## Advantages of threads

- Scaling
- Modularity
- Performance
- Resource sharing
- Responsiveness

## Concurrency vs parralel

- Parallel - Two process working on two distinct jobs

- Concurrent - One process working between different jobs

- When an application is executed, the jvm creates a thread whose task
  is to make the main method and it starts the main thread

- The thread executes the statements of the program until the thread
  returns or until the thread dies

- All threads in the program can see the same heap space \## Thread
  states

- Now - After created

- runnable - after started

- running - when jvm selects it for exeuciton

- dead - after completion

- waiting - when it chooses to sleep wait or wait for io - once over,
  returns to the runnable/ready state

# Thread class

- Part of java.lang

- Allows you to create and maange threads

- Each thread is a separate instance of this calss

- In the run method, the program can create other objects or initiate
  other threads.

- Start method starts the execution of the thread.

- Another way of creating a thread os to declare a class that implements
  the runnable interface. This declares only one method

# When do we use thread and runnable

- If we use it we may use too little or no synchronization -
  inconsistency,loss or corruption of data
- Too much sync - deadlock or system freeze
