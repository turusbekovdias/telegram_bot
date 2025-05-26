package com.example.telegram.model;

import lombok.Getter;
import lombok.Setter;

public class TelegramResult {
    private Long update_id;
    private TelegramMessage message;
    private Object my_chat_member;
    private Object channel_post;

    public Object getChannel_post() {
        return channel_post;
    }

    public void setChannel_post(Object channel_post) {
        this.channel_post = channel_post;
    }

    public Object getMy_chat_member() {
        return my_chat_member;
    }

    public void setMy_chat_member(Object my_chat_member) {
        this.my_chat_member = my_chat_member;
    }

    public Long getUpdate_id() {
        return update_id;
    }

    public void setUpdate_id(Long update_id) {
        this.update_id = update_id;
    }

    public TelegramMessage getMessage() {
        return message;
    }

    public void setMessage(TelegramMessage message) {
        this.message = message;
    }
}
