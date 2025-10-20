# CPU Scheduling

## Starvation

- If a process is starved of access to the CPU it cannot run

## Algorithm Comparison

### Benchmark data

- Wait time is the length of time spent waiting for the CPU
- Turnaround time - Total amount of time spent in the system for this
  burst of service
- the service burst and turnaround time doesn\'t give us the full
  picture as it doesnt apply wait time
- NTT is the normalised turnaround time whcih is turnaround time divded
  by the service time (wait+service)/service
- This gives a good indication of the relative penalty incurred by each
  process under the algorithm in question.
- NTT gives us a more accurate insight into algorithm performance.
- Low ntt = doing well vs high ntt doing badly
- Note that in reality we never know the service burst times for a
  scheduling algorithm.

### First come first serve

- A simple queue where processes get the CPU in the ordder they arrive
  in the ready queue.
  - It is non pre-emptive meaning once a process gets served it runs to
    the end of its burst time.
  - FCFS performs well when all processes have similar burst times, this
    is a rare situation.

1.  Advantages

    - Fair in a simple way
    - Low overhead
    - No possible starvation

2.  Disadvantages

    - Mix of long and short process causes not needed long wait times
      for short processes
    - Favours cpu bound processes rather than IO bound processes

### Round robin - FCFS but with a time limit

- Introduced time slices/quantities/quantums

- This process is preemptive

- They get a set amount of time for each process and if this is reached,
  the next process begins

- Once the process gets service it runs either until it finishes its
  burst or a time limit is reached whichever is sooner.

  - It gives a level of predictability

1.  Advantages

    - Short process move quickly
    - Maintains basic fairness in queue
    - Predictable
    - Works well with a mix of different process lengths

2.  Disadvantages

    - Poor performance for IO bound processes.
    - Increases number of clock interrupts -\> more process switch so
      larger overhead.

3.  Issues with quantum size

    1.  very short

        - It imporves response time but short processes move through it
          more quickly
        - Many clock interrupts = more process switches

    2.  Longer

        - Less process switches

### Short process Next

- What if the job with the shortest expected processing burst time is
  selected next?
- Non-pre-emptive: When the scheduler has to choose a process, the
  waiting processes are ranked on burst time
- Once a job starts, it has to keep going and in that time, the next
  shortest process to arrive will start next

1.  Advantages

    - Better overall response times
    - Much better for shorter jobs

2.  Diadvantages

    - Need to estimate processing burst requirements

    - Predictability reduced

    - Risk of ****starvation****

      - Is it possible to keep these advantages and remove the risk of
        starvation

## Multi level feedack

- Maintains a few ready queues that operate under different rules

- Waiting processs can be assigned to the different queues as required
  and the queues ca be given different priorities

- It chooses the process from the head of the highest priority queue
  that contains waiting processes.

- Here we try to avoid guesswork and focus on shorter processes.

- Instead we focus on the amount of time a process has already spent
  running as a measure of its length

- IE instead of explicitly favouring short jobs, longer jobs are
  penalized.

  - When a process enters the queue it joins RQ0 and when it gets the
    cpu it is given n time units.
  - If it doesn\'t complete its execution in this burst time it will be
    put in a lower queue of Q1
  - If this continues the process may end up in an extremely low
    priority queue
  - This will leave the process unattended for some time as the
    processor will continue to ignore this process until it finishes
    with the higher process queue.
  - 
