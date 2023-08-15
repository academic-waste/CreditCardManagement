package com.boot.rest.CreditCardManagement.dao;

import com.boot.rest.CreditCardManagement.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CustomerRepository extends MongoRepository<Customer, Long> {
    List<Customer> findAll();

}
