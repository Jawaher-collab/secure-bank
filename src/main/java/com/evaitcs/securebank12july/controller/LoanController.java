package com.evaitcs.securebank12july.controller;

import com.evaitcs.securebank12july.model.Loan;
import com.evaitcs.securebank12july.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    // تقديم طلب قرض لعميل
    // Submit a loan application for a customer
    @PostMapping("/{customerId}")
    public Loan requestLoan(
            @PathVariable Long customerId,
            @RequestBody Loan loan) {

        return loanService.requestLoan(customerId, loan);
    }

    // جلب جميع طلبات القروض
    // Get all loan applications
    @GetMapping
    public List<Loan> getAllLoans() {
        return loanService.getAllLoans();
    }

    // حذف طلب قرض
    // Delete a loan application
    @DeleteMapping("/{id}")
    public void deleteLoan(@PathVariable Long id) {
        loanService.deleteLoan(id);
    }
}