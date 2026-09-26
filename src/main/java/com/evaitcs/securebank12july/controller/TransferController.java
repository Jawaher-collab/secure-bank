package com.evaitcs.securebank12july.controller;


import com.evaitcs.securebank12july.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/transfers")
@RequiredArgsConstructor
public class TransferController {

    private final TransferService transferService;

    @PostMapping
    public String transfer(
            @RequestParam Long fromAccountId,
            @RequestParam Long toAccountId,
            @RequestParam BigDecimal amount) {

        transferService.transfer(fromAccountId, toAccountId, amount);

        return "Transfer completed successfully";
    }

}