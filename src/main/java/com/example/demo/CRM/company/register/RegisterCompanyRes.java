package com.example.demo.CRM.company.register;

import java.util.List;

import com.example.demo.common.task.OpResult;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterCompanyRes {
    List<OpResult> resList;
}
