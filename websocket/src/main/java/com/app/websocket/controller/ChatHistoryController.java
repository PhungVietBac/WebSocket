package com.app.websocket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.websocket.model.ChatMessage;
import com.app.websocket.repository.ChatMessageRepository;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api")
public class ChatHistoryController {
    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @GetMapping("/message")
    public List<ChatMessage> getMessages() {
        return chatMessageRepository.findAll(Sort.by(Sort.Direction.ASC, "timestamp"));
    }
    
}
