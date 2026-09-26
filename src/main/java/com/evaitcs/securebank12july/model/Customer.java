package com.evaitcs.securebank12july.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // الاسم الأول
    // First name
    @Column(nullable = false)
    private String firstName;

    // اسم العائلة
    // Last name
    @Column(nullable = false)
    private String lastName;

    // البريد الإلكتروني
    // Email address
    @Column(nullable = false, unique = true)
    private String email;

    // رقم الهاتف
    // Phone number
    private String phone;

    // تاريخ الميلاد
    // Date of birth
    private LocalDate dateOfBirth;

    // العنوان
    // Address
    private String address;

    // بيانات تسجيل الدخول الخاصة بالعميل
    // Customer login information
//    @OneToOne
//    @JoinColumn(name = "user_id", unique = true)
//    private User user;



    @OneToOne
    @MapsId
    @JoinColumn(name = "id")

    private User user;
}