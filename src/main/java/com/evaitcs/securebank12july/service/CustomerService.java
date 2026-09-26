package com.evaitcs.securebank12july.service;

import com.evaitcs.securebank12july.model.Customer;
import com.evaitcs.securebank12july.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    // حقن الـRepository باستخدام Constructor Injection
    // Inject the repository using constructor injection
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // إضافة عميل جديد
    // Create a new customer
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // جلب جميع العملاء
    // Get all customers
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // البحث عن عميل بواسطة ID
    // Find a customer by ID
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Customer not found with ID: " + id));
    }

    // تحديث بيانات العميل
    // Update customer information
    public Customer updateCustomer(Long id, Customer updatedCustomer) {

        Customer existingCustomer = getCustomerById(id);

        existingCustomer.setFirstName(updatedCustomer.getFirstName());
        existingCustomer.setLastName(updatedCustomer.getLastName());
        existingCustomer.setEmail(updatedCustomer.getEmail());
        existingCustomer.setPhone(updatedCustomer.getPhone());
        existingCustomer.setDateOfBirth(updatedCustomer.getDateOfBirth());
        existingCustomer.setAddress(updatedCustomer.getAddress());

        return customerRepository.save(existingCustomer);
    }

    // حذف العميل
    // Delete a customer
    public void deleteCustomer(Long id) {

        Customer existingCustomer = getCustomerById(id);

        customerRepository.delete(existingCustomer);
    }


    public Customer getCustomerByUsername(String username) {

        return customerRepository.findByUserUsername(username)
                .orElseThrow(() ->
                        new RuntimeException("Customer not found"));
    }
}