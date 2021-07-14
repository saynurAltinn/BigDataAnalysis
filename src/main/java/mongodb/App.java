package mongodb;

import com.mongodb.*;

public class App {
    public static void main(String[] args) {
        MongoClient m1=new MongoClient("localhost",27017);
        DB database=m1.getDB("test");
        DBCollection collection=database.getCollection("user");
       /* //veri ekleme
       BasicDBObject obj=new BasicDBObject()
                .append("name", "Stephen Hawking")
                .append("date", "1942")
                .append("country","England")
                .append("dateofdeath","2018");

        BasicDBObject obj2=new BasicDBObject()
                .append("name","Isaac Newton")
                .append("date","1643")
                .append("country","England");

        WriteResult result=collection.insert(obj);
        WriteResult result2=collection.insert(obj2);

        System.out.println(result);
        System.out.println(result2); */

        /*//veri listeleme
        DBCursor list= collection.find(new BasicDBObject("name", "Albert Einstein"));
        while (list.hasNext()){//listin icindeki veriler bitene kadar while dongusu surekli olarak donecek
            System.out.println(list.next());//bir sonrakini surekli olarak ekrana yazdiracak
        } */

       /* //veri guncelleme
        BasicDBObject newobj=new BasicDBObject()
                .append("name","Stephen Hawking")
                .append("date","1941")
                .append("country","England")
                .append("dateofdeath","2018");
        collection.update(
                new BasicDBObject("date","1942"),
                new BasicDBObject("$set",newobj)
        ); */
        /* //collection drop
        collection.drop(); */
        //database drop
        database.dropDatabase();

    }
}
