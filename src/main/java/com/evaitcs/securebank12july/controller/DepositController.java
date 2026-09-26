package com.evaitcs.securebank12july.controller;

import com.evaitcs.securebank12july.service.DepositService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/deposits")
@RequiredArgsConstructor
public class DepositController {



    private final DepositService depositService;
    @PostMapping
    public String deposit(
            @RequestParam Long accountId,
            @RequestParam BigDecimal amount) {

        depositService.deposit(accountId, amount);

        return "Deposit completed successfully";
    }

}