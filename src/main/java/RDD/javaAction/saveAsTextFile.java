package RDD.javaAction;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.VoidFunction;

public class saveAsTextFile {
    public static void main(String[] args) {
        JavaSparkContext sc=new JavaSparkContext("local", "Map Func");
        JavaRDD<String> Raw_Data = sc.textFile("C:\\Users\\abc\\Desktop\\WorldCups.csv");
        System.out.println(Raw_Data.count());
        JavaRDD<CapModel> new_rdd = Raw_Data.map(new Function<String, CapModel>() {
            public CapModel call(String line) throws Exception { //lıne csv dosyasının ilk satırı
                String[] split = line.split(",");//veriyi virgullere gore parcalayacak dplit dizisinde tutacak
                //virgullere ayirdigimiza gore capmodele donusturebilirim zaen donus degerim cap model
                return new CapModel(split[0], split[1], split[2], split[3], split[4], split[5], Integer.parseInt(split[6]), Integer.parseInt(split[7]), Integer.parseInt(split[8]), split[9]);
            }
        });

        JavaRDD<CapModel> italy = new_rdd.filter(new Function<CapModel, Boolean>() {//giris degerim capmodel donus degerim boolean
            public Boolean call(CapModel capModel) throws Exception {
                return capModel.getBirinci().equals("Italy");//birinci olanlardan italya olanlar icin yeni dataset olusturdu.
            }
        });

        JavaRDD<String> result = italy.map(new Function<CapModel, String>() {
                                                                 //String olarak kaydedicek

            public String call(CapModel capModel) throws Exception {
                return capModel.getYil()+ " "+capModel.getBirinci();
            }
        });
        result.saveAsTextFile("C:\\Users\\abc\\Desktop\\birinci\\");
        //masaustune birinci adli dosya icinde verileri stirng olarak kaydetti

    }
}
