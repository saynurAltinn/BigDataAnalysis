package kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by tkilic on 28.05.2018.
 */
public class ProducerExample {
    public static void main(String[] args) throws InterruptedException {
        String topicName="search";
        String[] dizi = {"canta","cuzdan","ayakkabi","kalem","anahtar"}; //birden fazla veri gondermek icin
        Properties configPro = new Properties();
        configPro.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        //kafkanin yolunu belirtiyoruz.
        configPro.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.ByteArraySerializer");
        configPro.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        //kafka verileri seriliaze sekilde alir stringleri seriliaze ediyoruz yani 1010100000 (byte) donusturuyoruz
        Producer producer=new KafkaProducer<String,String>(configPro);//hangi konfigurasyon ayarlarini yapacagimizi belirttik

            ProducerRecord<String, String> rec = new ProducerRecord<String, String>
                    (topicName, "ayakkabi");//verilerimizi gondermek icin
            producer.send(rec);//gonderiyoruz
            producer.close();

            /*
            *   Producer producer=new KafkaProducer<String,String>(configPro);
        for(int i=0;i<dizi.length;i++) {
            ProducerRecord<String, String> rec = new ProducerRecord<String, String>
                    (topicName, dizi[i]); //tek tek verileri gonderiyor
            producer.send(rec);
            TimeUnit.SECONDS.sleep(2); 2 //saniye bekliyor
        }
        producer.close();
            * */


    }
}

