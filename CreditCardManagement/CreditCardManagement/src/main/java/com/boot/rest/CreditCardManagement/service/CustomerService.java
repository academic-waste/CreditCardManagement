package com.boot.rest.CreditCardManagement.service;

import com.boot.rest.CreditCardManagement.dao.CustomerRepository;
import com.boot.rest.CreditCardManagement.entity.Customer;
import com.boot.rest.CreditCardManagement.exception.RecordNotFoundException;
import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

//    public void deleteCustomerById(long cusId) throws RecordNotFoundException {
//        //if(this.customerRepository.existsById(cusId))
//        if(this.customerRepository.findById(cusId).isEmpty()){
//            throw new RecordNotFoundException("customer with "+cusId+" does not exist");
//        }else{
//            System.out.println("customer: " + customerRepository.findById(cusId) + " deleted");
//            this.customerRepository.deleteById(cusId);
//        }
//
//    }

    public long deleteCustomer(long cusId) throws RecordNotFoundException {
//        if(mongoTemplate.find(new Query().addCriteria(Criteria.where("customer_id").is(cusId)),Customer.class).isEmpty()){
//            throw new RecordNotFoundException("customer with "+cusId+" does not exist");
//              Query query = new Query();
//              query.fields().include(field);
//              query.addCriteria(new Criteria(filed).exists(true));
//        }else{
//            Query query = new Query();
//            mongoTemplate.remove(query.addCriteria(Criteria.where("customer_id").is(cusId)), Customer.class);
//            System.out.println("customer: " + cusId + " deleted");
//            return cusId;
//        }
        Query query = new Query();
        mongoTemplate.remove(query.addCriteria(Criteria.where("customer_id").is(cusId)), Customer.class);
        System.out.println("customer: " + cusId + " deleted");
        return cusId;


    }

    public long deleteCustomerUseMongoRepo(long cusId){
        this.customerRepository.deleteByCustomerId(cusId);
        return cusId;
    }

    public Customer insertCustomer(String first, String last, String gender, String job, Date dob, long customerId){
        Customer customer=new Customer();
        customer.setFirst(first);
        customer.setLast(last);
        customer.setGender(gender);
        customer.setJob(job);
        customer.setDob(dob);
        customer.setCustomerId(customerId);
        return customerRepository.insert(customer);

    }

//    public Optional<Customer> findCustomerById(long cusId){
//        return customerRepository.findById(cusId);
//    }

    public List<Customer> getAllCustomer(){
        return customerRepository.findAll();
    }
}
