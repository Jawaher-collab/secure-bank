package com.evaitcs.securebank12july.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "beneficiaries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Beneficiary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // اسم المستفيد
    // Beneficiary name
    @Column(nullable = false)
    private String name;

    // رقم حساب المستفيد
    // Beneficiary account number
    @Column(nullable = false)
    private String accountNumber;

    // اسم البنك
    // Bank name
    private String bankName;

    // العميل الذي أضاف المستفيد
    // Customer who added the beneficiary
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
}