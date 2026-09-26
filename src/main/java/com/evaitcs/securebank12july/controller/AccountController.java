package com.evaitcs.securebank12july.controller;

import com.evaitcs.securebank12july.model.Account;
import com.evaitcs.securebank12july.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // إنشاء حساب لعميل
    // Create an account for a customer
    @PostMapping("/{customerId}")
    public Account createAccount(
            @PathVariable Long customerId,
            @RequestBody Account account) {

        return accountService.createAccount(customerId, account);
    }

    // جلب جميع الحسابات
    // Get all accounts
    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    // جلب حساب بواسطة ID
    // Get an account by ID
    @GetMapping("/{id}")
    public Account getAccountById(@PathVariable Long id) {
        return accountService.getAccountById(id);
    }

    // حذف حساب
    // Delete an account
    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
    }
}