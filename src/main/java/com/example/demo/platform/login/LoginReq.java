package com.example.demo.platform.login;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginReq {

    @NotBlank
    String name;

    @NotNull
    @Length(min = 4, max = 50)
    String password;
}
