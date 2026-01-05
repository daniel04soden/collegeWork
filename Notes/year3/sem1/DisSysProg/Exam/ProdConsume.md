# Messages and events
- Asynchronous messaging is a solution that is non-blocking
- Often referred to as the producer-consumer  

- Messages contain packets of data, which are in a known format
- Messages are often the result of events which indicate some change of state that other interested components should known about


## Brokers and channels

- Producers of messages open an input channel to a message broker.
- Consumers interested in the messages, open an output channel with the message broker
## Advantages of pub-sub

- **Decoupling** - separate subsystems can be managed independently
- **Scalability** - Producer can fire and forget, move on with other processing while we can add potentially any other number of consumers
-  **Reliability** - better handling of load because messages can be queued and consumers will consume them as and when they are able to
## Disadvantages of pub-sub

- Loose coupling can **introduce complexity**, such as messages being lost, lack of acknowledgement (meaning the producer might assume delivery even when  it has not)
- Messaging can become a **bottleneck if overloaded** (as opposed to gRPC which allows clients and servers to communicate independently without shared middleware just need their own port)


# Message Subsets

- Certain consumers may not be interested in all messages produced so filtering is sometimes needed
- A **topic** is  label that indicates a subset/subcategory. This can be used when a producer publishes many types of messages applicable to different consumers.
- Content-based filtering can also be done, requiring some kind of pattern/constraint matching in the message content.


## Message broker

- The architectural pattern that supports prod-con is message brokers. They handle validation, transformation (serialising and deserialising) and routing. Eg Rabbitmq
- For go most often rabbitmq is used as the consumer producer library through the amqp or advanced queue messaging protocol which specifies:
	- Wire format - how messages are encoded on the network
	- Commands - how clients and brokers talk (eg open channel, declare, queue, publish, message)
	- Delivery guarantees - ack/nack, persistent messages, transactions. 
	- Entities - exchanges queues, binding, and routing keys.
### Routing types

| Exchange type | Routing behaviour                                                                                  | Common use case                                         |
| ------------- | -------------------------------------------------------------------------------------------------- | ------------------------------------------------------- |
| Direct        | Routes messages by exact match between the messages **routing key** and the queues **binding key** | Point-to-point messaging (specific recipient)           |
| Fanout        | Broadcasts every message to all queues bound to the exchange (ignores routing key)                 | Pub/sub, notifications, live feeds                      |
| Topic         | Routes by pattern matching on the routing keys using * one word and # (many words) wildcards       | Selective broadcast, event categories, multi-room games |
| Headers       | Routes based on matching key-value pairs in the message headers instead of routing keys.           | Metadata-based routing (eg  format region priority)     |
