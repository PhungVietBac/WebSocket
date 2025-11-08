package com.app.websocket.controller;

import java.time.Instant;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import com.app.websocket.model.ChatMessage;
import com.app.websocket.model.Greeting;
import com.app.websocket.model.HelloMessage;
import com.app.websocket.repository.ChatMessageRepository;

@Controller
public class GreetingController {
    private final ChatMessageRepository chatMessageRepository;

    public GreetingController(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    @MessageMapping("/hello")
    public Greeting greeting(HelloMessage message) throws InterruptedException {
        Thread.sleep(1000);
        String content = "Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!";
        ChatMessage chatMessage = new ChatMessage(
            message.getName(),
            content,
            Instant.now()
        );
        chatMessageRepository.save(chatMessage);
        
        return new Greeting(content);
    }
}
