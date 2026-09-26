package com.evaitcs.securebank12july.repository;

import com.evaitcs.securebank12july.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository
        extends JpaRepository<Transaction, Long> {
}