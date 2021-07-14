package SparkSQL;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.RelationalGroupedDataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class App {
    public static void main(String[] args) {
        SparkSession sparkSession = SparkSession.builder().appName("firstSql").master("local").getOrCreate();

        Dataset<Row> data = sparkSession.read().json("C:\\Users\\abc\\Desktop\\person.json");

        data.show();
        data.printSchema();
        Dataset<Row> nameAgeData = data.select("name", "age");
        nameAgeData.show();
       // RelationalGroupedDataset groupedDataset = data.groupBy("city"); nesne
        Dataset<Row> groupData = data.groupBy("city").sum("age"); //data
        groupData.show();


    }
}
