package com.example.telegram.service;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MessageServiceImpl implements MessageService{

    @Value("${telegram.chat_id}")
    private String chatId;

    @Value("${telegram.token}")
    private String token;

    @Override
    public boolean sendMessage(String text) {

        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://api.telegram.org/bot" + token + "/sendMessage").newBuilder();
        urlBuilder.addQueryParameter("chat_id", chatId);
        urlBuilder.addQueryParameter("text", text);
        String url = urlBuilder.build().toString();

        return sendToTelegramBot(url);

    }

    @Override
    public boolean sendMessageByChatId(String clientChatId, String text) {


        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://api.telegram.org/bot" + token + "/sendMessage").newBuilder();
        urlBuilder.addQueryParameter("chat_id", clientChatId);
        urlBuilder.addQueryParameter("text", text);
        String url = urlBuilder.build().toString();

        return sendToTelegramBot(url);

    }

    private boolean sendToTelegramBot(String url) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                return false;
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
