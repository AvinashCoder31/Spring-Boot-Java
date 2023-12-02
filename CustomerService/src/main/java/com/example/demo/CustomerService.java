package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        // Check if the customer with the given id exists
        Optional<Customer> existingCustomer = customerRepository.findById(id);
        
        if (existingCustomer.isPresent()) {
            // Update the existing customer with the new data
            Customer customerToUpdate = existingCustomer.get();
            customerToUpdate.setName(updatedCustomer.getName());
            customerToUpdate.setEmail(updatedCustomer.getEmail());
            customerToUpdate.setPhoneNumber(updatedCustomer.getPhoneNumber());

            // Save the updated customer
            return customerRepository.save(customerToUpdate);
        } else {
            // Customer with the given id not found
            throw new RuntimeException("Customer not found with id: " + id);
        }
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
