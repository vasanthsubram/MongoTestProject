package app;

import java.net.UnknownHostException;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.MongoClient;
import static config.SpringMongoDBConfiguration.*;

public class SpringDataMongoDBMain {
    
	
    public static void main(String[] args) {
        try {
            MongoClient mongo = new MongoClient(MONGO_HOST, MONGO_PORT);
            MongoOperations mongoOps = new MongoTemplate(mongo, DB_NAME);
            Person p = new Person(null, "Bond", "Bangalore, India");
            mongoOps.insert(p, PERSON_COLLECTION);
 
            Person p1 = mongoOps.findOne(
                    new Query(Criteria.where("name").is("PankajKr")),
                    Person.class, PERSON_COLLECTION);
 
            System.out.println(p1);
            
           Query searchUserQuery = new Query(Criteria.where("name").is("Bond"));
            
           long count = mongoOps.count(searchUserQuery, PERSON_COLLECTION);
           System.out.println("Number of objects = " + count);
           
           
           mongoOps.remove(searchUserQuery, app.Person.class);
           
           count = mongoOps.count(searchUserQuery, PERSON_COLLECTION);
           System.out.println("Number of objects = " + count);
           
           
//            mongoOps.dropCollection(PERSON_COLLECTION);
            mongo.close();
             
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
 
}