# Topics

## Listado de topics

kafka-topics.sh --list --bootstrap-server localhost:9092
kafka-topics.sh --list --zookeeper localhost:2181

## Crear un topic
kafka-topics.sh --bootstrap-server localhost:9092 \
    --create --topic topic1
    
kafka-topics.sh --bootstrap-server localhost:9092 \
    --create --topic topic2 --replication-factor 3
    
kafka-topics.sh --bootstrap-server localhost:9092 \
    --create --topic topic3 --partitions 3
    
kafka-topics.sh --bootstrap-server localhost:9092 \
    --create --topic topic4 --partitions 2

## Describir un topic
kafka-topics.sh --bootstrap-server localhost:9092 \
    --describe --topic topic4



----

# kafka-console-producer.sh --bootstrap-server localhost:9092 --topic topic1
# kafka-console-consumer.sh --bootstrap-server localhost:9094 --topic topic1 --from-beginning


Producer  >>>>>>>>>>    KAFKA   <<<<<<<<<<<  Consumer
                       brokers