package RDD.pairRDD;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;

import scala.Tuple2;

public class App {
    public static void main(String[] args) {

        JavaSparkContext sc=new JavaSparkContext("local", "Map Func");
        JavaRDD<String> Raw_Data = sc.textFile("C:\\Users\\abc\\Desktop\\WorldCups.csv");
        System.out.println(Raw_Data.count());
        JavaRDD<CapModel> new_rdd = Raw_Data.map(new Function<String, CapModel>() {
            public CapModel call(String line) throws Exception { //lıne csv dosyasının ilk satırı
                String[] split = line.split(",");//veriyi virgullere gore parcalayacak dplit dizisinde tutacak
                //virgullere ayirdigimiza gore capmodele donusturebilirim zaen donus degerim cap model
                return new CapModel(split[0], split[1], split[2], split[3], split[4], split[5], Integer.parseInt(split[6]), Integer.parseInt(split[7]), Integer.parseInt(split[8]),split[9]);
            }
        });

        //first pair uruguay, 800.000
        //           brazil, 600.000
        JavaPairRDD<String, String> firstPair = new_rdd.mapToPair(new PairFunction<CapModel, String, String>() {
            public Tuple2<String, String> call(CapModel capModel) throws Exception {
                //capmodel adinda bir modelim var geri donus degerimde key-value pairs olacak
                return new Tuple2<String, String>(capModel.getBirinci(), capModel.getToplamKatilimci());
                //gettoplamkatilimci double oldu[u icin hata verdi o sebeble stringe donusturdum.

            }
            //giris degerim capmodel cikis degerlerim key ve value
            //1. takimlar key value de takimdaki kisi sayisi ona da string dedik .unku toplama falan yapmayacgiz.
        });
        firstPair.foreach(new VoidFunction<Tuple2<String, String>>() {
            public void call(Tuple2<String, String > line) throws Exception {
                System.out.println(line._1+"--------"+line._2);
                //ulkeler ------- katilimci sayisi
            }
        });
        JavaPairRDD<String, Iterable<String>> resultData = firstPair.groupByKey();
        //gruplayarak yazdiricak
        resultData.foreach(new VoidFunction<Tuple2<String, Iterable<String>>>() {
            public void call(Tuple2<String, Iterable<String>> line2) throws Exception {
                System.out.println(line2._1+"---"+line2._2);
                //ulkelere gore gruplayacak yani italya yazip tum value lari yanina yazacak.

            }
        });
    }
}
