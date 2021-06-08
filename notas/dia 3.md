# Cual es el objetivo de la clave (KEY) al mandar un evento?

Particionado <<<< La clave no es necesaria....
Para identificar el tema <<< TOPIC
    Subtema...
    
    

Actualización de precios en un supermercado

Yo creo que es mejor el productID, asi me aseguro que los cambios de precio de un producto lleguen ordenados
Lo que garantiza el orden no es la clave... sino que lleguen los mensajes a la misma particion.
Quien garantiza que los mensajes llegan a la misma particion? El particionador
Por defecto el particionador que trae kafka usa la clave.

--- CLAVE
TIPO       <<<<<<< productId
--- MENSAJE
productID: X
prices:
    newPrice: X
    oldPrice: Y
timeStamp: XX FECHA-HORA
otrosDatosProducto: XXXX
usuario: XXXX                    <<<<<<<<    PARSER YAML,  JSON
---
bytes[]

La clave me da un acceso rápido a información relevante de un mensaje para ¿lo que sea?


PRODUCERs
Orden de cambio de precio  >>  TOPIC <<<<   CONSUMIDOR >>>>  Cambiiar el precio de forma efectiva
                                                                UPDATE EN UNA BBDD
                                                                MANDAR UN EMAIL
                                V    KAFKA STREAMS  (programa que manipula datos) TOPICS   >>> PROCESARLOS >>> TOPIC
                                TOPIC  <<<   CONSUMIDOR   [Cantidad de precios cambiados al por CATEGORIA]
                                
                                
                                
Kafka
    Topic A
        Broker 1 - P0
        Broker 2 - P0   
        Broker 3 - P0   <<<<<<<
        Broker 4 - P0   
        Broker 5 - 
        
Replicas: 3

KAFKA: (topic)
min.insync.replicas: Numero mínimo de replicas en las que 
                     quiero que se guarde cada dato.
                     
TOPIC A: min.insync.replicas: 3     <<<<<<      CONSISTENCIA
    Y si se cae un nodo? Que dice KAFKA: ERROR! TOPIC UNAVAILABLE

TOPIC A: min.insync.replicas: 1     <<<<<<      DISPONIBILIDAD
    Y si se cae un nodo? Que dice KAFKA: NADA
    
PRODUCER:    acks=1 => ESPERA A TODAS las disponibles    => Escribe en todos... pero recibo el ok 
                                                            como producer en cuanto se ha 
                                                            escrito en el lider
TOPIC KAFKA: TOPIC A: min.insync.replicas: 2             => Todo OK


PRODUCTOR: 
    Número máximo de mensajes enviados sin haber recibido confirmación.
    max.in.flight.requests.per.connection=4
    
    QUE ME MEJORA ESTO? Throuput
    QUE PELIGRO TIENE? 
        Mando 1 -- FALLA (timeout)
        Mando 2
        Mando 3
        Reintento 1

PRODUCER: enable.idempotence=true


enable.idempotence=true    
Evitar duplicados:
- Reintentos
- 


----
CONSUMIDOR: 
    Configurarlo:
        GROUP
            ASIGNOR
        OFFSET
        DESERIALIZADORES KEY, VALUE
    
    Subscrirme a unos topics (o uno)
    Leer <<<< No leemos de mensaje en mensaje... si no leemos bloques de mensajes
        Commits
        
    
CONSUMER GROUP: 
    Grupo de consumidores que trabajan como si fueran un único consumidor. En que sentido:
    Un mensaje solo va ser consumido por un unico consumidor.

OFFSET:
    Ultimo mensaje leido de una PARTICION de TOPIC por un GRUPO DE CONSUMIDORES
    Como sabe Kafka que un mensaje determinado ha sido el ultimo leido?
    El offset lo guarda KAFKA !
    El offset lo genera Kafka !
    Cuando Kafka incrementa el OFFSET,
        no lo hace cuando manda un mensaje....
        Sino cuando el consumidor le manda un COMMIT !
    Cuando como consumidor consumo un mensaje, lo confirmo a Kafka ----> Kafka: incrementa el offset
                             VVV
                             Lo he leido y procesado y he hecolo que sea que tuviera que hacer .
        Varias estrategias para los commits:
            AutoCommit en Bloque   >>> Siempre de forma asíncrona
            Commits en bloque       | Sincrona o asincrona
            Commit individuales     |
    
