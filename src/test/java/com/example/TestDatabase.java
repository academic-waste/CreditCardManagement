package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * test whether connect the database Projectdata
 */
public class TestDatabase extends CreditCardChallengeApplicationTests{
    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    public void testCreateCollection(){
        boolean aa=mongoTemplate.collectionExists("customer");
        if(aa){
            System.out.println("Connection Success!");
        }
    }
}
