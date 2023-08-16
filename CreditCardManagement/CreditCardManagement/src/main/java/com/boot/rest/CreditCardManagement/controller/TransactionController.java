package com.boot.rest.CreditCardManagement.controller;

import com.boot.rest.CreditCardManagement.entity.CategoryTransactions;
import com.boot.rest.CreditCardManagement.entity.StateTransactions;
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
    public List<Transaction> getTransactionByFilters(@RequestParam String gender, @RequestParam String category, @RequestParam String merchant,
                                                          @RequestParam String city, @RequestParam String state,
                                                          @RequestParam String job,  @RequestParam int amt) throws RecordNotFoundException {
        List<Transaction> transactionList = transactionService.getTransaction(gender, category,merchant, city, state, job, amt);
        System.out.println(transactionList.size()+" files meet the requirements");
        return transactionList;

    }

    @GetMapping("/analystByState")
    public List<StateTransactions> getTransactionByStateAnalysis(){
        List<StateTransactions> results = transactionService.getTransactionsForCountry();
        return results;
    }

    @GetMapping("/analysisByCategory")
    public List<CategoryTransactions> getTransactionByCategory(@RequestParam String state){
        List<CategoryTransactions> results = transactionService.getTransactionsForCategory(state);
        return results;
    }

//    @DeleteMapping
//    public void deleteById(){
//        for(long i=44; i<1008000; i+=8){
//            transactionService.deleteById(i);
//        }
//
//    }

}
