package com.example.demo.CRM.company.client.register;

import java.util.List;

import com.example.demo.common.task.OpResult;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterClientRes {
    List<OpResult> results;
}
