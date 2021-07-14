package SparkSQL;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class sqlScript {
    public static void main(String[] args) {
        SparkSession sparkSession = SparkSession.builder().appName("firstSql").master("local").getOrCreate();
        Dataset<Row> data = sparkSession.read().json("C:\\Users\\abc\\Desktop\\person.json");
        data.createOrReplaceTempView("person");
        //view olusturduk
        Dataset<Row> dataSQL = sparkSession.sql("select * from person");//hepsini alir
        dataSQL.show();
        sparkSession.sql("select name,city from person").show();//sadece name ve cityleri alir
        sparkSession.sql("select city,avg(age) from person group by city").show();

    }
}
