package com.example.demo.platform.user.register;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.example.demo.Auth.Permission;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserReq {

    @Data
    public static class UserInfo {
        @NotBlank
        @Size(min = 4, max = 50)
        String name;

        @NotBlank
        @Length(min = 4, max = 50, message = "太短")
        String pwd;
        
        Permission permission;
    }

    @Valid
    List<UserInfo> data;
}
