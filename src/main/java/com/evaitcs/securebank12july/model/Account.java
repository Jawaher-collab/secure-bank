package com.evaitcs.securebank12july.model;


import com.evaitcs.securebank12july.model.enums.AccountStatus;
import com.evaitcs.securebank12july.model.enums.AccountType;
import lombok.Data;
import lombok.NoArgsConstructor;



import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // رقم الحساب
    // Unique account number
    @Column(nullable = false, unique = true)
    private String accountNumber;

    // نوع الحساب
    // Account type
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountType accountType;

    // الرصيد
    // Current balance
    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal balance = BigDecimal.ZERO;

    // حالة الحساب
    // Account status
    @Enumerated(EnumType.STRING)
    private AccountStatus status = AccountStatus.ACTIVE;

    // تاريخ إنشاء الحساب
    // Account creation date
    private LocalDateTime createdAt = LocalDateTime.now();

    // العميل صاحب الحساب
    // Customer who owns the account
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
}