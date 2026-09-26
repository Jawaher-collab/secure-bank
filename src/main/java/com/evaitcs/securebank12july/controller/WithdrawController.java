package com.evaitcs.securebank12july.controller;

import com.evaitcs.securebank12july.service.WithdrawService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/withdrawals")
@RequiredArgsConstructor
public class WithdrawController {

    private final WithdrawService withdrawService;

    @PostMapping
    public String withdraw(
            @RequestParam Long accountId,
            @RequestParam BigDecimal amount) {

        withdrawService.withdraw(accountId, amount);

        return "Withdrawal completed successfully";
    }

}