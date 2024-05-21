package com.example.demo.platform.user.register;

import java.util.List;

import com.example.demo.common.task.OpResult;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterUserRes {
    List<OpResult> results;
}
