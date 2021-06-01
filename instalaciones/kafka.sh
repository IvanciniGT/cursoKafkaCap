#!/bin/bash

brokers=server
export PATH=$PATH:/home/ubuntu/environment/kafka/bin

function start(){
    for BROKER in $1
    do 
        echo arrancando broker $BROKER
        kafka-server-start.sh -daemon ~/environment/curso/instalaciones/kafka/$BROKER.properties 
    done
}
function stop(){
    for BROKER in $1
    do 
        echo parando broker $BROKER
        kafka-server-stop.sh -daemon ~/environment/curso/instalaciones/kafka/$BROKER.properties
    done
}

TO_EXEC="start"
while [[ $# > 0 ]]
do
    case "$1" in
        -s|--start)
            TO_EXEC=start
        ;;
        -q|--quit)
            TO_EXEC=stop
        ;;
        -b|--brokers)
            shift
            brokers="$1"
        ;;
        *)
            echo Opcion invalida. Se admiten los parametros --start, --quit, --brokers
            exit 1
    esac
    shift
done

$TO_EXEC "$brokers"