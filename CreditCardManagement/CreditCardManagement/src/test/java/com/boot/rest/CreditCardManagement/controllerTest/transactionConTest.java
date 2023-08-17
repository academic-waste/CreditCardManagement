package com.boot.rest.CreditCardManagement.controllerTest;

import com.boot.rest.CreditCardManagement.entity.Transaction;
import com.boot.rest.CreditCardManagement.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class transactionConTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    private List<Transaction> mockTransactions;

    @BeforeEach
    public void setUp() {
        // Mock transaction data for testing
        mockTransactions = Arrays.asList(
                new Transaction(),
                new Transaction(),
                new Transaction()
        );
    }

    @Test
    public void getTransactionByFiltersTest() throws Exception {
        // Mock the behavior of the transactionService to return mockTransactions
        when(transactionService.getTransaction(
                anyString(), anyString(), anyString(), anyString(), anyString(),
                anyLong(), anyLong(), anyDouble(), anyDouble()
        )).thenReturn(mockTransactions);

        mockMvc.perform(get("/transaction/find")
                        .param("gender", "M")
                        .param("category", "Electronics")
                        .param("merchant", "Amazon")
                        .param("city", "New York")
                        .param("state", "NY")
                        .param("populationMin", "1000")
                        .param("populationMax", "100000")
                        .param("amtMin", "10.0")
                        .param("amtMax", "1000.0"))
                .andExpect(status().isFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3))); // Asserting that 3 transactions are returned
    }
}
