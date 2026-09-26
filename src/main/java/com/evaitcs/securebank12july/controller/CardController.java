package com.evaitcs.securebank12july.controller;

import com.evaitcs.securebank12july.model.Card;
import com.evaitcs.securebank12july.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    // إنشاء بطاقة لحساب
    // Create a card for an account
    @PostMapping("/{accountId}")
    public Card createCard(
            @PathVariable Long accountId,
            @RequestBody Card card) {

        return cardService.createCard(accountId, card);
    }

    // جلب جميع البطاقات
    // Get all cards
    @GetMapping
    public List<Card> getAllCards() {
        return cardService.getAllCards();
    }

    // حذف بطاقة
    // Delete a card
    @DeleteMapping("/{id}")
    public void deleteCard(@PathVariable Long id) {
        cardService.deleteCard(id);
    }
}