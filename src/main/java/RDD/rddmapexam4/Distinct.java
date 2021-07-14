package RDD.rddmapexam4;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class Distinct {//ayni olan verileri temizler teke dusurur.
     public static void main(String[] args) {
        JavaSparkContext sc=new JavaSparkContext("local", "Map Func");
        JavaRDD<String> Raw_Data = sc.textFile("C:\\Users\\abc\\Desktop\\WorldCups.csv");
        System.out.println(Raw_Data.count());
        JavaRDD<String> distinctRDD = Raw_Data.distinct();
        System.out.println("sonuc.........."+ distinctRDD.count());
    }
}
