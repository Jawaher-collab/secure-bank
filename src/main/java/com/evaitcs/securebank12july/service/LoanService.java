package com.evaitcs.securebank12july.service;

import com.evaitcs.securebank12july.model.Customer;
import com.evaitcs.securebank12july.model.Loan;
import com.evaitcs.securebank12july.repository.CustomerRepository;
import com.evaitcs.securebank12july.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;
    private final CustomerRepository customerRepository;

    // تقديم طلب قرض لعميل
    // Submit a loan application for a customer
    public Loan requestLoan(Long customerId, Loan loan) {

        Customer customer = customerRepository
                .findById(customerId)
                .orElseThrow();

        loan.setCustomer(customer);

        return loanRepository.save(loan);
    }

    // جلب جميع طلبات القروض
    // Get all loan applications
    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    // حذف طلب قرض
    // Delete a loan application
    public void deleteLoan(Long id) {
        loanRepository.deleteById(id);
    }
}