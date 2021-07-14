package RDD.rddmapexam4;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

public class lazy {
    public static void main(String[] args) {
        JavaSparkContext sc=new JavaSparkContext("local", "Map Func");
        JavaRDD<String> Raw_Data = sc.textFile("C:\\Users\\abc\\Desktop\\WorldCups.csv");
        System.out.println(Raw_Data.count());
        final JavaRDD<CapModel> new_rdd = Raw_Data.map(new Function<String, CapModel>() {
            public CapModel call(String line) throws Exception { //lıne csv dosyasının ilk satırı
                String[] split = line.split(",");//veriyi virgullere gore parcalayacak dplit dizisinde tutacak
                //virgullere ayirdigimiza gore capmodele donusturebilirim zaen donus degerim cap model
                System.out.println(line);
                //action metodu olmasaydi transformation oldugu icin linei yazdirmayacakti
                //asagida count action metodunu kullandi[imiz zaman calisacak.
                return new CapModel(split[0], split[1], split[2], split[3], split[4], split[5], Integer.parseInt(split[6]), Integer.parseInt(split[7]), Integer.parseInt(split[8]), split[9]);

            }
        });

        System.out.println(new_rdd.count());


    }
}
