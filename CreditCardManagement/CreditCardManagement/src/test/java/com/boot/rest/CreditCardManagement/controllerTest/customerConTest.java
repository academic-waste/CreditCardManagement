package com.boot.rest.CreditCardManagement.controllerTest;

import com.boot.rest.CreditCardManagement.entity.Customer;
import com.boot.rest.CreditCardManagement.exception.RecordNotFoundException;
import com.boot.rest.CreditCardManagement.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class customerConTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    private Customer customer1, customer2, customer3;

    @BeforeEach
    public void setUp() {
        // Creating mock data
        customer1 = new Customer(1L,"Adam","L","M","Coder",
                new Calendar.Builder().setDate(1966, 10, 11).build().getTime());
        customer2 = new Customer(2L,"Anna","K","F","Designer",
                new Calendar.Builder().setDate(1990, 5, 21).build().getTime());
        customer3 = new Customer(3L,"John","D","M","Manager",
                new Calendar.Builder().setDate(1985, 2, 15).build().getTime());

    }

    @Test
    public void getCustomerByIdTest() throws Exception {
        long customerId = 1L;

        when(customerService.findCustomerById(1L)).thenReturn(Optional.of(customer1));

        mockMvc.perform(get("/customer/" + customerId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.customerId").value(customerId));

        verify(customerService, times(2)).findCustomerById(customerId);
    }

    @Test
    public void getAllCustomersTest() throws Exception {
        List<Customer> allCustomers = Arrays.asList(customer1, customer2, customer3);

        when(customerService.getAllCustomer()).thenReturn(allCustomers);

        mockMvc.perform(get("/customer"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3))) // Check if list has 3 items
                .andExpect(jsonPath("$[0].customerId").value(customer1.getCustomerId()))
                .andExpect(jsonPath("$[1].customerId").value(customer2.getCustomerId()))
                .andExpect(jsonPath("$[2].customerId").value(customer3.getCustomerId()));

        verify(customerService, times(1)).getAllCustomer();
    }

    @Test
    public void deleteCustomerByIdTest_success() throws Exception {
        long cusId = 1L;

        // Mock the behavior of the service for successful deletion
        doNothing().when(customerService).deleteCustomerById(cusId);

        mockMvc.perform(delete("/customer/" + cusId))
                .andExpect(status().isAccepted()) // 202 ACCEPTED
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.SUCCESS").value("Customer deleted successfully"));

        verify(customerService, times(1)).deleteCustomerById(cusId);
    }

    @Test
    public void deleteCustomerByIdTest_failure() throws Exception {
        long cusId = 99L; // assuming this ID doesn't exist
        String expectedErrorMessage = "Customer not found";
        // Mock the behavior of the service to throw RecordNotFoundException
        doThrow(new RecordNotFoundException(expectedErrorMessage)).when(customerService).deleteCustomerById(cusId);

        mockMvc.perform(delete("/customer/" + cusId))
                .andExpect(status().isNotAcceptable()) // 406 NOT_ACCEPTABLE
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.FAILURE").value(expectedErrorMessage));
        verify(customerService, times(1)).deleteCustomerById(cusId);
    }


}
