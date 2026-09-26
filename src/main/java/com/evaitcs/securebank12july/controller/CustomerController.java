package com.evaitcs.securebank12july.controller;

import com.evaitcs.securebank12july.model.Account;
import com.evaitcs.securebank12july.model.Customer;
import com.evaitcs.securebank12july.service.AccountService;
import com.evaitcs.securebank12july.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final AccountService accountService;
    private final CustomerService customerService;

    // حقن الـService باستخدام Constructor Injection
    // Inject the service using constructor injection
    public CustomerController(CustomerService customerService, AccountService accountService) {
        this.customerService = customerService;
        this.accountService = accountService;

    }

    @GetMapping("/test")

    public String testCustomer() {

        return "Welcome Customer";

    }


    // إضافة عميل جديد
    // Create a new customer
//    @PostMapping
//    public ResponseEntity<Customer> createCustomer(
//            @RequestBody Customer customer) {
//
//        Customer savedCustomer =
//                customerService.createCustomer(customer);
//
//        return ResponseEntity
//                .status(HttpStatus.CREATED)
//                .body(savedCustomer);
//    }

    // جلب جميع العملاء
    // Get all customers
//    @GetMapping
//    public ResponseEntity<List<Customer>> getAllCustomers() {
//
//        return ResponseEntity.ok(
//                customerService.getAllCustomers()
//        );
//    }

    // جلب عميل بواسطة ID
    // Get a customer by ID
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                customerService.getCustomerById(id)
        );
    }

    // تحديث بيانات العميل
    // Update a customer
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(
            @PathVariable Long id,
            @RequestBody Customer customer) {

        return ResponseEntity.ok(
                customerService.updateCustomer(id, customer)
        );
    }

    // حذف العميل
    // Delete a customer
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(
            @PathVariable Long id) {

        customerService.deleteCustomer(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/me/accounts")
    public List<Account> getMyAccounts(Authentication authentication) {
        String username = authentication.getName();
        Customer customer = customerService.getCustomerByUsername(username);
return accountService.getAccountsByCustomerId(customer.getId());
    }

}