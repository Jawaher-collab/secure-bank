package com.evaitcs.securebank12july.model;




import com.evaitcs.securebank12july.model.enums.CardStatus;
import com.evaitcs.securebank12july.model.enums.CardType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

    @Entity
    @Table(name = "cards")
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class Card {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        // رقم البطاقة
        // Card number
        @Column(nullable = false, unique = true)
        private String cardNumber;

        // نوع البطاقة
        // Card type
        @Enumerated(EnumType.STRING)
        private CardType cardType;

        // تاريخ انتهاء البطاقة
        // Card expiration date
        private LocalDate expirationDate;

        // حالة البطاقة
        // Card status
        @Enumerated(EnumType.STRING)
        private CardStatus status = CardStatus.ACTIVE;

        // الحساب المرتبط بالبطاقة
        // Account linked to the card
        @ManyToOne
        @JoinColumn(name = "account_id", nullable = false)
        private Account account;
    }