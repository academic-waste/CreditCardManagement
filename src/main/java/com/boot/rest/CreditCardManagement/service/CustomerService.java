package com.boot.rest.CreditCardManagement.service;

import com.boot.rest.CreditCardManagement.dao.CustomerRepository;
import com.boot.rest.CreditCardManagement.entity.Customer;
import com.boot.rest.CreditCardManagement.exception.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public void deleteCustomerById(long cusId) throws RecordNotFoundException {
        //if(this.customerRepository.existsById(cusId))
        if(!this.customerRepository.findById(cusId).isPresent()){ // here is modified by tester
            throw new RecordNotFoundException("customer with "+cusId+" does not exist");
        }else{
            System.out.println("customer: " + customerRepository.findById(cusId) + " deleted");
            this.customerRepository.deleteById(cusId);
        }

    }

    public Optional<Customer> findCustomerById(long cusId){
        return customerRepository.findById(cusId);
    }

    public List<Customer> getAllCustomer(){
        return customerRepository.findAll();
    }
}