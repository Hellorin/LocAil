package com.locail.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealSuggestionService {

    private static final String SYSTEM_PROMPT =
            "You are a culinary assistant. Given a list of ingredients, suggest 3 to 5 simple meal ideas " +
            "the user could prepare with them. Be concise and practical.";

    private final ChatClient chatClient;

    public MealSuggestionService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder
                .defaultSystem(SYSTEM_PROMPT)
                .build();
    }

    public String suggestMeals(List<String> ingredients) {
        String userMessage = "I have the following ingredients: " + String.join(", ", ingredients) +
                ". What meals can I make?";

        return chatClient.prompt()
                .user(userMessage)
                .call()
                .content();
    }
}
