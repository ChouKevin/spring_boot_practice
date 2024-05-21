package com.example.demo.CRM.company.query;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompanyQryRes {

    Long id;
    String name;
    String address;

    String updatedBy;
    String createdBy;

    LocalDateTime updatedAt;
    LocalDateTime createdAt;
}
