package streaming;

import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.sql.*;
import org.apache.spark.sql.streaming.StreamingQuery;
import org.apache.spark.sql.streaming.StreamingQueryException;

import java.util.Arrays;
import java.util.Iterator;

public class MessageListener {
    public static void main(String[] args) throws StreamingQueryException {
        SparkSession sparkSession = SparkSession.builder().appName("Messagge Listener").master("local").getOrCreate();

        Dataset<Row> dataset = sparkSession.readStream().format("socket").option("host", "localhost").option("port", "8005").load();
        Dataset<String> data = dataset.as(Encoders.STRING());

        //bosluga gore kelimelleri almaliyiz.
        Dataset<String> stringDataset = data.flatMap(new FlatMapFunction<String, String>() {
            public Iterator<String> call(String s) throws Exception {
                return Arrays.asList(s.split(" ")).iterator();
            }
        }, Encoders.STRING());

        Dataset<Row> groupedData = stringDataset.groupBy("value").count();
        StreamingQuery start = groupedData.writeStream().outputMode("complete").format("console").start();

        start.awaitTermination();//program kapanana kadar ekrana yazdirir.


    }
}