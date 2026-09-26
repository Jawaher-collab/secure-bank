package com.evaitcs.securebank12july;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AiConfig {

    @Bean
    public SimpleVectorStore vectorStore(EmbeddingModel embeddingModel) {
        return SimpleVectorStore.builder(embeddingModel).build();
    }

    @Bean
    public TextReader textReader() {
        return new TextReader("classpath:securebank-policies.txt");
    }


    @Bean
    CommandLineRunner loadPolicies(
            TextReader textReader,
            SimpleVectorStore vectorStore) {

        return args -> {
           // vectorStore.add(textReader.get());

            TokenTextSplitter splitter = TokenTextSplitter
                    .builder()
                    .withMinChunkSizeChars(100)
                    .withChunkSize(100)
                    .build();

            var chunks = splitter.apply(textReader.get());
            System.out.println("NUMBER OF CHUNKS = " + chunks.size());

            vectorStore.add(chunks);

        };

    }
}
