package com.example.demo.platform.user;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.aop.LogExecutionTime;
import com.example.demo.platform.user.query.QueryReq;
import com.example.demo.platform.user.query.QueryRes;
import com.example.demo.platform.user.query.UserQuery;
import com.example.demo.platform.user.register.RegisterUser;
import com.example.demo.platform.user.register.RegisterUserReq;
import com.example.demo.platform.user.register.RegisterUserRes;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/platform")
@RequiredArgsConstructor
@Validated
public class UserController {

    final RegisterUser register;
    final UserQuery queryUsers;
    
    @PreAuthorize("hasAuthority('ROOT')")
    @PostMapping("/users")
    @LogExecutionTime
    public RegisterUserRes create(@RequestBody @Valid RegisterUserReq req) {
        return register.run(req);
    }

    @PreAuthorize("hasAuthority('ROOT')")
    @GetMapping("/users")
    public List<QueryRes> search(@ModelAttribute @Valid QueryReq req) {
        return queryUsers.query(req);
    }
}
