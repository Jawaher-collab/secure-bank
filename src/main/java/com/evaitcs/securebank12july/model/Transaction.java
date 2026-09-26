package com.evaitcs.securebank12july.model;


import com.evaitcs.securebank12july.model.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;


import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // نوع العملية
    // Transaction type
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType type;

    // مبلغ العملية
    // Transaction amount
    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    // وقت العملية
    // Transaction date and time
    private LocalDateTime createdAt = LocalDateTime.now();

    // وصف العملية
    // Transaction description
    private String description;
    private String category;
    private String fraudStatus;


    // الحساب الذي خرج منه المبلغ
    // Account from which money was taken
    @ManyToOne
    @JoinColumn(name = "source_account_id")
    private Account sourceAccount;

    // الحساب الذي وصل إليه المبلغ
    // Account that received the money
    @ManyToOne
    @JoinColumn(name = "destination_account_id")
    private Account destinationAccount;




}