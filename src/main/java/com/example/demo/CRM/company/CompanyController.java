package com.example.demo.CRM.company;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.CRM.company.delete.CompanyDelReq;
import com.example.demo.CRM.company.delete.CompanyDelRes;
import com.example.demo.CRM.company.delete.DelCompany;
import com.example.demo.CRM.company.query.CompanyQry;
import com.example.demo.CRM.company.query.CompanyQryReq;
import com.example.demo.CRM.company.query.CompanyQryRes;
import com.example.demo.CRM.company.register.RegisterCompany;
import com.example.demo.CRM.company.register.RegisterCompanyReq;
import com.example.demo.CRM.company.register.RegisterCompanyRes;
import com.example.demo.CRM.company.update.CompanyUpdateReq;
import com.example.demo.CRM.company.update.CompanyUpdateRes;
import com.example.demo.CRM.company.update.UpdateCompany;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/companies")
@Validated
public class CompanyController {
    
    @Autowired RegisterCompany registerCompany;
    @Autowired CompanyQry companyQry;
    @Autowired DelCompany delCompany;
    @Autowired UpdateCompany updateCompany;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('SUPER_USER', 'OPERATOR')")
    public RegisterCompanyRes register(
            @RequestBody @Valid RegisterCompanyReq reqList
        ) {
        return registerCompany.run(reqList);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('SUPER_USER', 'OPERATOR', 'MANAGER')")
    public List<CompanyQryRes> query(
            @ModelAttribute @Valid CompanyQryReq reqList
        ) {
        return companyQry.query(reqList);
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('SUPER_USER', 'MANAGER')")
    public CompanyUpdateRes update(
            @RequestBody @Valid CompanyUpdateReq req
        ) {
        return updateCompany.run(req);
    }

    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('SUPER_USER', 'MANAGER')")
    public CompanyDelRes delete(CompanyDelReq req) {
        return delCompany.run(req);
    }
}
