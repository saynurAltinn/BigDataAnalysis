package kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;

import java.util.Arrays;
import java.util.Properties;

public class ConsumerExample {
    public static void main(String[] args) {
        String topicName="search";

        Properties configPro=new Properties();
        configPro.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        configPro.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");
        configPro.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");
        configPro.put(ConsumerConfig.GROUP_ID_CONFIG,"saynur");
        configPro.put(ConsumerConfig.CLIENT_ID_CONFIG,"exam2");


        //configpro ayarlarina gore kafka consumerini ayaga kaldiriyoruz
        KafkaConsumer<String,String> kafkaConsumer;
        kafkaConsumer = new KafkaConsumer<String, String>(configPro);

        kafkaConsumer.subscribe(Arrays.asList(topicName));
        try {
            while (true) {
                ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
                for (ConsumerRecord<String, String> record : records)
                    System.out.println(record.value());
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        kafkaConsumer.close();


    }
}
