package com.boot.rest.CreditCardManagement.dao;

import com.boot.rest.CreditCardManagement.entity.Customer;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CustomerRepository extends MongoRepository<Customer, Long> {
    List<Customer> findAll();
//    @Query("{'customerId':?0}")
//    void deleteByCustomerId(long customerId);

}
