package temp;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Arrays;
import java.util.Properties;

public class kafkaConsumer {

    public static void main(String[] args) {

        Properties properties = new Properties();


        properties.put(ConsumerConfig.CLIENT_ID_CONFIG,"test-deneme");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,new StringDeserializer().getClass().getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,new StringDeserializer().getClass().getName());
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"136.244.110.112:9092");

        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"dene");
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,"true");

        KafkaConsumer consumer = new KafkaConsumer<String,String>(properties);

        consumer.subscribe(Arrays.asList("test"));
/*
        while (true){
           // ConsumerRecords<String,String> consumerRecords = consumer.poll(Duration.ZERO);
            for(ConsumerRecord<String, String> rec : consumerRecords){
                System.out.println(rec.value());
                System.out.println(rec.offset());
            }
        }

 */

    }
}
