package com.example.demo.CRM.company.update;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CompanyUpdateReq {
    @NotNull
    Long id;

    String name;
    
    String addr;
}
