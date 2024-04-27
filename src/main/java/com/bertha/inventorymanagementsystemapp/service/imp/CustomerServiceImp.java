package com.bertha.inventorymanagementsystemapp.service.imp;

import com.bertha.inventorymanagementsystemapp.domain.Customer;
import com.bertha.inventorymanagementsystemapp.dto.request.CustomerRequest;
import com.bertha.inventorymanagementsystemapp.dto.response.CustomerResponse;
import com.bertha.inventorymanagementsystemapp.repository.CustomerRepository;
import com.bertha.inventorymanagementsystemapp.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImp implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImp(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerResponse registerCustomer(CustomerRequest customerRequest) {
        Customer customer = new Customer(null,customerRequest.customerName(), customerRequest.customerEmail(), customerRequest.customerPhone(), null);
        Customer savedCustomer = customerRepository.save(customer);
        return new CustomerResponse(savedCustomer.getCustomerId(), savedCustomer.getCustomerName(), savedCustomer.getCustomerEmail(), savedCustomer.getCustomerPhone());
    }

    @Override
    public CustomerResponse updateCustomer(CustomerRequest customer, Long id) {
        Customer customerToUpdate = customerRepository.findById(id).orElse(null);
        System.out.println(customerToUpdate);
        if ( customerToUpdate == null){
            Customer newCustomer = new Customer(null,customer.customerName(), customer.customerEmail(), customer.customerPhone(), null);
            return registerCustomer(new CustomerRequest(newCustomer.getCustomerName(), newCustomer.getCustomerEmail(), newCustomer.getCustomerPhone()));
        }

         customerToUpdate.setCustomerName(customer.customerName());
        customerToUpdate.setCustomerEmail(customer.customerEmail());
        customerToUpdate.setCustomerPhone(customer.customerPhone());
        Customer updatedCustomer = customerRepository.save(customerToUpdate);


        return new CustomerResponse(updatedCustomer.getCustomerId(), updatedCustomer.getCustomerName(), updatedCustomer.getCustomerEmail(), updatedCustomer.getCustomerPhone());

    }

    @Override
    public String deleteCustomer(Long id) {
        if(customerRepository.findById(id).isEmpty()){
            return "Customer does not exist";
        }
        customerRepository.deleteById(id);
        return "Customer deleted successfully";
    }

    @Override
    public CustomerResponse getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if(customer == null){
            return null;
        }
        return new CustomerResponse(customer.getCustomerId(), customer.getCustomerName(), customer.getCustomerEmail(), customer.getCustomerPhone());

    }

    @Override
    public List<CustomerResponse> getAllCustomers() {
      List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(customer -> new CustomerResponse(customer.getCustomerId(), customer.getCustomerName(), customer.getCustomerEmail(), customer.getCustomerPhone())).toList();
    }

}
