
# Pregunta 10:
 
A customer has many consumer applications that process messages from a Kafka topic. Each consumer application can only process 50 MB/s. Your customer wants to achieve a target throughput of 1 GB/s. What is the minimum number of partitions will you suggest to the customer for that particular topic?
- 50
- 20
- 10
- 1

# Pregunta 11:
 
Producing with a key allows to...
- Allow a Kafka Consumer to subscribe to a (topic,key) pair and only receive that data
- Influence partitioning of the producer messages
- Ensure per-record level security
- Add more information to my message

# Pregunta 12:
 
A topic receives all the orders for the products that are available on a commerce site. Two applications want to process all the messages independently - order fulfilment and monitoring. The topic has 4 partitions, how would you organise the consumers for optimal performance and resource usage?
- Create 8 consumers in the same group with 4 consumers for each application
- Create two consumer groups for two applications with 4 consumers in each
- Create four consumers in the same group, one for each partition - two for fulfilment and two for monitoring
- Create two consumers groups for two applications with 8 consumers in each

# Pregunta 13:
 
There are two consumers C1 and C2 belonging to the same group G subscribed to topics T1 and T2. Each of the topics has 3 partitions. How will the partitions be assigned to consumers with PartitionAssignor being RoundRobinAssignor?
- C1 will be assigned partitions 0 and 1 from T1 and T2, C2 will be assigned partition 2 from T1 and T2.
- Two consumers cannot read from two topics at the same time
- C1 will be assigned partitions 0 and 2 from T1 and partition 1 from T2. C2 will have partition 1 from T1 and partitions 0 and 2 from T2.
- All consumers will read from all partitions

# Pregunta 14:
 
A consumer wants to read messages from a specific partition of a topic. How can this be achieved?
- Call assign() passing a Collection of TopicPartitions as the argument
- Call subscribe() passing TopicPartition as the argument
- Call subscribe(String topic, int partition) passing the topic and partition number as the arguments

# Pregunta 15:
 
Select all the way for one consumer to subscribe simultaneously to the following topics - topic.history, topic.sports, topic.politics? (select two)
- consumer.subscribePrefix("topic.");
- consumer.subscribe(Arrays.asList("topic.history", "topic.sports", "topic.politics"));
- consumer.subscribe("topic.history");
  consumer.subscribe("topic.sports");
  consumer.subscribe("topic.politics");
- consumer.subscribe(Pattern.compile("topic\..*"));

# Pregunta 16:
 
A consumer starts and has auto.offset.reset=latest, and the topic partition currently has data for offsets going from 45 to 2311. The consumer group has committed the offset 643 for the topic before. Where will the consumer read from?
- offset 643
- it will crash
- offset 45
- offset 2311
