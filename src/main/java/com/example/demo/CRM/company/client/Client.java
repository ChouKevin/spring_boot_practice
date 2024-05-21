package com.example.demo.CRM.company.client;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Client {
    Long id;
    Long companyId;
    String name;
    String email;
    String phone;

    long createdAt;
    long createdBy;
    long updatedAt;
    long updatedBy;
}
