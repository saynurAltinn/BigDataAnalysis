package RDD;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class App {
    public static void main(String[] args) {
        JavaSparkContext cont=new JavaSparkContext("local", "First Exam");
        JavaRDD<String> wordrdd =  cont.textFile("C:\\Users\\abc\\Desktop\\ilkdataset.txt");
        System.out.println(wordrdd.count());//ka. satir oldugunu yazdiracak
        System.out.println(wordrdd.first());
    }
}
