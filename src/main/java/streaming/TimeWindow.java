package streaming;

import org.apache.spark.sql.*;
import org.apache.spark.sql.streaming.StreamingQuery;
import org.apache.spark.sql.streaming.StreamingQueryException;

public class TimeWindow {
    public static void main(String[] args) throws StreamingQueryException {
        SparkSession sparkSession = SparkSession.builder().appName("Messagge Listener").master("local").getOrCreate();
        Dataset<Row> dataset = sparkSession.readStream().format("socket").option("host", "localhost").option("port", "8005").option("includeTimestamp",true).load();
        Dataset<Row> products = dataset.as(Encoders.tuple(Encoders.STRING(), Encoders.TIMESTAMP())).toDF("product", "timestamp");//toDF ile stringi ve timestampi adlandirdim.
        Dataset<Row> resultData = products.groupBy(functions.window(products.col("timestamp"), "1 minute"), products.col("product")).count().orderBy("window");
        StreamingQuery start = resultData.writeStream().outputMode("complete").format("console").option("truncate", "false").start();
        start.awaitTermination();


    }
}
