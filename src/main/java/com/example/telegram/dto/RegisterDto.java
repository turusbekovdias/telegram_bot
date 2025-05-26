package com.example.telegram.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDto {

    private String login;
    private String password;
    private String name;

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
