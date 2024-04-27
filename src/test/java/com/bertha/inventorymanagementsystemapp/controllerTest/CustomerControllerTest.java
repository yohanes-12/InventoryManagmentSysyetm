package com.bertha.inventorymanagementsystemapp.controllerTest;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import com.bertha.inventorymanagementsystemapp.controller.CustomerController;
import com.bertha.inventorymanagementsystemapp.dto.request.CustomerRequest;
import com.bertha.inventorymanagementsystemapp.dto.response.CustomerResponse;
import com.bertha.inventorymanagementsystemapp.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

class CustomerControllerTest {

    private MockMvc mockMvc;
    private CustomerService customerService = mock(CustomerService.class);
    private CustomerController customerController;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        customerController = new CustomerController(customerService);
        mockMvc = standaloneSetup(customerController).build();
    }

    @Test
    void registerCustomer() throws Exception {
        CustomerRequest customerRequest = new CustomerRequest("John Doe", "john@example.com", "1234567890");
        CustomerResponse customerResponse = new CustomerResponse(1L, "John Doe", "john@example.com", "1234567890");

        when(customerService.registerCustomer(any(CustomerRequest.class))).thenReturn(customerResponse);

        mockMvc.perform(post("/ims/api/v1/customer/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerId").value(1L))
                .andExpect(jsonPath("$.customerName").value("John Doe"))
                .andExpect(jsonPath("$.customerEmail").value("john@example.com"))
                .andExpect(jsonPath("$.customerPhone").value("1234567890"));
    }
}
