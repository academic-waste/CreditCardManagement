package com.boot.rest.CreditCardManagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/chat")
    public String chatPage() {
        //return "forward:/chat.html";
        return "chat";
    }
}
