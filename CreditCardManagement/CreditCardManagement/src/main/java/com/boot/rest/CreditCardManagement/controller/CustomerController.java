package com.boot.rest.CreditCardManagement.controller;

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
@CrossOrigin
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

//    @PostMapping("/create")
//    public ResponseEntity<Object> createCustomer(@RequestBody String first, String last, String gender, String job, String dob, Long customerId) throws ParseException, RecordExistsException {
//        SimpleDateFormat isoFormat = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss 'GMT'Z");
//        Date date = isoFormat.parse(dob);
//        Map<StatusMessages, String> map = new HashMap<>();
//        try {
//            this.customerService.insertCustomer(first,last,gender,job,date,customerId);
//            map.put(StatusMessages.SUCCESS, "Customer inserted successfully");
//            return ResponseEntity.status(HttpStatus.ACCEPTED).body(map);
//        } catch (RecordExistsException e) {
//            map.put(StatusMessages.FAILURE, e.getMessage());
//            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(map);
//        }
//
//    }

    @PostMapping("/create")
    public ResponseEntity<Object> createCustomer(@RequestBody Customer customer) throws RecordExistsException {
        Map<StatusMessages, String> map = new HashMap<>();
        try {
            this.customerService.insertCustomer(
                    customer.getFirst(),
                    customer.getLast(),
                    customer.getGender(),
                    customer.getJob(),
                    customer.getDob(),
                    customer.getCustomerId()
            );
            map.put(StatusMessages.SUCCESS, "Customer inserted successfully");
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(map);
        } catch (RecordExistsException e) {
            map.put(StatusMessages.FAILURE, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(map);
        }
    }


    @DeleteMapping("/{cusId}")
    public ResponseEntity<Object> deleteCustomerById(@PathVariable long cusId){
        Map<StatusMessages, String> map = new HashMap<>();
        try {
            this.customerService.deleteCustomerById(cusId);
            map.put(StatusMessages.SUCCESS, "Customer deleted successfully");
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(map);
        }catch (RecordNotFoundException e) {
            map.put(StatusMessages.FAILURE, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(map);
        }
    }

//@DeleteMapping("/{cusId}")
//public void deleteCustomerById(@PathVariable long cusId) throws RecordNotFoundException {
//    this.customerService.deleteCustomerById(cusId);
//}

//    @DeleteMapping("/newDelete/{cussId}")
//    public void delete(@PathVariable long cussId) throws RecordNotFoundException {
//        this.customerService.deleteCustomer(cussId);
//    }

//    @GetMapping("/{cusId}")
//    public Optional<Customer> getCustomerById(@PathVariable long cusId){
//        System.out.println(customerService.findCustomerById(cusId));
//        return this.customerService.findCustomerById(cusId);
//    }

    @GetMapping
    public List<Customer> getAllCustomer(){
        return this.customerService.getAllCustomer();
    }

}
