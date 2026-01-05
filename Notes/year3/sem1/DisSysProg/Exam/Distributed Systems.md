# What is a distributed system?

- A distributed system is one which components located at networked computers communicate and coordinate their actions by passing  messages.

## Key example

- Netflix would be one of the worlds most popular distributed systems given it is composed of over 1000 micro-services which all communicate between one another via software like Kafka as a means of providing users with services like reccomendations, sign ins and storage of videos which when streamed are delivered to users.
- Multiple points of failure: Although netflix can go down from time to time, it isn't usually the entire system, it is likely the different microservices at netflix are all individually hosted on their own AWS servers and there are security protocols for each service so that if one goes down, the entire application doesn't follow
- Networking: The load that would be placed on a single server for billions (with a b) users to be being reccomended hundreds of shows/movies while another person is playing their games and another person is streaming a show would be ludicrous. As a result each of those components runs on their own server and when the user requests a certain service (play a game, watch a movie), it is only then that the netflix servers would receive a message to deliver the user with that service.
- Their usage of AWS although contentious for some, makes a lot of sense given the scale and profits their working with. For a smaller startup using their own servers may make more sense given they need total control of their centralised simple frontend and backend however given the larger quantity of users and videos netflix attempt to deliver, they require the compute of the highest cost.
## Characteristics of a distributed system:
1. Concurrency of components
2. Lack of a global clock
3.  Multiple points of failure
4. Multiple points of control

## Characteristics of Centralised Systems:
1. All resources accessible
2. Single point of control
3. Single point of failure
4. Software runs in a single process

## Examples of Distributed Systems

1. The "Cloud"
2. Local area network and intranet
3. Database management system
4. Automatic Teller Machine Network

## Common (More specific) Characteristics of a distribute system

### Heterogenity (the quality or state of being diverse in character or content.)
- Variety and differences in
	- Networking
	- Computer Hardware
	- Operating systems
	- Programming languages
- Given this wide variety, we use **Middleware** as a means of providing abstraction and masking the differences between the operating systems, hardware and programming languages. 

- Eg, we have a mobile app written in kotlin, running on android that sends http requests over tcp to an ubuntu server that receives messages via rabbitmq

- **Mobile code** (not mobile like android ios) is code that can be sent from one computer to another and run fine at the destination (Eg Java applets on the JVM)
## Scalability 
- Adaption of a system to:
	- Accomodate more users
	- Respond just as fast if not faster
- Usually done vertically by adding more processors, more/faster memory etc 
- Vertical: Adding more/better hardware
- Horizontal: Dispersing load across more of similar hardware

- Software components should not need to change/scale back when the scale of a system increases
- Design components to be scalable! (Eg micro-services running in individual docker containers on a single host machine)
## Concurrency

- Components in a distributed system are executed in concurrent processes

- These components update and access shared resources (variables,databases,device drivers etc)

- Integrity of the system may be violated if concurrent updates are not coordinated.  

- Lost updates (e.g. user A adds a line to the end of a file and saves the change remotely; user B was making a change at the same time and saves the change remotely slightly later, but user A’s change was lost in the process)  


## Design issues in Distributed systems:

### Naming

- A name is resolved when translated into an interpretable form for resource/object reference
- Eg Assigning IPs to then getting names from DNS to then name resolution from DNS to IP addresses involves multiple steps

- Design considerations:
	- Choice of name for each resource type (uniqueness)
	- Name service to resolve resource names to resolve ids
### System Architecture

- There is a wide variety of different distributed system architectures but selecting the exact correct one can prove tricky. 
- Eg
1. Client-server - One server invoked by many clients which must be able to accept many concurrent connection
2. Peer to Peer - The P2P process deals with a network structure where any participant in the network known as a node acts as both a client and a server.
3. Proxy servers - A proxy server acts as a middle layer between your device and the internet, hiding your real IP address and improving online privacy. It routes your requests through another server, offering security, anonymity, and control
### Workload allocation

- Deciding on who should handle more of the compute between the general structure of clients and servers can become challenging. Theoretically the majority of the compute should be done on the server keeping in mind we don't know what kind of hardware is being used by the client however if we are to expect the single or even multiple dispersed servers to handle all of the compute for all users, the server would very quickly lose a significant amount of performance. Therefore for services like online games or web based video editing software.