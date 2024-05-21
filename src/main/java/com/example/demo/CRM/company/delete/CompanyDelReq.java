package com.example.demo.CRM.company.delete;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompanyDelReq {
    List<Long> ids;
}
