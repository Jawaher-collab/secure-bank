package com.evaitcs.securebank12july.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class FraudAiService {



    private ChatClient chatClient;

    public FraudAiService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public String analyzeTransaction(
            BigDecimal amount,
            String description,
            String category) {

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "Transaction amount must be greater than zero"
            );
        }
        String result = chatClient
                .prompt()
                .system("""
                You are a fraud detection assistant for SecureBank.

                Analyze the transaction and determine whether it appears
                SUSPICIOUS or LEGITIMATE.

                Return only SUSPICIOUS or LEGITIMATE.
                """)
                .user("""

                    Amount: %s

                    Description: %s

                    Category: %s

                    """.formatted(amount, description, category))
                .call()
                .content();

        if (!result.equals("SUSPICIOUS")
                && !result.equals("LEGITIMATE")) {

          return "REVIEW_REQUIRED";

        }

        return result;



    }
}
