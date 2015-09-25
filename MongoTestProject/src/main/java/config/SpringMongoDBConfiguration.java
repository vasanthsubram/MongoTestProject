package config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClient;


@Configuration
public class SpringMongoDBConfiguration {

    public static final String DB_NAME = "mydb";
    public static final String PERSON_COLLECTION = "app.Person";
    public static final String MONGO_HOST = "localhost";
    public static final int MONGO_PORT = 27017;
    
    public @Bean MongoDbFactory getMongoDbFactory() throws Exception {
        return new SimpleMongoDbFactory(new MongoClient(MONGO_HOST,MONGO_PORT), DB_NAME);
    }
 
    public @Bean MongoTemplate mongoOps() throws Exception {
        MongoTemplate mongoTemplate = new MongoTemplate(getMongoDbFactory());
        return mongoTemplate;
    }
}