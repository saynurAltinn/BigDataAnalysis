package SparkSQL;

import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class filter {
    public static void main(String[] args) {
        SparkSession sparkSession = SparkSession.builder().appName("firstSql").master("local").getOrCreate();
        Dataset<Row> data = sparkSession.read().option("multiline", true).json("C:\\Users\\abc\\Desktop\\person_dizi.json");
        Dataset<Row> dataStephen = data.filter(new Column("name").equalTo("Stephen Hawking"));
        dataStephen.show();

        Dataset<Row> dataCont = data.filter(new Column("age").contains("7"));
        //age icerisinde 7 gecenleri verdi.
        dataCont.show();
    }
}
