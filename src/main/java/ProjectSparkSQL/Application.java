package ProjectSparkSQL;

import org.apache.spark.api.java.function.ForeachFunction;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class Application {
    public static void main(String[] args) {
        SparkSession sparkSession = SparkSession.builder().master("local").appName("Chicago-311-Spark-SQL-job").getOrCreate();

        /*baslik var demedigim icin yani option("header",true) eklemedigim  icin basliklarida data olarak kabul etti.
       +-------------------+---------------+-------------------+--------------------+--------------------+--------------------+----------------+--------------------+--------------------+--------+----------------+----------------+----+---------------+--------------+----+------------------+------------------+--------------------+
|                _c0|            _c1|                _c2|                 _c3|                 _c4|                 _c5|             _c6|                 _c7|                 _c8|     _c9|            _c10|            _c11|_c12|           _c13|          _c14|_c15|              _c16|              _c17|                _c18|
+-------------------+---------------+-------------------+--------------------+--------------------+--------------------+----------------+--------------------+--------------------+--------+----------------+----------------+----+---------------+--------------+----+------------------+------------------+--------------------+

|      Creation_Date|         Status|    Completion_Date|Service_Request_N...|Type_of_Service_R...|Number of Black C...|Current Activity|  Most Recent Action|      Street_Address|ZIP Code|    X Coordinate|    Y Coordinate|Ward|Police District|Community Area| SSA|          Latitude|         Longitude|            Location|
|2012-06-15T00:00:00|Completed - Dup|2012-08-27T00:00:00|         12-01089270|Garbage Cart Blac...|                null|            null|                null|    4942 W KINZIE ST|   60644|1143347.84997003|1902273.92031755|  28|             15|            25|null| 41.88797829529805|-87.74961410499051|{'longitude': '-8...|
|2012-06-15T00:00:00|Completed - Dup|2012-08-27T00:00:00|         12-01089274|Garbage Cart Blac...|                null|            null|                null|    4944 W KINZIE ST|   60644|1143334.44997003|1902273.72031755|  28|             15|            25|null|41.887977628170304|-87.74968498253848|{'longitude': '-8...|

       */
        //Dataset<Row> raw_data = sparkSession.read().csv("C:\\Users\\abc\\Desktop\\311chicago.csv");
       // raw_data.show();

        Dataset<Row> raw_data = sparkSession.read().option("header",true).csv("C:\\Users\\abc\\Desktop\\311chicago.csv");
        raw_data.show();
        Dataset<Row> selectdata = raw_data.select(new Column("Creation_Date"),
                new Column("Status"),
                new Column("Completion_Date"),
                new Column("Service_Request_Number"),
                new Column("Type_of_Service_Request"),
                new Column("Street_Address"));
        selectdata.show();
        //birden fazla arayan olmus mu
        Dataset<Row> numberCount = selectdata.groupBy(new Column("Service_Request_Number")).count();
        numberCount.show();
        numberCount.foreach(new ForeachFunction<Row>() {
            public void call(Row row) throws Exception {
                System.out.println(row.getString(0)+" "+row.getLong(1));
                //tum datayi dongu ile ekrana yazdirir.
                //show ilk 20 datayi gosteriyordu
            }
        });

        Dataset<Row> count5 = numberCount.filter(new Column("count").equalTo("5"));
        count5.show();
        Dataset<Row> specialNumberData = selectdata.filter(new Column("Service_Request_Number").equalTo("14-01783521"));
        specialNumberData.show();
    }
}
