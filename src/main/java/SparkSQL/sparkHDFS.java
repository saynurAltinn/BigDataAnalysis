package SparkSQL;

import org.apache.spark.sql.*;

public class sparkHDFS {
    public static void main(String[] args) {
        SparkSession sparkSession = SparkSession.builder().appName("firstSql").master("local").getOrCreate();
        Encoder<movieModel> movieModelEncoder = Encoders.bean(movieModel.class);
        Dataset<movieModel> data = sparkSession.read().option("infershema", true).option("header", true).csv("hdfs://localhost:8020/data/ratings.csv").as(movieModelEncoder);
        //csv oldugu icin optionlara ihtiyacimiz var.
        System.out.println(data.count());
        data.printSchema();//kolonlari gosterir.
        data.foreach(modeldata -> {
            System.out.println(modeldata.getMovieId()+"  "+modeldata.getRatings());
        });

        Dataset<movieModel> maxRatings = data.filter(new Column("ratings").equalTo("5.0"));
        maxRatings.show();
        Dataset<Row> groupMovieId = data.groupBy(new Column("movieId")).count();
        groupMovieId.show();
        groupMovieId.write().format("csv").save("hdfs://localhost:8020/data/groupMovieData.csv");
        //analizi hadooptan yapmamiz zaman alirdi sparm ram uzerinde calistigi icin hizli ama depolama birimi yok onun icin hdfs kullandik.


    }
}