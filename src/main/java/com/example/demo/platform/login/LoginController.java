package com.example.demo.platform.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/platform")
@Validated
public class LoginController {
    
    @Autowired
    LoginService loginService;

    @PostMapping(value = "/login", 
                consumes = MediaType.APPLICATION_JSON_VALUE, 
                produces = MediaType.APPLICATION_JSON_VALUE)
    public LoginRes login(@Valid @RequestBody LoginReq data) {
        return loginService.login(data.name, data.password);
    }
}
