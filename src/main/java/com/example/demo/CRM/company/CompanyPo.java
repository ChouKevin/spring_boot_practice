package com.example.demo.CRM.company;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo.common.BasePo;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@TableName("company")
@EqualsAndHashCode(callSuper = true)
public class CompanyPo extends BasePo {
    String name;
    String address;
}
