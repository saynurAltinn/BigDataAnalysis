package streaming;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.streaming.StreamingQuery;
import org.apache.spark.sql.streaming.StreamingQueryException;
import org.apache.spark.sql.types.StructType;

public class IOTweather {
    public static void main(String[] args) throws StreamingQueryException {
        SparkSession sparkSession = SparkSession.builder().appName("iot weather").master("local").getOrCreate();
        StructType structType = new StructType().add("quarter","string").add("heatType","string")
                .add("heat","integer").add("windType","string")
                .add("wind","integer");
        Dataset<Row> dataset = sparkSession.readStream().schema(structType).option("sep", ",").csv("C:\\Users\\abc\\Desktop\\files\\sparkstreaming\\*");
        //sema structType okumayi buna gore yapacak ilk gelen heat type iinci heat ucuncu windtype 4.wind
        //optionda virgullere gore ayirdim.
        //yildiz ile spark streaming icinde dusen her seyi realtime olarak analiz eder
        Dataset<Row> heatData = dataset.select("quarter", "heat").where("heat>10");
        StreamingQuery start = heatData.writeStream().format("console").start();
        //formattan once outputmodea complete yazdigimda hata verdi silince calisti ona bir bak
        start.awaitTermination();


    }
}
