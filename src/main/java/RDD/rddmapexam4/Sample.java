package RDD.rddmapexam4;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.VoidFunction;

public class Sample {
    public static void main(String[] args) {
        JavaSparkContext sc=new JavaSparkContext("local", "Map Func");
        JavaRDD<String> Raw_Data = sc.textFile("C:\\Users\\abc\\Desktop\\WorldCups.csv");

        JavaRDD<String> sampleRdd = Raw_Data.sample(false, 0.5);
        //false dersek ayni kayitlar gelebilir true ise ayni kayitlar gelemez.
        //2.argument ise kayitlarin yuzde kacini istiyorsun.
        sampleRdd.foreach(new VoidFunction<String>() {
            public void call(String s) throws Exception {
                System.out.println(s);
            }
        });
    }
}
