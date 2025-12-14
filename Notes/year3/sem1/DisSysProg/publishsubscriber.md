# Introduction

- Distributed systems with many components often need to have components
  provide info to other components

- Asynchronous messaging is a solution that is non-blocking

## Messages and events

- Messages contain packets of data which are in a known format (eg
  serialized data such as in a JSON format)

- Messages are often the result of events which indicate some change of
  state that other interested components should know about

- We looked at this approach with the Observer pattern

## Brokers and Channels

- Producers/Publishers of messages open an input channel to a message
  broker
- Consumers/Subscribers interested in the messages open an ouput channel
  with the message broker

# Advantages of Pub-sub

- Decoupling - separate subsystems can be managed independently

- Fault tolerance - if a subscriber crashses, it can restart and pick up
  messages with it being back up and running

- Scalability - producer can fire and forget, move on with other
  processing while we can add potentially any number of consumers.

- Reliability - better handling of load because messages can be queued
  and consumers will consume them as and when they are able to.

- Supports heterogeneity - can use any number of supported programming
  languages per component / subsystem, allowing for improved
  integration.

- Async/non-blocking can improve efficiency.

- Deferred processing - eg can send an event message during the day,
  handle the event at off-peak hours.

- Supports \"pushing\" of information to clients (as opposed to clients
  \"pulling\" info from a server)

# Disadvantages of Pub-Sub

- The advantage of loose coupling can introduce complexity, such as
  messages being lost lack of acknowledgement (meaning produccer might
  assume delivery even when it has not)

- Messaging can become a bottleneck if overloaded (as opposed to gRPC,
  which allows clients and servers to communicate independently without
  shared middleware - just need their own port)

# Message Subsets

- Consumers are not interested in all messages produced so fitlering is
  needed. A topic is a label that indicates a subset and can be used
  when a producer publishes many types of messages applicable to
  different consumers
  - Content based filtering can also be done, requiring some kind of
    pattern/constraint matching in the message content
