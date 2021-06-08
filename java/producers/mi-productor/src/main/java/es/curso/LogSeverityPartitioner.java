package es.curso;

import java.util.Map;

import org.apache.kafka.common.Cluster;
import org.apache.kafka.clients.producer.Partitioner;

public class LogSeverityPartitioner implements Partitioner{
    public int partition(String topic,
              Object key,
              byte[] keyBytes,
              Object value,
              byte[] valueBytes,
              Cluster cluster){

        int numeroParticiones= cluster.partitionCountForTopic(topic);
        
        int toRet=0;
    
        switch( value.toString().split(" ")[0] ){
            case "WARNING":
                toRet=1;
                break;
            case "ERROR":
                toRet=2;
                break;
        }
        if(toRet >= numeroParticiones ) toRet=0;
        return toRet;
    }
    
    /*
    Particion 0 - INFO y similares
    Particion 1 - WARNING
    Particion 2 - ERROR
    */
    
    public void close(){}
    public void configure(Map<String,?> configs){}

}