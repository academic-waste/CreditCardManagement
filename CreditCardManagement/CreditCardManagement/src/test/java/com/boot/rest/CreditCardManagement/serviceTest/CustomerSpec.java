package com.boot.rest.CreditCardManagement.serviceTest;


import com.boot.rest.CreditCardManagement.dao.CustomerRepository;
import com.boot.rest.CreditCardManagement.entity.Customer;
import com.boot.rest.CreditCardManagement.exception.RecordNotFoundException;
import com.boot.rest.CreditCardManagement.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CustomerSpec {


    @Mock
    CustomerRepository repo;

    @InjectMocks
    CustomerService service;

    List<Customer> collection;
    Customer customer1, customer2, customer3;

    @BeforeEach
    public void befEach(){c
        customer1 = new Customer(1L,"Paul","L","M","Coder",
                new Calendar.Builder().setDate(1986, 10, 18).build().getTime());

        customer2 = new Customer(2L,"Rose","K","F","Interpreter",
                new Calendar.Builder().setDate(1996, 12, 22).build().getTime());

        customer3 = new Customer(3L,"Tom","F","F","Banker",
                new Calendar.Builder().setDate(1966, 10, 11).build().getTime());

        collection = Arrays.asList(customer1, customer2, customer3);
    }

    @Test
    public void deleteCustomerByIdSpec() throws RecordNotFoundException {
        // Given
        long customerId = customer1.getCustomerId();
        when(repo.findById(customerId)).thenReturn(Optional.of(customer1)).thenReturn(Optional.empty());

        // When
        service.deleteCustomerById(customerId);

        // Then
        verify(repo, times(1)).deleteById(customerId);
    }

    @Test
    public void deleteCustomerByIdNotFoundSpec() {
        // Given
        long invalidCustomerId = 9999L; // Assuming this ID does not exist in the database
        when(repo.findById(invalidCustomerId)).thenReturn(Optional.empty());

        // Then
        assertThrows(RecordNotFoundException.class, () -> {
            // When
            service.deleteCustomerById(invalidCustomerId);
        });
    }

    @Test
    public void findCustomerByIdSpec(){
        when(repo.findById(customer1.getCustomerId())).thenReturn(Optional.of(customer1));

        Optional<Customer> cusTest = service.findCustomerById(customer1.getCustomerId());

        assertEquals(cusTest,Optional.of(customer1));
    }

    @Test
    public void getAllCustomerSpec(){
        when(repo.findAll()).thenReturn(collection);

        List<Customer> cusTest = service.getAllCustomer();

        assertEquals(3,cusTest.size());
    }









}
