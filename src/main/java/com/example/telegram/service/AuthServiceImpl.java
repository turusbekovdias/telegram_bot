package com.example.telegram.service;

import com.example.telegram.dto.RegisterDto;
import com.example.telegram.model.TelegramResult;
import com.example.telegram.model.TelegramUpdates;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class AuthServiceImpl implements  AuthService {

    @Value("${telegram.token}")
    private String token;

    @Override
    public Object registration(RegisterDto dto) {
        try {
            String jsonString = new String(
                    Files.readAllBytes(
                            Paths.get(dto.getLogin() + ".json")
                    )
            );
            return "Клиент с данным login уже существует";
        } catch (IOException e) {

            Long id = checkUsernameIntoTelegram(dto.getLogin());

            if (id == null) {
                return "напишите боту @dias_turusbekov_telegram_bot, не нашли чат с " + dto.getLogin();
            }

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", dto.getName());
            jsonObject.put("login", dto.getLogin());
            jsonObject.put("password", dto.getPassword());
            jsonObject.put("token", id);



            try (FileWriter file = new FileWriter(dto.getLogin() + ".json")) {
                file.write(jsonObject.toString(2)); // Use toString(2) for pretty printing
                System.out.println("JSON data written to data.json");
                return "chat id is " + id;
            } catch (IOException ex) {
                ex.printStackTrace();
                return "Something wrong";
            }
        }
    }

    @Override
    public Object signIn(String login, String password) {
        try {
            String jsonString = new String(
                    Files.readAllBytes(
                            Paths.get(login + ".json")
                    )
            );
            JSONObject jsonObject = new JSONObject(jsonString);
            if (jsonObject.get("password").equals(password)) {
                return String.valueOf(jsonObject.get("token"));
            } else {
                return "Password not right!";
            }
        } catch (IOException e) {
            return "Not found!";
        }

    }


    public Long checkUsernameIntoTelegram(String login) {
        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://api.telegram.org/bot" + token + "/getUpdates").newBuilder();
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                return null;
            }
            System.out.println(response.peekBody(99999L).string());
            ObjectMapper objectMapper = new ObjectMapper();
            TelegramUpdates updates = objectMapper.readValue(response.peekBody(99999L).string(), TelegramUpdates.class);

            for (TelegramResult result: updates.getResult()) {
                if (result.getMessage().getChat().getUsername().equals(login)) {
                    return result.getMessage().getChat().getId();
                }
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
