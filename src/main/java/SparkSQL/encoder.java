package SparkSQL;
import org.apache.spark.api.java.function.ForeachFunction;
import org.apache.spark.sql.*;

public class encoder {
    public static void main(String[] args) {
        SparkSession sparkSession = SparkSession.builder().appName("firstSql").master("local").getOrCreate();
        Encoder<person> personEncoder = Encoders.bean(person.class);
        Dataset<person> data = sparkSession.read().json("C:\\Users\\abc\\Desktop\\person.json").as(personEncoder);
        //person.jsoni okuyacagiz icindeki colon isimleri person encoder modelinin icerisinde barinmis.
        //.as(personEncoder) ile dogrudan oku ve bana raw degilde person adinda ver
        //onceden asencoder yerine map split falan kullaniyorduk.ve cok daha uzun oluyordu.
        data.foreach(kv -> {
            System.out.println(kv.getAge()+"  "+kv.getCity());
        });

    }
}