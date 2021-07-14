package RDD.rddmapexam4;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.VoidFunction;

import java.util.Arrays;
import java.util.Iterator;

public class Flatmap {//flatmap birden fazla rdd olusturur.
    public static void main(String[] args) {
        JavaSparkContext sc=new JavaSparkContext("local", "Map Func");
        JavaRDD<String> Raw_Data = sc.textFile("C:\\Users\\abc\\Desktop\\WorldCups.csv");

        JavaRDD<String> flatMapRDD = Raw_Data.flatMap(new FlatMapFunction<String, String>() {
            public Iterator<String> call(String s) throws Exception {//s bir satirin hepsi yani linei temsil ediyor.
                //linedeki verileri virguller ile ayiriyoruz.
                return Arrays.asList(s.split(",")).iterator();//mapfunctionda model geri donmustuk burada array geri donuyoruz.

            }
        });
        flatMapRDD.foreach(new VoidFunction<String>() {
            public void call(String s) throws Exception {
                System.out.println(s);//tum elemanlari farkli rddlerde tutuyorum yani hepsini alt alta yazdirir.

            }
        });

    }
}
