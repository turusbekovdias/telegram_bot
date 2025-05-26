package com.example.telegram.controller;

import com.example.telegram.dto.RegisterDto;
import com.example.telegram.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(value = "/register")
    public Object registration(@RequestBody RegisterDto regisDto) {
        return authService.registration(regisDto);
    }

    @GetMapping(value = "/sign-in")
    public Object signIn(@RequestParam("login") String login,
                         @RequestParam("password") String password) {

        return authService.signIn(login, password);
    }

}
