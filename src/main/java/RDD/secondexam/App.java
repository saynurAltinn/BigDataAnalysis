package RDD.secondexam;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) {
        JavaSparkContext cont = new JavaSparkContext("local", "secondexam");
        List<String> exam= Arrays.asList("Albert Einstein", "Stephen Hawking", "Isaac Newton");

        JavaRDD<String> firstpr = cont.parallelize(exam);

        System.out.println(firstpr.count());

    }
}
