package com.boot.rest.CreditCardManagement.service;

import com.boot.rest.CreditCardManagement.entity.Customer;
import com.boot.rest.CreditCardManagement.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Transaction> getTransaction(String gender, String category, String merchant, String city,
                                            String state, long populationMin, long populationMax,
                                            double amtMin, double amtMax){
        Query query = new Query();
        ArrayList<Criteria> list = new ArrayList<Criteria>();
        if(!(gender.equals(" "))){
            list.add(Criteria.where("gender").is(gender));
        }
        if(!(category.equals(" "))){
            list.add(Criteria.where("category").regex(".*?" + category +".*"));
        }
        if(!(merchant.equals(" "))){
            list.add(Criteria.where("merchant").regex(".*?" + merchant +".*"));
        }
        if(!(city.equals(" "))){
            list.add(Criteria.where("city").regex(".*?" + city +".*"));
        }
        if(!(state.equals(" "))){
            list.add(Criteria.where("state").regex(".*?" + state +".*"));
        }
        if(!(populationMin == -1)){
            //list.add(Criteria.where("city_population").is(cityPopulation));
            list.add(Criteria.where("city_population").gte(populationMin));
        }
        if(!(populationMax == -1)){
            //list.add(Criteria.where("city_population").is(cityPopulation));
            list.add(Criteria.where("city_population").lte(populationMax));
        }
        if(!(amtMin == -1)){
            list.add(Criteria.where("amt").gte(amtMin));
        }
        if(!(amtMax == -1)){
            list.add(Criteria.where("amt").lte(amtMax));
        }
        Criteria[] arr = new Criteria[list.size()];
        if(arr.length > 0){
            list.toArray(arr);
            Criteria criteria = new Criteria().andOperator(arr);
            query.addCriteria(criteria);
        }
        List<Transaction> transactions = mongoTemplate.find(query, Transaction.class, "transaction");
        return transactions;

    }

}
