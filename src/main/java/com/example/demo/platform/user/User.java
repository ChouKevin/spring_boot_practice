package com.example.demo.platform.user;

import com.example.demo.Auth.Permission;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class User {
    long id;
    String name;
    String password;
    Permission permission;
}
