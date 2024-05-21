package com.example.demo.CRM.company.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.CRM.company.client.query.ClientQry;
import com.example.demo.CRM.company.client.register.RegisterClient;
import com.example.demo.CRM.company.client.register.RegisterClientReq;
import com.example.demo.CRM.company.client.register.RegisterClientRes;

import jakarta.validation.constraints.Min;

@Validated
@RestController
@RequestMapping("/companies/{companyId}/clients")
public class ClientController {

    @Autowired ClientQry queryClient;
    @Autowired RegisterClient registerClient;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('SUPER_USER', 'OPERATOR')")
    public RegisterClientRes register(
                @PathVariable @Min(0) Long companyId, 
                @RequestBody RegisterClientReq req) {
        req.setCompanyId(companyId);
        return registerClient.run(req);
    }
}
