package com.boot.rest.CreditCardManagement.serviceTest;

import com.boot.rest.CreditCardManagement.entity.Transaction;
import com.boot.rest.CreditCardManagement.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class TransactionSpec {

    @Mock
    private MongoTemplate mongoTemplate;

    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private Transaction createSampleTransaction(String gender, String category, String merchant, String city, String state, long population, double amt) {
        Transaction transaction = new Transaction();
        transaction.setGender(gender);
        transaction.setCategory(category);
        transaction.setMerchant(merchant);
        transaction.setCity(city);
        transaction.setState(state);
        transaction.setCityPopulation(population);
        transaction.setAmt(amt);

        return transaction;
    }

    @Test
    public void getTransactionTest() {
        // Create sample data
        Transaction transaction1 = createSampleTransaction("M", "Electronics", "Amazon", "New York", "NY", 5000, 250.50);
        Transaction transaction2 = createSampleTransaction("F", "Clothing", "Walmart", "Los Angeles", "CA", 7000, 100.75);

        List<Transaction> mockTransactions = Arrays.asList(transaction1, transaction2);

        // Mocking MongoTemplate's find method
        when(mongoTemplate.find(any(Query.class), eq(Transaction.class), eq("transaction"))).thenReturn(mockTransactions);

        // Call the method
        List<Transaction> retrievedTransactions = transactionService.getTransaction("M", "Electronics", "Amazon", "New York", "NY", 0, 10000, 0.0, 300.0);

        // Assert the results
        assertEquals(1, retrievedTransactions.size());  // Only one transaction should match the criteria


    }
}
