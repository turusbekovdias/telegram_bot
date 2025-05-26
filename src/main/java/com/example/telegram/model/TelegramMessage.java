package com.example.telegram.model;

import lombok.Getter;
import lombok.Setter;

public class TelegramMessage {

    private long message_id;
    private Object from;
    private long date;
    private String text;
    private TelegramChat chat;

    public long getMessage_id() {
        return message_id;
    }

    public void setMessage_id(long message_id) {
        this.message_id = message_id;
    }

    public Object getFrom() {
        return from;
    }

    public void setFrom(Object from) {
        this.from = from;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public TelegramChat getChat() {
        return chat;
    }

    public void setChat(TelegramChat chat) {
        this.chat = chat;
    }
}
