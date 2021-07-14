package RDD.rddmapexam4;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class Union {
    public static void main(String[] args) {
        JavaSparkContext sc=new JavaSparkContext("local", "Map Func");
        JavaRDD<String> firstData = sc.textFile("C:\\Users\\abc\\Desktop\\WorldCups.csv");
        JavaRDD<String> secondData = sc.textFile("C:\\Users\\abc\\Desktop\\WorldCupPlayers.csv");
        System.out.println("1. Rdd"+ "  "+firstData.count());
        System.out.println("2. Rdd"+"  "+secondData.count());
        JavaRDD<String> resultRDD = firstData.union(secondData);
        //iki datayi birlestirip result rddyi olusturdu.
        System.out.println("3. Rdd"+"  "+resultRDD.count());




    }
}
