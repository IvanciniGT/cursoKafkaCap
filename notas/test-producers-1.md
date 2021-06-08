
# Pregunta 1:
 
To enhance compression, I can increase the chances of batching by using:
- batch.size=65536
- max.message.size=10MB
- linger.ms=20
- acks=all

# Pregunta 2:
 
Which of the following setting increases the chance of batching for a Kafka Producer?
- Increase batch.size
- Increase linger.ms
- Increase message.max.bytes
- Increase the number of producer threads

# Pregunta 3:
 
Your producer is producing at a very high rate and the batches are completely full each time. How can you improve the producer throughput? (select two)
- Disable compression
- Decrease linger.ms
- Increase linger.ms
- Increase batch.size
- Enable compression
- Decrease batch.size

# Pregunta 4:
 
If I want to have an extremely high confidence that leaders and replicas have my data, I should use
- acks=all, replication factor=3, min.insync.replicas=2
- acks=all, replication factor=3, min.insync.replicas=1
- acks=all, replication factor=2, min.insync.replicas=1
- acks=1, replication factor=3, min.insync.replicas=2

# Pregunta 5:
 
A Kafka producer application wants to send log messages to a topic that does not include any key. What are the properties that are mandatory to configure for the producer configuration? (select three)
- value
- value.serializer
- key.serializer
- partition
- bootstrap.servers
- key

# Pregunta 6:
 
What is the risk of increasing max.in.flight.requests.per.connection while also enabling retries in a producer?
- Less resilient
- Reduce throughput
- At least once delivery is not guaranteed
- Message order not preserved

# Pregunta 7:
 
Select all that applies (select THREE)
- min.insync.replicas is a producer setting
- min.insync.replicas is a topic setting
- acks is a topic setting
- min.insync.replicas matters regardless of the values of acks
- min.insync.replicas only matters if acks=all
- acks is a producer setting

## Explicación: 

acks is a producer setting
min.insync.replicas is a topic or broker setting and is only effective when acks=all


# Pregunta 8:
 
Which of the following errors are retriable from a producer perspective? (select two)
- NOT_LEADER_FOR_PARTITION
- NOT_ENOUGH_REPLICAS
- INVALID_REQUIRED_ACKS
- MESSAGE_TOO_LARGE
- TOPIC_AUTHORIZATION_FAILED

# Pregunta 9:
 
To get acknowledgement of writes to only the leader partition, we need to use the config...
- acks=all
- acks=1
- acks=0
