package com.bertha.inventorymanagementsystemapp.serviceTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.bertha.inventorymanagementsystemapp.domain.Customer;
import com.bertha.inventorymanagementsystemapp.dto.request.CustomerRequest;
import com.bertha.inventorymanagementsystemapp.dto.response.CustomerResponse;
import com.bertha.inventorymanagementsystemapp.repository.CustomerRepository;
import com.bertha.inventorymanagementsystemapp.service.imp.CustomerServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomerServiceImpTest {

    private CustomerRepository customerRepository = mock(CustomerRepository.class);
    private CustomerServiceImp customerService;

    @BeforeEach
    void setUp() {
        customerService = new CustomerServiceImp(customerRepository);
    }

    @Test
    void registerCustomer() {
        Customer customer = new Customer(null, "John Doe", "john@example.com", "1234567890", null);
        Customer savedCustomer = new Customer(1L, "John Doe", "john@example.com", "1234567890", null);
        CustomerRequest customerRequest = new CustomerRequest("John Doe", "john@example.com", "1234567890");

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        CustomerResponse result = customerService.registerCustomer(customerRequest);
        assertNotNull(result);
        assertEquals(1L, result.customerId());
        assertEquals("John Doe", result.customerName());
        assertEquals("john@example.com", result.customerEmail());
        assertEquals("1234567890", result.customerPhone());
    }
}
