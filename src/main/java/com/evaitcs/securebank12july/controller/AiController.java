package com.evaitcs.securebank12july.controller;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ai.tool.ToolCallbackProvider;

@RestController
@RequestMapping("/api/ai")
public class AiController {

    private final ChatClient chatClient;
    private final SimpleVectorStore vectorStore;
    private final ToolCallbackProvider mcpTools;

    public AiController(ChatClient.Builder builder, SimpleVectorStore vectorStore, ToolCallbackProvider mcpTools) {
        this.chatClient = builder.build();
        this.vectorStore = vectorStore;
        this.mcpTools = mcpTools;
    }

    @GetMapping("/chat")
    public String chat(@RequestParam String question) {

        var documents = vectorStore.similaritySearch(question);

        String context = documents.get(0).getText();
        return chatClient
                .prompt()
                .system("""
                    You are a customer support assistant for SecureBank.
                    Answer only questions related to SecureBank and banking services.
                    Be clear, helpful, and concise.
                    """)
                .user("""

                    Context:

                    %s

                    Customer question:

                    %s

                    """.formatted(context, question))
                .tools(mcpTools)
                .call()
                .content();
    }

    @GetMapping("/search")
    public Object search(@RequestParam String question) {

        return vectorStore.similaritySearch(question);
    }


    @GetMapping("/categorize")
    public String categorizeTransaction(@RequestParam String description) {

        return chatClient
                .prompt()
                .system("""

                    You are a transaction categorization assistant for SecureBank.

                    Categorize the transaction into exactly one of these categories:

                    FOOD,

                    TRANSPORTATION,

                    SHOPPING,

                    ENTERTAINMENT,

                    UTILITIES,

                    OTHER.

                    Return only the category name.

                    """)

                .user(description)
                .call()
                .content();


    }





}
