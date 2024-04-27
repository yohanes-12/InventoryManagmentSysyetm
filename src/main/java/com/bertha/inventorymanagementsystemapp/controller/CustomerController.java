package com.bertha.inventorymanagementsystemapp.controller;

import com.bertha.inventorymanagementsystemapp.domain.Customer;
import com.bertha.inventorymanagementsystemapp.dto.request.CustomerRequest;
import com.bertha.inventorymanagementsystemapp.dto.response.CustomerResponse;
import com.bertha.inventorymanagementsystemapp.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ims/api/v1/customer")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @PostMapping("/register")
    public ResponseEntity<CustomerResponse> registerCustomer(@RequestBody @Valid CustomerRequest customer){
        return ResponseEntity.ok(customerService.registerCustomer(customer));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CustomerResponse> updateCustomer(@RequestBody @Valid CustomerRequest customer,@PathVariable Long id){
        return ResponseEntity.ok(customerService.updateCustomer(customer, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id){
        return ResponseEntity.ok(customerService.deleteCustomer(id));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long id){
        CustomerResponse customer = customerService.getCustomerById(id);
        if(customer == null){
            return ResponseEntity.badRequest().body("Customer does not exist");
        }
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/list")
    public ResponseEntity<List<CustomerResponse>> getCustomers(){
        return ResponseEntity.ok(customerService.getAllCustomers());
    }


}
