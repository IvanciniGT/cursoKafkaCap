Teorema de CAP
----------------
Sistema distribuido:
- Consistencia de la información:
    - Todos los observadores vean la misma/ultima información en el mismo tiempo
- Disponibilidad de la información:
    - Que cuando un cliente solicite información, está siempre le sea entregada
- Tolerancia la Particionado
    - El sistema siga siga funcionando aunque se produzca un particionado en la red (un fallo de red)

En un momento dado, un sistema distribuido solo puede garantizar 2 de estas 3 caracteristicas.

Cambio la Información
  Cliente 1            Cliente 2
    V                     ^
Nodo A ---------|------ Nodo B
Información'    |    Información


Tengo que tomar una decisión. Antes situación, que devuelve el Nodo B? Cual es el comportamiento que DESEO YO de mi sistema?
- Devolver la infromación que el tiene (que puede no ser la última)                                     >>>>>     DISPONIBILIDAD
- Decir que no le devielvo nada, ya que no estoy seguro de que la información que tengo es la buena     >>>>>     CONSISTENCIA

  Cliente 1            Cliente 2
    V                      ^
Nodo A --------|------- Nodo B
Información'   |     Información'

Con KAFKA yo elijo.


PRODUCER EVENTs   > EVENTO >>> BROKER
    - Event    - Key
               - Body
    - Topic
    - Partition: El producer usa un algoritmo PARTITIONER por defecto para determinar la partición: HASH KEY % # PARTICIONES TOPIC
    - El numero de particiones en el que tiene que escribir para darlo por válido      <<<<<<      CONSISTENCIA

Broker 1    
^   TopicA    P1'   R2   R3
^    
Broker 2           <<<<<<<<<<<<<<<<<<<<<<< LB <<<<    Alta de un dato en P1  <<<<<<<   PRODUCER 
    TopicA    P2   R1'   R3                                                                 Quiero 1 copias del dato para tener OK - FUNCIONA
Broker 3     PUFFF                                                                          Quiero 3 copias del dato para tener OK - NO FUNCIONA
    TopicA    P3   R1'   R2

Particiones <<< Son los elementos que MANDAN en el cluster
Replicas no mandan


Broker 1    
    TopicA    P1'   R2    P3
Broker 2           
    TopicA    P2    R1'   R3
---------------------------------
Broker 3     PUFFF         
    TopicA    P3   R1'   R2

Puedo tomar 2 decisiones:
    - 1: Que negocien entro los brokers, cual va a ser el nuevo P3
            Si me piden cambios a P3, que pasa funciona? SI                 <<<<<<<<<   ALTA DISPONIBILIDAD
    - 2: Dejo a P3 inhabilidata                             
            Si me piden cambios a P3, que pasa funciona? NO                 <<<<<<<<<   CONSISTENCIA
        
        

Broker 1    
    Topic2    P0   R0 R1 R2
Broker 2           
    Topic2    P2   R0 R1 R2
Broker 3     
    Topic2    P3   R0 R1 R2




--- Escalabilidad
Montar varios brokers

Producers      BROKERS              CONSUMERS
----------------------------------------------------------------------------
Log     >      Broker 1
Log     >      Broker 2
Log     >      Broker 3
Log     >
Log     >
Log     >
EV1             TOPIC
EV2            Particion         
                                    Consumer GROUPS
                            <       Fluentd MONITORIZACION 1    >>>> INDEXACION: ElasticSearch SISTEMAS (Monitorizacion)   EV1
                            <       Fluentd MONITORIZACION 2    >>>> INDEXACION: ElasticSearch SISTEMAS (Monitorizacion)
                            <       Fluentd MONITORIZACION 3    >>>> INDEXACION: ElasticSearch SISTEMAS (Monitorizacion)
                                    Consumer GROUPS  --  offset EV1
                            <       Fluentd BUSINESS 1          >>>> INDEXACION: ElasticSearch BUSINESS (Analisis)         EV2
                            <       Fluentd BUSINESS 2          >>>> INDEXACION: ElasticSearch BUSINESS (Analisis)         EV1
                            




-------------------------
Productor en JAVA
Libreria en JAVA que permita conectar con KAFKA
<!-- https://mvnrepository.com/artifact/org.apache.kafka/kafka-clients -->
<dependency>
    <groupId>org.apache.kafka</groupId>
    <artifactId>kafka-clients</artifactId>
    <version>2.8.0</version>
</dependency>

Paquete:
    org.apache.kafka.clients.producer

Clases:
    Producer
    ProducerConfig
    ProducerRecord         EVENTO que mandamos a KAFKA
    CallBack               Función a la que se nos llamará cuando el mensaje haya sido procesado
        RecordMetadata     Información sobre el evento ya creado en Kafka







-----------------------------------------
Random ---- Aleatorio, no implica equiprobable
Nodo 1 - NPI - 100%
Nodo 2 - NPI - 0%
Nodo 3 - NPI - 0%


---------------------------------------

Nodo 1
    TOPIC A   0  >>>>       0 1 2 3 4 5        <<<<<<< CONSUMER    Lo que quiero garantizar es que los mensajes sean consumidos en el orden que se producen   FIFO
Nodo 2
    TOPIC A   1  >>>>       0                  <<<<<<< CONSUMER
Nodo 3
    TOPIC A   2
                            0 1 2 3 4 5
    
El offset a que nivel va? PARTICION
