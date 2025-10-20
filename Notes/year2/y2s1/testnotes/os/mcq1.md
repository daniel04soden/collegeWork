# Operating systems mcq notes

- Operating system is the layer of software between the applications and
  the computer hardware

  - It manages the execution of applications

  - It manages the resources given to applications

    - Ensures efficient use of a processor
    - Allows switching between multiple processes (concurrency)

## Processes:

- A unit of activity characterised by the execution of a sequence of
  instructions

  - It consists of a PCB, set of instructions and associated data

## Control structures:

### File table

- Contains all info about existence of files
- Current Location in disk
- Current status

### Process table

- File access
- Current IO status
- Location in memory
- Uses Cross references to the other tables

### IO table

- What IO is being used and what is available
- Status of current io operation on each device
  - Source/destination used in main memory

### Memory table

- Allocation of main and virtual memroy to processes
  - Info for managing virtual memory

  <!-- -->

  - Protection info about segments of main memory ie whoch processes may
    access shared memory areas

# Process Control Block

- Process Control block are the attributes of a process stored with the
  process

  - The pcb is unique for each process

  - Three categories of info for the PCB

    1.  Process ID
    2.  CPU State info
    3.  Prcess control info

### PID

- Process identification
  - Unique numeric value for the process, its Parent process and the
    associated

### CPU State info

- Execution context of the process ie copies of the current values of
  the cpu registers for this process need to be stores when the process
  isnt using the CPU

  - Such as: Programmer visible registers
  - Control and status registers (PC,IR,MAR,MBR, Program Status Word.)

### Process Control info

- processs state and scheduling
- Relationships with other processes
- Inter process communication
- Process Privileges
- Memory management
- Resource ownership and utilization

# Process state transitions

- New - process has just been created
- Ready - New process is loaded into main memory and placed in the queue
  of processes which are waiting for CPU allocation
- Running - CPU allocated to the process
- Blocked - Process waiting for IO task
- Blocked suspend - Main memory full, placed in disk
- Ready suspend - Main memory full, placed in disk and swapped out from
  main memory already into disk
  - Terminated - Entire set of instructions executed and the process
    completed - PCB deleted

# Threads

- A unit of dispatch ie the entity that is given control of the CPU and
  has a state (ready,running,blocked)

  - A process is a unit of resource ownership ie its the entity that has
    control of a piece of memory, ie some peripherals and files

## User level and kernel level threads.

- user level threading works with the applicaiton being used by the user

- Kernel level threading is all thread managment done by the kernel

## Multithreading/Concurrency

- Concurrency is the Method which allows multiple concurrent paths of
  execution to run from within the same process with shared memory and
  shared access to their process\' resources

  - This not only saves overhead by having a separate process for each
    thread, processes are faster to switch craete and terminate.
  - Inter thread communication is possible too which leads to more
    efficiency.

# Process Switching

- For many different reasons a process is removed form the CPU to give
  another process control

  We need to consider what events trigger such a switch and how are
  these switches done?

  - the program counter keeps the address of the next instruction to be
    fetched

  - Any time an interrupt occurs the currently running process must make
    way for the interrupt handler to use the CPU
