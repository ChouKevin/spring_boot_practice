package com.example.demo.common;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.demo.Auth.UserDetailsImp;
import com.example.demo.platform.user.User;

@Component
public class UserHolder {

    public User get() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImp details = (UserDetailsImp) auth.getPrincipal();

        return User.builder()
            .id(details.getId())
            .name(details.getName())
            .password(details.getPassword())
            .permission(details.getPermission())
            .build();
    }
}
