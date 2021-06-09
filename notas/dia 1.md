# Que es Kafka?

Plataforma de mensajería mediante streaming.

## Para que sirve?
- **Enviar y recibir eventos/mensajes**
    - Intercambio de información
    - Sistema de integración.... Me permite mandar información entre unos y otros
    - Sistema de persistencia de información... de forma temporal
    - KAFKA NO ES UNA BASE DE DATOS
    - Procesamiento de los eventos/mensajes que voy mandando

## Necesito una "Plataforma de mensajería mediante streaming" para esto? 
No necesariamente. Solo cuando necesito ASINCRONIA.
Cuando necesito enviar mensajes de forma asíncrona es cuando me hace falta.
- Whatsapp

## Si tenemos comunicaciones Asincronas, es que también las hay síncronas...
Comunicación Sincrona: Los dos participantes están presenten en el momento de realizar la comunicación
Comunicación Asincrona: Los dos participantes no tienen por que estar presenten en el momento de realizar la comunicación

## Por qué nos interesa una comunicación asíncrona?
- Rendimiento:
    El consumidor del mensaje / receptor no es capaz de procesar al ritmo que en un momento dado se van generando los mensajes/eventos
- Alta disponibilidad:
    El consumidor en un momento dado no está disponible, pero quiero garantizar la entrega del mensaje/evento
    El mensaje es mandado al servidor de mensajería.

Siempre vamos a preferir las comunicaciones Asincronas a las Sincronas.... Eso es cierto?
No es cierto... por la funcionalidad.
Ejemplo:
    Tengo un ordenador/computadora que tiene un programa que es capaz de conectarse con una pasarela de pago de un banco,
    para hacer cobros con tarjeta. Que tipo de comunicación quiero en este caso?
        - Sincrona: Eso esta guay! Imaginaros que sois Alcampo.
        - Asincrona... peajes. Por qué?  Imaginaros que se cae la parsarela de pago.


## A quien manda Kafka Información?
- Proactivamente a nadie
- A quien se la pida, siempre que esté autorizado, se la dará


## Por qué Kafka es popular?
- OperSource: El código está disponible para su consulta. Dependerá de la licencia lo que pueda hacer con ese código.
    - GPL: GNU Public License FreeSoftware: >>>Libre<<< y Gratis
    - Obliga a quien usa y modifica ese software, a distribuirlo bajo la mismas condiciones.
        Redhat Inc.  Fedora >>> upstream >>> RHEL Gratis? NO (subscripción) -> CENTOS, Oracle LINUX
- Gratis? 
    - Hay versiones de Kafka? o Distribuciones?
        - Apache Kafka <<< GRATIS
        - Confluent    <<< Depende
- Volumen de transferencia enorme
- Es capaz de ejecutarse en entornos "Big Data"

# Qué es Apache Kafka <<< Confluent
Fundacion que se dedica a realizar/gestionar su financiación projectos Opensource

# Big Data ?
Entornos BigData / INFRAESTRUCTURA (decenas.... cientos o miles de nodos formados por commodity Hardware)> no tiene NADA que ver con Analis de datos... 
HADOOP
    MongoDB
    Casandra
    HIVE
    Kafka
    

2x2
    3 min... Cada segundo cada jugador hace 2 movimientos
    Cada movimiento que hago... genera cuantos mensajes "finales"? 3
    4 jugando... 1 segundo cuantos mensajes tengo? 12
    50k x 12 = 600K mensajes/segundo <<<< Entorno Bigdata <<<< Kafka!
    

## Por que hoy en dia es tan importante un sistema de mensajería
Por la forma en la que hoy en día planteamos las aplciaciones: Monolitos v1 ---> v2
Metodologías: Cascada Waterfall >>>> Agiles (Desarrolladores) agilemanifesto.org <<<
    Entregas en un plazo de entre 15-60 días

Arquitecturas orientadas a servicios: SOA - Microservicios
                                        Nivel de acoplamiento (APIs)

Protocolo de comunicaciones para sistemas de mensajería: JAVA - JMS < JakartaEE
Java era el lenguaje REY de backend --- JMS
    Compra de Sun < Oracle


# VOCABULARIO KAFKA
--------------------

Con que características debe cumplir un entorno de producción:
- Alta Disponibilidad
- Escalabilidad

Cluster: Grupo (sinonimo de group)
    Activo-Pasivo
    Activo-Activo
    
En Kafka siempre arrancamos un cluster Activo-Activo.
Cada servidor de Kafka que tenemos en un cluster: BROKER
Cada broker va identificado por un BROKER_ID

Cuando mandamos EVENTS/mensajes a un cluster de KAFKA... donde los mandamos?

TOPIC: Conjunto de mensajes Ordenado.
    Los mensajes se despachan siguiendo un modelo FIFO (First in - first out): COLA DE MENSAJES

TOPIC internamente en Kafka esta dividido en PARTITIONS

PARTITION: Conjunto de eventos/mensajes todos pertenecientes a un topic.
REPLICA: Una copia de una particion. Cuantas replicas quiero al menos? 1, para tener HA
    Que consigo con las REPLICAS?
        - Alta disponibilidad. Si se cae un broker, hay otros que tienen sus mismos datos, al menos 1
        - Escalabilidad. Tengo escalabilidad en la lectura de datos?    Mejora el rendimiento
                         Tengo mejora en la escritura de datos?         Puedo perder. Cuando mande el mensaje, podre establecer en cuantas replicas quiero que se guarde antes de obtener confirmación

