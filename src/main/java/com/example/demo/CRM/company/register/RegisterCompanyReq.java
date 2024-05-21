package com.example.demo.CRM.company.register;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterCompanyReq {

    @Data
    public static class CompanyInfo {
        @NotBlank
        String name;
    
        @NotBlank
        String addr;
    }

    List<CompanyInfo> data;
}
