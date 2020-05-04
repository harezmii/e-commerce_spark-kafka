package temp;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.Scanner;

public class kafkaProducer {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String server_ipadress = "136.244.110.112:9092";
        Properties properties = new Properties();

        properties.put(ProducerConfig.CLIENT_ID_CONFIG,"test-deneme");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,new StringSerializer().getClass().getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,new StringSerializer().getClass().getName());
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,server_ipadress);

        Producer producer = new KafkaProducer<String,String>(properties);

        while (true){
            String input = scanner.next();
            producer.send(new ProducerRecord("test",input));
        }



    }
}
