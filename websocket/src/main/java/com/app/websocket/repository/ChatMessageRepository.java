package com.app.websocket.repository;

import com.app.websocket.model.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
    // You can add custom queries here if needed
}
