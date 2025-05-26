package com.example.telegram.service;

import com.example.telegram.dto.RegisterDto;

public interface AuthService {

    Object registration(RegisterDto dto);
    Object signIn(String login, String password);
}