Cuando mandamos un mensaje a un TOPIC de Kafka... aquel se guarda en una partición o en varias?
En una UNICA PARTITION

Que obtengo con las particiones? ESCALABILIDAD
Cada PARTICION está asumida integramente por un servidor de Kafka: BROKER

Los mensajes se guardan en ultima instancia dentro de archivos llamados LOGS


El TOPIC 17 lo tengo dividido en particiones:
    Cuantas particiones puedo crear de este topic? 4? NADA QUE VER UNA COSA CON LA OTRA
    12 particiones: 0 1 2 3 4 5 6 7 8 9 10 11     0b 1b 2b 3b 4b 5b 6b 7b 8b 9b 10b 11b
Ejemplo:
    Cliente... JAVA PYTHON SCALA C#   ... hay una libreria de Kafka
        Mandar un mensaje/EVENT a Kafka. 
            A donde mando el mensaje? A un "TOPIC 17"
            A quién le mando el mensaje?
                Va a hacer una comunicación basada en protocolo? TCP: IP
    Cluster de KAFKA
        Nodo1 - Maquinas fisicas - Contenedorización
            Broker 1 IP1 Puerto1
                1 2 3b 4b
            Broker 2 IP2 Puerto1
                4 5 6b 7b
        Nodo2 - Maquinas fisicas
            Broker 3 IP3
                7 8 10b 11b
            Broker 4 IP4
                10 11 3b 6b
        Nodo3 - Maquinas fisicas
            Broker 5 IP3
                3 6 9b 0b
            Broker 6 IP4
                9(m1) 0 1b 2b

Cluster Kafka
    Broker 1   90%
        topic 1 a b2 
    Broker 2   90%
        topic 1 b c2
    Necesito un broker nuevo:
    Broker 3   30%
        topic 1 c a2
    Broker 4   0%
    Broker 5   0%
    
    Esto con los -- de corbata.... JODER !!!!!! HA ???? NO    
        Que pasa si se cae Broker3?
            Mirar las CPUs, que les pasa?
                Se me cae el cluster entero
    
ESCALABLE
        Apache Zookeeper: 
            Balanceador de carga 1 IP -> Sofware que recibe peticiones y la distribuye entre servidores backend
            Balanceador de carga 2 IP 
            IP de balnceador de balanceadores... que me viene regalada por los gestores de contenedores.


- Sobre propio "Hierro"
    Microservicio 1:  x 7
        Springboot
            ^^^ Servidor embebido TOMCAT
    Balanceador de carga: 
        Apache -> ajp http
        NginX
- Virtualización (en una MV)
- Contenedores: Forma de instalar/ejercutar aplicaciones dentro del kernel de cgroup (google) >> LINUX.
    Microservicio 1:  x 7 < CONTENEDOR
        Springboot
            ^^^ Servidor embebido TOMCAT
    Balanceador de carga: 
        El propio gestor de contenedores: docker swarm, Kubernetes, Openshift : Asignar reglas a nivel de Kernel del SO Linux 
            IPtables > NetFilter que hacen las redirecciones a las IPs de backend.


PRODUCERS
    Aquel que envia eventos a un kafka
        Topic / Particion
    Producer >>> siempre manda en que particion va a ir el mensaje
    Cuando yo monte un producer, lo voy a hacer a través de una libreria... Y esas librerias normalmente me encapsulan esa funcionalidad
        Esas librerias normalmente van a utilizar un algoritmo:
            Round-Robin 0-1-2-3
            En funcion de la key IDENTIFICADOR DEL MENSAJE ---> HASH ---> HUELLA NUMERICA % (OPERADOR MOD resto) NUMERO DE PARTICIONES: Resultado entre 0 - (N-1)
                Letra DNI 12345678-LETRA
                    Se toma el numero y se divide entre 23... Y me quedo con el RESTO: 0-22 -> LETRA
            
        HashMap Cada clave la transforma en una huella en un HASH
    Que info manda?
        KEY
        CUERPO
        A que TOPIC
        A que PARTITION

CONSUMERS
    Alguien que lee eventos de un kafka.
        - Va a pader mensajes de:
            - Un TOPIC
            - Una PARTITION
            - OFFSET: Desde cual quiero leer
                NO ES DONDE ME HE QUEDADO... Es desde cual quiero leer, normalmente será el ultimo que he leido +1
            Kafka va a guardar internamente cual OFFSET Es el último que ha sido leido de una PARTICION por un consumnidor

MENSAJES:
    Logs de un webserver
        ID
        Descripcion
        NIVEL DE LOG (error, warning, info)
    Sensor de temperatura
        ID
        Temperatura (normal, alerta)
    

# SCALA: Sintaxis alternativa a JAVA                    ==>>  BigData
# Kotlin: Sintaxis alternativa a JAVA <= JetBrains      ==>>  Apps Moviles
IntelliJ <<< JetBrains  > AndroidStudio
 Google : Desarrollo de apps para Android
    Eclipse < AndoirSDK

C++  Orientacion a objetos
SCALA - JAVA
.scala   ===> .class ===> JVM

Encapsulacion <<< La implementacion get/set

C# El java bien hecho

Productores Custom en JAVA para Kafka
Comsumers   Custom en JAVA para Kafka

Procesos Kafka Stream JAVA para Kafka
                      SCALA
---

