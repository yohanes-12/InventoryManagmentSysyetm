package com.bertha.inventorymanagementsystemapp.service;

import com.bertha.inventorymanagementsystemapp.domain.Customer;
import com.bertha.inventorymanagementsystemapp.dto.request.CustomerRequest;
import com.bertha.inventorymanagementsystemapp.dto.response.CustomerResponse;

import java.util.List;

public interface CustomerService {
    CustomerResponse registerCustomer(CustomerRequest customer);

    CustomerResponse updateCustomer(CustomerRequest customer, Long id);

    String deleteCustomer(Long id);

    CustomerResponse getCustomerById(Long id);

    List<CustomerResponse> getAllCustomers();

}
