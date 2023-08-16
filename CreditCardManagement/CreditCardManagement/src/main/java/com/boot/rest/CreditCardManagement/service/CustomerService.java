package com.boot.rest.CreditCardManagement.service;

import com.boot.rest.CreditCardManagement.dao.CustomerRepository;
import com.boot.rest.CreditCardManagement.entity.Customer;
import com.boot.rest.CreditCardManagement.exception.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public void deleteCustomerById(long cusId) throws RecordNotFoundException {
        //if(this.customerRepository.existsById(cusId))
        if(this.customerRepository.findById(cusId) == null){
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

    public Customer insertCustomer(String first, String last, String gender, String job, Date dob){
        Customer customer=new Customer();
        customer.setJob(job);
        customer.setDob(dob);
        customer.setFirst(first);
        customer.setLast(last);
        customer.setGender(gender);
        long cusId01  =customerRepository.count();
        customer.setCustomerId(cusId01);


        return customerRepository.insert(customer);

    }
}
