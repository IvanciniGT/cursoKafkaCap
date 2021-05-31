#!/bin/bash

zookeepers=server
export PATH=$PATH:/home/ubuntu/environment/kafka/bin

function start(){
    for ZOOKEEPER in "$1"
    do 
        zookeeper-server-start.sh -daemon ~/environment/curso/instalaciones/zookeeper/$ZOOKEEPER.properties
    done
}
function stop(){
    for ZOOKEEPER in "$1"
    do 
        zookeeper-server-stop.sh -daemon ~/environment/curso/instalaciones/zookeeper/$ZOOKEEPER.properties
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
        -b|--zookeepers)
            shift
            zookeepers="$1"
        ;;
        *)
            echo Opcion invalida. Se admiten los parametros --start, --quit, --zookeepers
            exit 1
    esac
    shift
done

$TO_EXEC $zookeepers