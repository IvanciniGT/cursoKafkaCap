
# Pregunta 1: 
Which of the following Kafka Streams operators are stateful? (select all that apply)
- peek
- aggregate
- joining
- reduce
- flatmap
- count


# Pregunta 2: 
You are running a Kafka Streams application in a Docker container managed by Kubernetes, and upon application restart, it takes a long time for the docker container to replicate the state and get back to processing the data. How can you improve dramatically the application restart?
- Reduce the Streams caching property
- Increase the number of partitions in your inputs topic
- Mount a persistent volume for your RocksDB
- Increase the number of Streams threads


# Pregunta 3: 
You want to perform table lookups against a KTable everytime a new record is received from the KStream. What is the output of KStream-KTable join?
- Kstream
- GlobalKTable
- KTable
- You choose between KStream or KTable


# Pregunta 4: 
Which of the following event processing application is stateless? (select two)
- Read events from a stream and modifies them from JSON to Avro
- Read log messages from a stream and writes ERROR events into a high-priority stream and the rest of the events into a low-priority stream
- Find the minimum and maximum stock prices for each day of trading
- Publish the top 10 stocks each day


# Pregunta 5: 
To transform data from a Kafka topic to another one, I should use
- Consumer + Producer
- Kafka Connect Source
- Kafka Connect Sink
- Kafka Streams


# Pregunta 6: 
We have a store selling shoes. What dataset is a great candidate to be modeled as a KTable in Kafka Streams?
- Items returned
- Money made until now
- The transaction stream
- Inventory contents right now


# Pregunta 7: 
An ecommerce website maintains two topics - a high volume "purchase" topic with 5 partitions and low volume "customer" topic with 3 partitions. You would like to do a stream-table join of these topics. How should you proceed?
- Do a KStream / KTable join after a repartition step
- Model customer as a GlobalKTable
- Repartition customer topic to have 5 partitions
- Repartition the purchase topic to have 3 partitions


# Pregunta 8: 
In Kafka Streams, by what value are internal topics prefixed by?
- tasks-<number>
- group.id
- application.id
- kafka-streams-
