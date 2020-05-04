
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.security.Timestamp;
import java.time.Duration;
import java.util.*;

public class Consumer {

    public static void main(String[] args) {

        // Properties
        Properties prop = new Properties();

        prop.put(ConsumerConfig.CLIENT_ID_CONFIG,"client-id");
        prop.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest"); // start offset 0
        prop.put(ConsumerConfig.GROUP_ID_CONFIG, "group-id");
        prop.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,"true");// consumer ack
        prop.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,new StringDeserializer().getClass().getName());
        prop.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,new StringDeserializer().getClass().getName());

        prop.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"ip-adress:9092");

        // Kafka Consume
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String,String>(prop);

        // consume topic
        consumer.subscribe(Arrays.asList("search-analysis"));


        while (true){
            ConsumerRecords<String, String> poll = consumer.poll(Duration.ofMillis(100));
            for(ConsumerRecord<String, String> rec : poll){
                System.out.println(rec.value());
                System.out.println(rec.offset());

            }
        }

    }



}
