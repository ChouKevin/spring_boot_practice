package com.example.demo.CRM.company.register;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.CRM.company.Company;
import com.example.demo.CRM.company.CompanyService;
import com.example.demo.common.task.OpResult;
import com.example.demo.common.task.Task;

@Component
public class RegisterCompany implements Task<RegisterCompanyReq, RegisterCompanyRes> {

    @Autowired
    CompanyService companyService;

    @Override
    public RegisterCompanyRes run(RegisterCompanyReq in) {
        List<Company> companies = in.data.stream().map(this::convert).toList();
        List<OpResult> res = companyService.register(companies);
        return new RegisterCompanyRes(res);
    }

    private Company convert(RegisterCompanyReq.CompanyInfo req) {
        return Company.builder()
            .name(req.name)
            .addr(req.addr)
            .build();
    }
    
}
