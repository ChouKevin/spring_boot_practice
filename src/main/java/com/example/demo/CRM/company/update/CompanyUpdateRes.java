package com.example.demo.CRM.company.update;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompanyUpdateRes {
    Long id;
    boolean succ;
}
