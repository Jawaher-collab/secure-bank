package com.evaitcs.securebank12july.repository;

import com.evaitcs.securebank12july.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
}