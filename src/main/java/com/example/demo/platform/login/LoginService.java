package com.example.demo.platform.login;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Auth.JwtService;
import com.example.demo.platform.user.User;
import com.example.demo.platform.user.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

    final UserService userService;
    final BCryptPasswordEncoder encoder;
    final JwtService jwtService;


    public LoginRes login(String name, String pwd) {
        User user = userService.findByName(name);
        if (user != null && encoder.matches(pwd, user.getPassword())) {
            log.info("%s login successful".formatted(name));
            return new LoginRes(jwtService.generateToken(name));
        } else {
            throw new RuntimeException("%s login fail".formatted(name));
        }
    }
}
