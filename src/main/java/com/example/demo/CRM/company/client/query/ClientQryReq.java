package com.example.demo.CRM.company.client.query;

import lombok.Data;

@Data
public class ClientQryReq {
    Long id;
    Long companyId;
    String name;
}
