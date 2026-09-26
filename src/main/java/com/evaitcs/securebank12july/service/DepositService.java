package com.evaitcs.securebank12july.service;

import com.evaitcs.securebank12july.model.Account;
import com.evaitcs.securebank12july.repository.AccountRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class DepositService {

    private final AccountRepository accountRepository;

    @Transactional
    public void deposit(Long accountId, BigDecimal amount) {

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "Deposit amount must be greater than zero"
            );
        }
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        account.setBalance(
                account.getBalance().add(amount)
        );
        accountRepository.save(account);

    }

}