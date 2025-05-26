package com.example.telegram.controller;

import com.example.telegram.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping(value = "/defaultMessage")
    public boolean sendMessage (@RequestParam("message") String message) {
        return messageService.sendMessage(message);
    }

    @GetMapping(value = "/sendMessage")
    public boolean sendMessageByChatId(@RequestParam("chatId") String chatId,
                                       @RequestParam("message") String message) {
        return messageService.sendMessageByChatId(chatId, message);
    }

}
