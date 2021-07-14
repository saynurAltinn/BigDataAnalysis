package SparkSQL;

import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import scala.collection.immutable.$colon$colon;

public class groupBy {
    public static void main(String[] args) {
        SparkSession sparkSession = SparkSession.builder().appName("firstSql").master("local").getOrCreate();
        Dataset<Row> data = sparkSession.read().json("C:\\Users\\abc\\Desktop\\person.json");
        Dataset<Row> dataCity = data.groupBy(new Column("city")).count();
        Dataset<Row> dataAvg = data.groupBy(new Column("city")).avg("age");
        dataCity.show();
        dataAvg.show();
        data.groupBy(new Column("name")).sum("age").show();

    }
}

