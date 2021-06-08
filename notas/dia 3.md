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