package com.evaitcs.securebank12july.service;

import com.evaitcs.securebank12july.model.Account;
import com.evaitcs.securebank12july.model.Customer;
import com.evaitcs.securebank12july.repository.AccountRepository;
import com.evaitcs.securebank12july.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;



    // إنشاء حساب وربطه بعميل
    // Create an account and link it to a customer
    public Account createAccount(Long customerId, Account account) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() ->
                        new RuntimeException("Customer not found"));

        account.setCustomer(customer);

        return accountRepository.save(account);
    }

    // جلب جميع الحسابات
    // Get all accounts
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    // جلب حساب بواسطة ID
    // Get an account by ID
    public Account getAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Account not found"));
    }

    // حذف حساب
    // Delete an account
    public void deleteAccount(Long id) {
        Account account = getAccountById(id);
        accountRepository.delete(account);
    }

    public List<Account> getAccountsByCustomerId(Long customerId) {

        return accountRepository.findByCustomerId(customerId);
    }
}