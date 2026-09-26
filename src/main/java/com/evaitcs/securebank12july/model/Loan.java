package com.evaitcs.securebank12july.model;


import com.evaitcs.securebank12july.model.enums.LoanStatus;
import com.evaitcs.securebank12july.model.enums.LoanType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "loans")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // مبلغ القرض المطلوب
    // Requested loan amount
    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    // نوع القرض
    // Loan type
    @Enumerated(EnumType.STRING)
    private LoanType loanType;

    // حالة طلب القرض
    // Loan application status
    @Enumerated(EnumType.STRING)
    private LoanStatus status = LoanStatus.PENDING;

    // تاريخ تقديم الطلب
    // Application date
    private LocalDateTime appliedAt = LocalDateTime.now();

    // العميل الذي طلب القرض
    // Customer who requested the loan
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
}