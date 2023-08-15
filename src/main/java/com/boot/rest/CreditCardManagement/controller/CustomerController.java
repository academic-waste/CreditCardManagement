package com.boot.rest.CreditCardManagement.controller;

import com.boot.rest.CreditCardManagement.entity.Customer;
import com.boot.rest.CreditCardManagement.exception.RecordNotFoundException;
import com.boot.rest.CreditCardManagement.service.CustomerService;
import com.boot.rest.CreditCardManagement.util.StatusMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @DeleteMapping("/{cusId}")
    public ResponseEntity<Object> deleteCustomerById(@PathVariable long cusId){
        Map<StatusMessages, String> map = new HashMap<>();
        try {
            this.customerService.deleteCustomerById(cusId);
            map.put(StatusMessages.SUCCESS, "Customer deleted successfully");
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(map);
        } catch (RecordNotFoundException e) {
            map.put(StatusMessages.FAILURE, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(map);
        }
    }

    @GetMapping("/{cusId}")
    public Optional<Customer> getCustomerById(@PathVariable long cusId){
        System.out.println(customerService.findCustomerById(cusId));
        return this.customerService.findCustomerById(cusId);
    }

    @GetMapping
    public List<Customer> getAllCustomer(){
        return this.customerService.getAllCustomer();
    }
}
