package app;


import com.google.common.collect.Iterators;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import scala.Tuple2;

import java.util.Iterator;

public class App {
    public static void main(String[] args) {
        System.setProperty("hadoop.home.dir", "C:\\winutils");

        JavaSparkContext sc = new JavaSparkContext("local", "worldCupApp");
        JavaRDD<String> Raw_Data = sc.textFile("C:\\Users\\abc\\Desktop\\WorldCupPlayers.csv");
        System.out.println(Raw_Data.count());

        JavaRDD<PlayersModel> playersRDD = Raw_Data.map(new Function<String,/*Object*/ PlayersModel>() {
            public PlayersModel call(String line) throws Exception {
                String[] dizi = line.split(",",-1);//arayofbound hatasi verdi yani dizi tasti bunun icin limit -1 verdik
                //virgullere gore ayirip bunu bir diziye atti.

                return new PlayersModel(dizi[0], dizi[1], dizi[2], dizi[3], dizi[4], dizi[5], dizi[6], dizi[7], dizi[8]);
            }
        });

        playersRDD.foreach(new VoidFunction<PlayersModel>() {
            public void call(PlayersModel playersModel) throws Exception {

                System.out.println(playersModel.playerName);
            }
        });
        //messi dunya kupasinda kac mac yapti
        //sadece ismi messi olan verileri messi rddye ekledik.
        JavaRDD<PlayersModel> messiRDD = playersRDD.filter(new Function<PlayersModel, Boolean>() {
            public Boolean call(PlayersModel playersModel) throws Exception {
                return playersModel.getPlayerName().equals("MESSI");
            }
        });
        System.out.println("messi dunya kupalarinda  "+ messiRDD.count()+ "  mac yapti" );

       //kim kac mac yapti
        //bunun icin filter methodunu secemeyiz cunku messi ya da emre diyip bir seyi filtrelemiyoruz.

        //mapRDD nin icerisinde futbolcu isimleri ve mac idleri var ama ben macin countunu istiyorum
        JavaPairRDD<String, String> mapRDD = playersRDD.mapToPair(new PairFunction<PlayersModel, String, String>() {
            //giris degerim player models cikis degerlerim futbolcu adi ve mac sayisi olmak uzere String string
            public Tuple2<String, String> call(PlayersModel playersModel) throws Exception {
                return new Tuple2<String, String>(playersModel.getPlayerName(), playersModel.getMatchID());
            }
        });

        mapRDD.foreach(new VoidFunction<Tuple2<String, String>>() {
            public void call(Tuple2<String, String> line) throws Exception {
                System.out.println(line._1+" "+line._2);//tuple oldugu icin bu sekilde yaziyoruz.
            }
        });

        //keylere gore grupluyor
        //JavaPairRDD<String, String> mapRDD yukarda mapRDDyi olusturmak icin yazdigimiz satir.
        //ilki key ikincisi value yani keylere(futbolcu Adlarina gore) gore grupluyor.
        JavaPairRDD<String, Iterable<String>> groupPlayer = mapRDD.groupByKey();
        groupPlayer.foreach(new VoidFunction<Tuple2<String, Iterable<String>>>() {
            public void call(Tuple2<String, Iterable<String>> line) throws Exception {
                System.out.println(line._1+" "+line._2);

            }
        });
        //giris degeri tuple cikis degeri obje onun icin yeni bir class olusturalim.

        //app.groupPlayer=object
        JavaRDD<app.groupPlayer> resultRDD = groupPlayer.map(new Function<Tuple2<String, Iterable<String>>, app.groupPlayer>() {
            public app.groupPlayer call(Tuple2<String, Iterable<String>> dizi) throws Exception {
                Iterator<String> iteratorraw = dizi._2.iterator();
                int size = Iterators.size(iteratorraw);
                return new groupPlayer(dizi._1, size);
            }
        });
        
       resultRDD.foreach(new VoidFunction<app.groupPlayer>() {
           public void call(app.groupPlayer groupPlayer) throws Exception {
               System.out.println(groupPlayer.getPlayerName()+ " " + groupPlayer.getMatchCount());
           }
       });

    }
}
