package com.boot.rest.CreditCardManagement.controller;

import com.boot.rest.CreditCardManagement.entity.Transaction;
import com.boot.rest.CreditCardManagement.exception.RecordNotFoundException;
import com.boot.rest.CreditCardManagement.service.CustomerService;
import com.boot.rest.CreditCardManagement.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/find")
    public ResponseEntity<Object> getTransactionByFilters(@RequestParam String gender, @RequestParam String category, @RequestParam String merchant,
                                                          @RequestParam String city, @RequestParam String state,
                                                          @RequestParam long populationMin, @RequestParam long populationMax, @RequestParam double amtMin, @RequestParam double amtMax) throws RecordNotFoundException {
        List<Transaction> transactionList = transactionService.getTransaction(gender, category,merchant, city, state, populationMin, populationMax, amtMin, amtMax);
        System.out.println(transactionList.size()+" files meet the requirements");
        return ResponseEntity.status(HttpStatus.FOUND).body(transactionList);

    }

}
