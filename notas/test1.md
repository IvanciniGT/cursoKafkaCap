# Pregunta 1:
A kafka topic has a replication factor of 3 and min.insync.replicas setting of 2. How many brokers can go down before a producer with acks=all can't produce?
- 1
- 0
- 2
- 3

# Pregunta 2:
You are sending messages with keys to a topic. To increase throughput, you decide to increase the number of partitions of the topic. Select all that apply.
- New records with the same key will get written to the partition where old records with that key were written
- New records may get written to a different partition
- All the existing records will get rebalanced among the partitions to balance load
- Old records will stay in their partitions


# Pregunta 3:
A producer is sending messages with null key to a topic with 6 partitions using the DefaultPartitioner. Where will the messages be stored?
- Partition 5
- The partition for the null key
- Partition 0
- Any of the topic partitions


# Pregunta 4:
What is returned by a producer.send() call in the Java API?
- Future<ProducerRecord> object
- Future<RecordMetadata> object
- A Boolean indicating if the call succeeded
- Unit


# Pregunta 5:
The rule "same key goes to the same partition" is true unless...
- the number of kafka broker changes
- the number of producer changes
- the number of partition changes
- the replication factor changes


# Pregunta 6:
When is the onCompletion() method called?

    private class ProducerCallback implements Callback {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
            if (e != null) {
                e.printStackTrace();
                }
            } 
    }
        ProducerRecord<String, String> record =
                new ProducerRecord<>("topic1", "key1", "value1");
        producer.send(record, new ProducerCallback()); 
    }

- When message is serialized successfully
- When the broker response is received
- When the message is partitioned and batched successfully
- When send() method is called

# Pregunta 7:
To prevent network-induced duplicates when producing to Kafka, I should use
- batch.size=1
- max.in.flight.requests.per.connection=1
- enable.idempotence=true
- retries=200000


# Pregunta 8:
You are doing complex calculations using a machine learning framework on records fetched from a Kafka topic. It takes more about 6 minutes to process a record batch, and the consumer enters rebalances even though it's still running. How can you improve this scenario?
- Add consumers to the consumer group and kill them right away
- Increase session.timeout.ms to 600000
- Increase heartbeat.interval.ms to 600000
- Increase max.poll.interval.ms to 600000


# Pregunta 9:
A topic has three replicas and you set min.insync.replicas to 2. If two out of three replicas are not available, what happens when a consume request is sent to broker?
- A new leader for the partition will be elected
- NotEnoughReplicasException will be returned
- An empty message will be returned
- Data will be returned from the remaining in-sync replica


# Pregunta 10:
To read data from a topic, the following configuration is needed for the consumers
- any broker to connect to, and the topic name
- the list of brokers that have the data, the topic name and the partitions list
- any broker, and the list of topic partitions
- all brokers of the cluster, and the topic name


# Pregunta 11:
To allow consumers in a group to resume at the previously committed offset, I need to set the proper value for...
- group.id
- value.deserializer
- enable.auto.commit
- auto.offset.resets


# Pregunta 12:
A consumer starts and has auto.offset.reset=none, and the topic partition currently has data for offsets going from 45 to 2311. The consumer group has committed the offset 10 for the topic before. Where will the consumer read from?
- offset 45
- offset 10
- offset 2311
- it will crash


# Pregunta 13:
A producer just sent a message to the leader broker for a topic partition. The producer used acks=1 and therefore the data has not yet been replicated to followers. Under which conditions will the consumer see the message?
- Right away
- When the message has been fully replicated to all replicas
- When the high watermark has advanced
- Never, the produce request will fail



# Pregunta 14:
In the Kafka consumer metrics it is observed that fetch-rate is very high and each fetch is small. What steps will you take to increase throughput?
- Increase fetch.max.bytes
- Decrease fetch.min.bytes
- Increase fetch.min.bytes
- Increase fetch.max.wait
- Decrease fetch.max.bytes


# Pregunta 15:
How does a consumer commit offsets in Kafka?
- It directly commits the offsets in Zookeeper
- It directly sends a message to the __consumer_offsets topic
- It interacts with the Group Coordinator broker


# Pregunta 16:
A consumer sends a request to commit offset 2000. There is a temporary communication problem, so the broker never gets the request and therefore never responds. Meanwhile, the consumer processed another batch and successfully committed offset 3000. What should you do?
- Use the kafka-consumer-group command to manually commit the offsets 2000 for the consumer group
- Add a new consumer to the group
- Restart the consumer
- Nothing


# Pregunta 17:
Which actions will trigger partition rebalance for a consumer group? (select three)
- Remove a broker from the cluster
- Add a broker to the cluster
- A consumer in a consumer group shuts down
- Add a new consumer to consumer group
- Increase partitions of a topic


# Pregunta 18:
There are 3 producers writing to a topic with 5 partitions. There are 10 consumers consuming from the topic as part of the same group. How many consumers will remain idle?
- 5
- None
- 3
- 10


# Pregunta 19:
What is true about partitions? (select two)
- A broker can have different partitions numbers for the same topic on its disk
- You cannot have more partitions than the number of brokers in your cluster
- A partition has one replica that is a leader, while the other replicas are followers
- Only out of sync replicas are replicas, the remaining partitions that are in sync are also leader
- A broker can have a partition and its replica on its disk


# Pregunta 20:
When auto.create.topics.enable is set to true in Kafka configuration, what are the circumstances under which a Kafka broker automatically creates a topic? (select three)
- Client alters number of partitions of a topic
- Consumer reads message from a topic
- Client requests metadata for a topic
- Producer sends message to a topic