TOPIC A                 |
-----------             |   GRUPO DE CONSUMIDORES A
Partition 0 -> URGENTE  |    Consumidor 1
Partition 1 -> NORMALES |   
Partition 2             |   
Partition 3             |   
Partition 4             |   

De que depende el quere tener varios consumidores?
- Lógica:
    Que haya asignado mensajes de diferente naturaleza a cada Partición
    Y quiero que sean tratados de diferente forma.

---
    1000 mensajes por segundo
    VVVVV
TOPIC A                 |
-----------             |
Partition 0 -> NORMALES |    Consumidor 1 -> 200 mensajes por segundo
Partition 1 -> NORMALES |    Consumidor 2
Partition 2             |    Consumidor 3
Partition 3             |    Consumidor 4
Partition 4             |    Consumidor 5
- Capacidad de procesamiento del consumer
    Quiero tratar todos los mensajes de la misma forma
        (independientemente de la particion en la que estén)
    Pero Un consumidor no da a basto a procesar tantos mensajes como necesito

De cuantas particiones pues estar consumiendo un consumidor? Un consumidor puede leer de muchas particiones
Una partición cuantos consumidores la pueden estar leyendo?  De un unico grupo de consumidores (GRUPO A) solo 1.


Si necesito procesar un  mensaje muchas veces (3), para hacer cosas distintas con el mensaje, que necesito?
    3 CONSUMER GROUPS
Cuandos consumidores tendré en cada consumer group? los que necesite... como maximo numero de particiones

Cuando creamos un consumer group: ¿Que es un consumer group? ID
Y empiezo a añadir consumidores a ese consumer group. Como se asignan las particiones que deben ser leidas por cada consumer?
- Mediante un algoritmo:
   - Kafka me da algunos por defecto
   - Yo me puedo crear el mio
- A capón! Este consumidor lee de la particion 7

RANGEASSIGNOR <<<<< DEFAULT

TOPIC B         TOPIC A                  CONSUMERS DEL CONSUMER GROUP A
Partition 0     Partition 0               Consumer 0    P0 TA - P0 TB
Partition 1     Partition 1               Consumer 1    P1 TA - P1 TB
Partition 2     Partition 2               Consumer 2    P2
Partition 3     Partition 3               Consumer 3    P3
Partition 4     Partition 4               Consumer 4    P4 
                                          Consumer 5    -

ROUND ROBIN

TOPIC B         TOPIC A                  CONSUMERS DEL CONSUMER GROUP A
Partition 0     Partition 0               Consumer 0    P0 TA - P1 TB
Partition 1     Partition 1               Consumer 1    P1 TA - P2 TB
Partition 2     Partition 2               Consumer 2    P2 TA - P3 TB
Partition 3     Partition 3               Consumer 3    P3 TA - P4 TB
Partition 4     Partition 4               Consumer 4    P4 TA
                                          Consumer 5    P0 TB
                                          
ROUND ROBIN tras incluir la P5TA

TOPIC B         TOPIC A                  CONSUMERS DEL CONSUMER GROUP A
Partition 0     Partition 0               Consumer 0    P0 TA - P0 TB
Partition 1     Partition 1               Consumer 1    P1 TA - P1 TB
Partition 2     Partition 2               Consumer 2    P2 TA - P2 TB
Partition 3     Partition 3               Consumer 3    P3 TA - P3 TB
Partition 4     Partition 4               Consumer 4    P4 TA - P4 TB
                Partition 5               Consumer 5    P5 TA -
                
                                          
STICKY ASSIGNOR: SIMILAR A ROUND ROBIN, PERO SE ASEGURA QUE SE MANTIENEN LAS ASIGNACIONES PREVIAS

Partition 0     Partition 0               Consumer 0    P0 TA - P1 TB
Partition 1     Partition 1               Consumer 1    P1 TA - P2 TB
Partition 2     Partition 2               Consumer 2    P2 TA - P3 TB
Partition 3     Partition 3               Consumer 3    P3 TA - P4 TB
Partition 4     Partition 4               Consumer 4    P4 TA - P5 TB
                Particion 5               Consumer 5    P0 TB
