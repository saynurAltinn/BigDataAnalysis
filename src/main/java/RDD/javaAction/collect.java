package RDD.javaAction;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.List;

public class collect {
    public static void main(String[] args) {
        JavaSparkContext sc=new JavaSparkContext("local", "Map Func");
        JavaRDD<String> Raw_Data = sc.textFile("C:\\Users\\abc\\Desktop\\WorldCups.csv");
        System.out.println(Raw_Data.count());
        System.out.println(Raw_Data.first());//ilk veri

        List<String> collectList = Raw_Data.collect();
        //clusterdaki tum veriyi spark conntexte cekti.
        //bunu liste olarak cekti sonrada listeyi dongu icinde yazdirdik.
        for (String x: collectList)
            System.out.println(x);

        List<String> takeList = Raw_Data.take(4);
        //transformation metodu degil onun icin rdd ye cevirmiyor.
        for (String y: takeList)//ilk 4 kaydi getirir.
            System.out.println(y);

        List<String> strings = Raw_Data.takeSample(false, 4);
        for (String z: strings)//rastgele 4 kayit getiririr.
            System.out.println(z);
    }
}
