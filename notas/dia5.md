Streams

Procesamiento de datos entre topics

TOPIC A >>>> STREAM >>>> TOPIC B
              ^^^^
El stream se esta ejecutando de forma continua

Especificamos en JAVA un Stream, que hacemos:
    - Definir un procesamiento.
Cuando se ejecuta ese procesamiento?
    Arrancamos el Stream
    
Se trabaja en Ventanas de tiempo.
En cada ventana de tiempo, se ejecuta el stream.

KStream <<<< Los datos recibidos en una ventana de tiempo genérica
    => Materializar en unos datos concretos
        KTable<<<<  Datos concretos en una ventana de tiempo

                    ESTADO
|       | STREAM    KTABLE      |       |       |        |
 a: 2     (a:1)      a:3
 b: 3
 c: 3
 
 
 
La información de estado se guarda primariamente en un directorio
 gestionado por el Stream RocksDB, pero adicionalmente se almacena en unos topics
 de Kafka
 
Qué es un contenedor?
    Un entorno aislado para la ejecución de procesos dentro de un SO Linux.
    Aislamiento: 
        Memoria
        CPU
        RED - IP
        FileSystem    <<<<<<<

Imagen de contenedor
    Software estrictamente necesario para ejecutar un proceso
    
    Archivo comprimido con una determinada estructura de directorios 
        (compatible con el FS de Linux normalmente)
        mi application
        librerias que requiera mi app
        otras utilidades que me puedan hacer falta en un futuro
    Configuración por defecto de como deben ejecutarse procesos
        dentro de ese contenedor
    Información de como está montada esa imagen


Cuando un proceso se ejecute en el contenedor, y pida acceso a su FS |
                                                                     V
                                                                     
                                                                     
*CARPETA ASOC CONT                                         /home/archivo1.txt
CARPETA IMAGEN           /bin     /opt    /tmp     /usr    /home/otros.txt


Cuando nosotros borramos un contenedor su carpeta asociada se borra


VOLUMEN            /datos (PERMITEN INFORMACION PERSISTENTE)
                     ^ (esto realmente es una carpeta que existe físicamente en algun sitio... Mi host)
                    topic1
                    topic2
CONTENEDOR 1       /datos
IMAGEN KAFKA       /datos /bin    /opt     /tmp    /kafka/scripts
                                                   /kafka/config/server.properties
                                                   
                                                   
                                                   
----------------------------------------------------------------------

KAFKA CONNECT - Conectores que existen contra sistemas estandarizados


SQL   >>>  Mensajes  >>>> KAFKA
        PRODUCTOR JAVA     Solución BUENA √√√√√
    
    Creeis que somos la primera persona en el mundo en hacer eso? NO
    
SINK CONNECTOR      KAFKA  --(Transformaciones simples)-->   OTRO SITIO
SOURCE CONNECTOR    OTRO SITIO    --(Transformaciones simples)--> KAFKA

KAFKA CONNECTs: GRATIS O DE PAGO? Depende

Kafka es Opensource !!! 
    Apache Kafka SI    < FileSource   FileSink
    Confluent tiene una versión OS  <<<<  SQL, HDFS
              tiene otras que no.
              
              
KAFKA:
    REST <<<< Interactuar con  KAFKA
    
    Apache KAFKA la tiene solo para Connector, Stream
    
ConfluentKafka si tiene una interfaz REST PROPIA para KAFKA


------
connect-standalone

Levanta un servicio de Connect WORKER
    EJECUTA AHI EN TU DENTRO DE TI:
        DOS conector que te estoy configurando
            Entradas
            Salidas
        18083 <<< REST 