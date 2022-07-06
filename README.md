# RabbitMQ Direct Exchange

- A direct exchange delivers messages to queues based on the message routing key.
- A direct exchange is ideal for the unicast routing of messages (although they can be used for multicast routing as well). Here is how it works:
- A queue binds to the exchange with a routing key K.When a new message with routing key R arrives at the direct exchange, the exchange routes it to the queue if K = R
- Direct exchanges are often used to distribute tasks between multiple workers (instances of the same application) in a round-robin manner. When doing so, it is important to understand that, in AMQP 0-9-1, messages are load-balanced between consumers and not between queues.

A direct exchange can be represented graphically as follows:

![](img/0.png)


### RabbitMQ Create Direct Exchange
![](img/1.png)

### RabbitMQ Create Queue
![](img/2.png)


### RabbitMQ Bind Queue
![](img/3.png)



### Status of Queues After Run Producer App 
![](img/4.png)


