package com.boot.rest.CreditCardManagement.controller;

import com.boot.rest.CreditCardManagement.service.ChatGPTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ChatGPTController {

    @Autowired
    private ChatGPTService chatGPTService;

    @PostMapping("/api/chat")
    public String sendMessageToChatGPT(@RequestBody String message) throws Exception {
        String response = chatGPTService.getChatGPTResponse(message);
        System.out.println("ChatGPT Response: " + response);
        return response;
    }


}
