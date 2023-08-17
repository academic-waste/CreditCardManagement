package com.boot.rest.CreditCardManagement.controller;

import com.boot.rest.CreditCardManagement.dao.CustomerRepository;
import com.boot.rest.CreditCardManagement.entity.Customer;
import com.boot.rest.CreditCardManagement.exception.RecordExistsException;
import com.boot.rest.CreditCardManagement.exception.RecordNotFoundException;
import com.boot.rest.CreditCardManagement.service.CustomerService;
import com.boot.rest.CreditCardManagement.util.StatusMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    private CustomerRepository customerRepository;

    @PostMapping("/create")
    public Customer createCustomer(@RequestParam String first, String last, String gender, String job, String dob, long customerId) throws ParseException, RecordExistsException {

        SimpleDateFormat isoFormat = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss 'GMT'Z");
        Date date = isoFormat.parse(dob);

        return customerService.insertCustomer(first, last, gender, job, date, customerId);
    }

    @DeleteMapping("/{cusId}")
    public ResponseEntity<Object> deleteCustomerById(@PathVariable long cusId) {
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
    public Optional<Customer> getCustomerById(@PathVariable long cusId) {
        System.out.println(customerService.findCustomerById(cusId));
        return this.customerService.findCustomerById(cusId);
    }

    @GetMapping
    public List<Customer> getAllCustomer() {
        return this.customerService.getAllCustomer();
    }
}
