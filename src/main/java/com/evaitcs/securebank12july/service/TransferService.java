package com.evaitcs.securebank12july.service;

import com.evaitcs.securebank12july.model.Account;
import com.evaitcs.securebank12july.model.Transaction;
import com.evaitcs.securebank12july.model.enums.TransactionType;
import com.evaitcs.securebank12july.repository.AccountRepository;
import com.evaitcs.securebank12july.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransferService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionAiService  transactionAiService;
    private final FraudAiService fraudAiService;



    @Transactional
    public void transfer(Long fromAccountId, Long toAccountId, BigDecimal amount) {

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Transfer amount must be greater than zero");
        }
        if (fromAccountId.equals(toAccountId)) {
            throw new IllegalArgumentException(
                    "Cannot transfer money to the same account"
            );
        }
        Account fromAccount = accountRepository.findById(fromAccountId)
                .orElseThrow(() -> new RuntimeException("Sender account not found"));

        Account toAccount = accountRepository.findById(toAccountId)
                .orElseThrow(() -> new RuntimeException("Receiver account not found"));

        if (fromAccount.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient balance");
        }

        fromAccount.setBalance(
                fromAccount.getBalance().subtract(amount)
        );

        toAccount.setBalance(
                toAccount.getBalance().add(amount)
        );

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);


        Transaction transaction = new Transaction();

        transaction.setType(TransactionType.TRANSFER);
        transaction.setAmount(amount);
        transaction.setSourceAccount(fromAccount);
        transaction.setDestinationAccount(toAccount);
        transaction.setDescription("Unusual high value online purchase");

        // 3. Send the description to AI

        String category =

                transactionAiService.categorizeTransaction(

                        transaction.getDescription()

                );

        // 4. Set the category returned by AI

        transaction.setCategory(category);

        transactionRepository.save(transaction);

        String fraudResult = fraudAiService.analyzeTransaction(
                transaction.getAmount(),
                transaction.getDescription(),
                transaction.getCategory()
        );

        transaction.setFraudStatus(fraudResult);
        transactionRepository.save(transaction);

    }
}