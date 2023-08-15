package com.boot.rest.CreditCardManagement.dao;

import com.boot.rest.CreditCardManagement.entity.Customer;
import com.boot.rest.CreditCardManagement.entity.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TranscationRepository extends MongoRepository<Transaction, Long> {
    List<Transaction> findAll();
}
