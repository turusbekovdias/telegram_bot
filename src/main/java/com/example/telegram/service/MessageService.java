package com.example.telegram.service;

public interface MessageService {

    boolean sendMessage(String text);
    boolean sendMessageByChatId(String chatId, String message);
}
