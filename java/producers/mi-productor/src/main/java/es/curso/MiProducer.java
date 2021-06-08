package es.curso;

import java.util.Properties;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.KafkaProducer;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

public class MiProducer implements Callback{
    
    private Producer<String,String> producer;
    
    // Crear un productor
    private MiProducer(Properties configuracion){
        // Aqui nos estamos generando nuestro productor, según una configuración suministrada
        producer=new KafkaProducer<>(configuracion);
    }
        
    // enviarMensaje
    public void send(String topic, String key, String body){
        // Crear el mensaje que voy a mandar a Kafka
        ProducerRecord<String,String> mensaje = new ProducerRecord<>(topic, key, body);
        System.out.println("MANDANDO EL MENSAJE");
        System.out.println("   MENSAJE: " + key+" " +body);
        this.producer.send(mensaje, this);
    }
    
    public void onCompletion(RecordMetadata metadata, Exception exception){
        if (exception != null){
            System.out.println("NO SE HA PODIDO ENTREGAR EL MENSAJE");
            System.out.println("   MENSAJE: " + metadata);
            System.out.println("   ERROR:   " + exception.getMessage());
        }else{
            System.out.println("Se ha conseguido mansar el mensaje");
            System.out.println("   MENSAJE: " + metadata);
        }
        System.out.println("HILO:"+ Thread.currentThread().getName() );
    }
    
    //cargarnosUnProductor
    public void dispose(){
        producer.close();
    }

    public static MiProducer getProducer(){
        Properties config=new Properties();
        
        // Contra que cluster de Kafka voy a trabajar
        config.put( ProducerConfig.BOOTSTRAP_SERVERS_CONFIG , "localhost:9092" );
        
        // En cuantas replicas quiero que se ecriba el mensaje antes de llamar a mi función de callback
        config.put( ProducerConfig.ACKS_CONFIG , "1" ); // "all"
        
        // Serializado: Accion de convertir un objeto de JAVA en una secuencia de bytes
        // Quien se encarga de serializar las claves
        config.put( ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG , "org.apache.kafka.common.serialization.StringSerializer" );
        // Quien se encarga de serializar lel cuerpo del mensaje
        config.put( ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG , "org.apache.kafka.common.serialization.StringSerializer" );
        
        config.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, "es.curso.LogSeverityPartitioner");
        
        
        return new MiProducer(config);
    }
    
}

