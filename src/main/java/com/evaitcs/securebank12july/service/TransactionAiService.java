package com.evaitcs.securebank12july.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;


@Service
public class TransactionAiService {

    private final ChatClient chatClient;
    public TransactionAiService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public String categorizeTransaction(String description) {

        return chatClient

                .prompt()

                .system("""

                    You are a transaction categorization assistant for SecureBank.

                    Categorize the transaction into one of these categories:

                    FOOD, SHOPPING, TRANSPORTATION, ENTERTAINMENT, BILLS, HEALTHCARE, OTHER.

                    

                    Return only the category name.

                    """)

                .user(description)

                .call()

                .content();

    }
}
