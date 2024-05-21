package com.example.demo.platform.user.register;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.common.task.OpResult;
import com.example.demo.common.task.Task;
import com.example.demo.platform.user.User;
import com.example.demo.platform.user.UserService;

@Component
public class RegisterUser implements Task<RegisterUserReq, RegisterUserRes> {

    @Autowired
    UserService userService;
    
    @Override
    public RegisterUserRes run(RegisterUserReq in) {
        List<User> users = in.data.stream().map(this::convert).toList();
        List<OpResult> created = userService.create(users);
        return new RegisterUserRes(created);
    }
    
    public User convert(RegisterUserReq.UserInfo req) {
        return User.builder()
                    .name(req.name)
                    .password(req.pwd)
                    .permission(req.permission)
                    .build();
    }
}
