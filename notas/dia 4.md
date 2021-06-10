                        RANGEASSIGNOR
TOPIC 1     TOPIC 2          
--------------------------------------
0               0             C1
--------------------------------------
1               1             C2
--------------------------------------
2               2             C1
--------------------------------------




KEY >>>> HASH % # particiones

MICLAVE > 23  % diferente


Cada cuanto debo hacer un poll para que no considere que estoy fallido
max.poll.interval.ms

                     poll
CONSUMIDOR  (API)   >>>>>>>        KAFKA
                    <<<<<<<
Yo hago mi trabajo

            
                    heartbeat.interval.ms 
            HEARTBEAT  ---------------->
                       <---------------  OK RECIBIDO
                      session.timeout.ms
                      
                      
                      
broker 1    TOPIC 1 P1*      CONSUIDOR >>>> P2
    v^
broker 2    TOPIC 1 P2*
    v^
broker 3    TOPIC 1 P3*
broker 4    
Actualización de offsets
    >>>>
        Actualización de mensajes
        
        
    
POLL    
2000 OK PROCESO COMMIT 
2001 OK PROCESO CASCO <<<
2002 OK PROCESO COMMIT


--------------------------------------------------

PARADIGMAS COMUNES DE PROGRAMACIÓN
----------------------------------
PROGRAMACION IMPERATIVA <<<< BASIC
    Ejecutar ordenes de forma secuencial
        IF ... FOR ... WHILE ... CASE ... GOTO

PROGRAMACION PROCEDURAL <<<<< C
    Ejecutar/Definir procedimientos o funciones, métodos
        function ... call

PROGRAMACION FUNCIONAL ..... JAVA..... 1.8
    Las funciones son ciudadanos de primer orden <<< Puedo referenciar una función a través de una variable.
    
    mifuncion (arg1, arg2, arg3)
        Podemos pasar funciones como argumentos a otras funciones
        Podemos devolver una función desde otra función

PROGRAMACION ORIENTADA A OBJETOS < JAVA

Algoritmos      MAP... REDUCE








mkdir -p ~/.config
docker run -it --name code-server -p 127.0.0.1:8080:8080 \
  -v "$HOME/.config:/home/coder/.config" \
  -v "$PWD:/home/coder/project" \
  -u "$(id -u):$(id -g)" \
  -e "DOCKER_USER=$USER" -e "PASSWORD=1234" \
  codercom/code-server:latest
  
  
  
  
textos
    upperCase                  <<  map
    normalizarTexto: á -> a     << map
    normalizarTexto: É -> E
    split 
    Agrupando por valor
    Contar
    
Si la palabra está en una lista de palabras (HOLA)  no la quiero: en cualquier variante: hola Hola      <<<< filter



TEXTOS               CUENTAS
P0      <<< S0 C1    
P1      <<< S1 C2  >>>    P0    <<< S0
P2      <<< S2 C3*
