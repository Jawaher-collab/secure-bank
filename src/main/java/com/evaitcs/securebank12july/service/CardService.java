package com.evaitcs.securebank12july.service;

import com.evaitcs.securebank12july.model.Account;
import com.evaitcs.securebank12july.model.Card;
import com.evaitcs.securebank12july.repository.AccountRepository;
import com.evaitcs.securebank12july.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final AccountRepository accountRepository;

    // إنشاء بطاقة لحساب بنكي
    // Create a card for a bank account
    public Card createCard(Long accountId, Card card) {

        Account account = accountRepository
                .findById(accountId)
                .orElseThrow();

        card.setAccount(account);

        return cardRepository.save(card);
    }

    // جلب جميع البطاقات
    // Get all cards
    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    // حذف بطاقة
    // Delete a card
    public void deleteCard(Long id) {
        cardRepository.deleteById(id);
    }
}