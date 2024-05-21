package com.example.demo.CRM.company.update;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.CRM.company.Company;
import com.example.demo.CRM.company.CompanyService;
import com.example.demo.common.task.Task;

@Component
public class UpdateCompany implements Task<CompanyUpdateReq, CompanyUpdateRes> {

    @Autowired
    CompanyService companyService;

    @Override
    public CompanyUpdateRes run(CompanyUpdateReq in) {
        int count = companyService.update(this.convert(in));
        return CompanyUpdateRes.builder()
                .id(in.getId())
                .succ(count > 0)
                .build();
    }
    
    private Company convert(CompanyUpdateReq in) {
        return Company.builder()
            .id(in.id)
            .addr(in.addr)
            .name(in.name)
            .build();
    }
}
