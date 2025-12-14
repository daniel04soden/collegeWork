# What are they?

- Systems in which components located at networked computers communicate
  and coordinate their actions only by passing messages. This definiton
  leads to the following characteristics
  1.  Concurrency of components
  2.  No global clock
  3.  Independent failures of components

## Centralized vs Distributed system characteristics

### Centralized

- One components with non-autonomous parts
- Component shared by users all the time
- All resources accessible
- Software runs in a single process
- Single point of control and single point of failure

### Distributed

- Multiple autonomous components
- Components not shared by all users
- Software runs in concurrent processes on different processors.
- Multiple points of control, multiple points of failure

## Examples of distributed systems

- Local area network and intranet
- Database management system
- Automatic teller machine network
- The \"Cloud\"

## Common characteristics of distributed systems:

### 1. Diversity/Hetrogenity

- Differences in networks, computer hardware, operating systems,
  programming languages and implementations by different devs
- Middleware to provide a programming abstraction as well as making the
  diversity of the underlying networks,hardware,OS anf languages
- Mobile code to refer to code that can be sent from one computer to
  another

### 2. Openess

- Concerend with extensions and imporvements of distributed systems

- Detailed interfaces of components need to be published.

- New components have to be integerated with existing components.

- Differences in data representation of interface types of diffrent
  processors have to be resolved

### 3. Security

- Clients send requests to access data managed by servers, resources in
  the networks
  - Doctors requesting records from hospitals
  - Users purchase products through electronic commerce
- Security is required for:
  - Concealing the contents of messages: security + privacy
  - Identifying a remote suer or other agent correctly (ie
    authentication)
- New challenges:
  - Denial of service attack
  - Security of mobile code

### 4. Scalability

- Adaption of distributed systems to accomodate more users and respond
  faster.
- Usually done by adding more/faster processing
- Components shouldn\'t need to be changed when the scale of a system
  increase, horizontal scaling is favourable ie clusters, replication,
  microservices etc.

### 5. Concurrency

- Components in distributed systems are executed in concurrent
  processes.

- Components access and update shared resources (eg variables, databases
  and device drivers)

- Integrity of the system may be violated if concurrent updates are not
  coordinated

  - Lost updates (eg user a adds line to end of file and save changes
    remotely, user B making a change at the same time and saves the
    change remotely slightl later but user A\'s change was lost in the
    process)
  - Inconsistent analysis

- Will examine strategies later using locking or semaphores

### 6. Transparency

- Distributed systems should be perceived by users and application
  programmers as a whole rather than as a collection of cooperating
  components

- Transparency has different aspects

- These represent various properties that distributed systems should
  have

1.  Types of transparency

    1.  **Acesss transparency**: No didfference between remote and local
        remote access methods.

    2.  **Location Transparancy**: Details of the topology of the system
        should be of no concern to the user

    3.  **Concurrency Transparency**: Users and applications should be
        able to access shared data or objects without interference
        between each other.

    4.  **Replication Transparency:** If the system provides a
        replicaiton it should not concern the user ie we include the
        applications programmer as a user.

    5.  **Fault Transparency:** If software or hardware failures occur,
        these should be hidden from the user, this can be difficult in a
        distributed system since partial failure of the communications
        subsystem is possible and this may not be reported. As far as
        possible, fault transparency will be provided by mechanisms that
        relate to acess transparency. However when the faults are
        inherent in the distributed nautre of a sytem, then access
        transparency may not be maintained. The mechanisms that allow a
        system to hide faults may result in changes to access
        mechanisms.

    6.  **Migration Transparency**: If objects migrate to another
        service (to provide better performance, reliability etc) it
        should be completely hidden form the user. There is no users
        wondering whether or not we switched from postgres to clustered
        mongodb.

    7.  **Performance Transparency**: The configuration of the system
        should not be apparent to the user in terms of performance. This
        may also require some complex resource management mechanisms as
        a means of hiding slowdowns or tricks to hide poor performace.
        It may not be possible in all cases, especially for users in low
        performance networks

    8.  **Scaling Transparency**: A system should be able to grow
        without affecting application algorithms. Graceful growth and
        evolution is an important requirement for most enterprises. A
        system should also be capable of sclaing down to smaller
        environments when required.

### 7. Failure handling

- Hardware software and networks do fail.

- Must maintain availability even at low levels of
  hardware/software/network reliability

- Fault tolerance is achieved by

  - Recovery (health monitor notices failure, restarts service)
  - Redundancy (Multiple copies running in a cluster so a failure of a
    copy only means service degredation rather than full failure)

## Issues which Arise in the design of a distributed system

1.  Naming

    - Name is resolved when translated into an interpretable form for
      resource/Object reference

      - Communication identifier - easiest to program for simple ip+port
        but is not flexible for situations where the network may be
        changed. This is why we use domain names which is a logical
        identifier vs a physical identifier
      - Name Resolution: Involves several translation steps

    - Design considerations:

      - Choice of name space for each resource type
      - Name service to resolve resource names to communicate the id

2.  Communication

    - Separated components communicate with sending processes and
      receiving processes for data transfer and synchronization
      - Synchronous (blocking)
      - Asynchronous (non-blocking)
      - Abstractions defined: channels, sockets & ports
    - Communication patterns:client-server communication (RPC - Running
      a function on a computer network as if it were local ie remote
      procedure call. Java RMI or gRPC) and group multicast
      - gRPC is googles open source RPC protocol which enables
        communication across dispersed systems.

3.  Software Structure

    - there is layers to both centralized and distributed systems
    - Distribute systems are as follows:
      - Application
        - Programming support
        - Open services
          - Open system kernel services
          - Computer and network hardware

4.  System Architecture

    - Client server
    - Peer-to-peer
    - Services provided by multiple servers
    - Proxy servers and caches
    - Mobile code and mobile agents
    - Network computers
    - Thin clients and mobile devices

5.  Workload Allocation

6.  Consistency Maintenance
