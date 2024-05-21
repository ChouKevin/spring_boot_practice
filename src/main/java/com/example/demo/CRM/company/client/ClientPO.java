package com.example.demo.CRM.company.client;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo.common.BasePo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@TableName("client")
@EqualsAndHashCode(callSuper = true)
public class ClientPO extends BasePo {
    Long companyId;
    String name;
    String email;
    String phone;
}
