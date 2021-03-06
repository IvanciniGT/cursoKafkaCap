package es.curso;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Produced;

import java.util.Arrays;
import java.util.Properties;
import java.util.regex.Pattern;
import java.text.Normalizer;

/*                                           productor
    TOPIC   >>>>  KAFKA STREAM (Processamiento) >>>> TOPIC
           consumidor

    mensajes (TEXTOS)  ----> Contar las veces que aparece cada palabra --->

*/


public class MiKafkaStream{

    private static String bootstrapServers = "localhost:9092";
    private static String TMP_DIR="/tmp";
    public static void main(String[] args) throws Exception {

        String inputTopic = args[0];

        // Configuramos el stream
        Properties streamsConfiguration = new Properties();
        streamsConfiguration.put(StreamsConfig.APPLICATION_ID_CONFIG, "contador2");
        streamsConfiguration.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        streamsConfiguration.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        streamsConfiguration.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        streamsConfiguration.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 1000);
        streamsConfiguration.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        streamsConfiguration.put(StreamsConfig.STATE_DIR_CONFIG, "/tmp/contador");


        // Creación de Stream
        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, String> textLines = builder.stream(inputTopic);
        Pattern pattern = Pattern.compile("\\W+", Pattern.UNICODE_CHARACTER_CLASS);

        // \w son caracters habituales en palabras: a L á ñ
        // \W caracteres que no son de palabra \s (  \t \n) , . - ()
        KTable<String, Long> wordCounts = textLines
                .mapValues((key,value) -> value.toUpperCase())
                .mapValues(MiKafkaStream::normalizarTexto)
                .flatMapValues(value -> Arrays.asList(pattern.split(value)) // Declarando que quiero esa operacion
                .groupBy((key, word) -> word)                                              // Declarando que quiero esa operación
                .count();                                                                  // Decir que me haga esa operación y todas las anteriores

        wordCounts.toStream().foreach((word, count) -> System.out.println("word: " + word + " -> " + count));

        // Configuramos la salida
        String outputTopic = "topic_salida";
        final Serde<String> stringSerde = Serdes.String();
        final Serde<Long> longSerde = Serdes.Long();
        //wordCounts.to(stringSerde, longSerde, outputTopic);
        wordCounts.toStream().to(outputTopic, Produced.with(stringSerde, longSerde).as("output"));
        
        // Arrancamos
        KafkaStreams streams = new KafkaStreams(builder.build(), streamsConfiguration);
        streams.start();

        // Controlar cuando parar mediante un listener: setStateListener(KafkaStreams.StateListener listener)
        try{
            while(true)
                Thread.sleep(30000);
        }catch(Exception e){}
        streams.close();
    }
    
    private static final String normalizarTexto(String key ,String value){
        value = Normalizer.normalize(value, Normalizer.Form.NFD);
        value = value.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return value;
    }
}
