package SparkSQL;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class multiline {
    public static void main(String[] args) {
        SparkSession sparkSession = SparkSession.builder().appName("firstSql").master("local").getOrCreate();
        /*
        asagidaki kod satir larini calistirinca bu sefer json dosyasinin icinde dizi oldugu icin ve dizimi farkli oldugu icin

        +--------------------+
|     _corrupt_record|
+--------------------+
|                  [{|
|    "name": "Step...|
|    "city": "Camb...|
|    "dateOfBirth"...|
|    "dateOfDeath"...|
|           "age": 76|
|                  },|
+--------------------+
only showing top 20 rows

sonucu bu sekilde verdi yani bolumleyemedi


*/
        // Dataset<Row> data = sparkSession.read().json("C:\\Users\\abc\\Desktop\\person_dizi.json");
        // data.show();

        Dataset<Row> data = sparkSession.read().option("multiline", true).json("C:\\Users\\abc\\Desktop\\person_dizi.json");
        data.show();
        //option foksiyonunu kkoyunca spark bunun bir dizi oldugunu anliyor

        /*
        +----+-----------+-----------+-----------+---------------+
        | age|       city|dateOfBirth|dateOfDeath|           name|
        +----+-----------+-----------+-----------+---------------+
        |  76|  Cambridge| 08/01/1942| 14/03/2018|Stephen Hawking|
        |null|        Ulm| 14/03/1879| 18/04/1955|Albert Einstein|
        |  84|Woolsthrope| 04/01/1643| 31/03/1727|   Isaac Newton|
        |null|   Sirakuza|       null|       null|        Arsimet|
        |  87|    Smiljan| 10/07/1856| 07/01/1943|   Nikole Tesla|
        |  47|   Pretoria| 28/06/1971|       null|      Elon Musk|
        |  56|  Cambridge| 24/02/1955| 05/10/2011|     Steve Jobs|
        */



    }
}
