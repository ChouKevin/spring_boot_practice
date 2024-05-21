package com.example.demo.CRM.company.client.query;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientQryRes {
    Long id;
    Long companyId;
    String name;
}
