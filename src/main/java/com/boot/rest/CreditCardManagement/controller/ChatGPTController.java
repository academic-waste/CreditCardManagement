package com.boot.rest.CreditCardManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;
import com.boot.rest.CreditCardManagement.service.ChatGPTService;

@RestController
public class ChatGPTController {

    @Autowired
    private ChatGPTService chatGPTService;

//    @PostMapping("/api/chat")
//    public String sendMessageToChatGPT(@RequestBody String message) {
//        return chatGPTService.getChatGPTResponse(message);
//    }


    @PostMapping("/api/chat")
    public String sendMessageToChatGPT(@RequestBody String message) {
        String response = chatGPTService.getChatGPTResponse("Hello, ChatGPT");
        System.out.println("ChatGPT Response: " + response);
        return response;
    }

}

