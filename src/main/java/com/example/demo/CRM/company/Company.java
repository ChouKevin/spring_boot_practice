package com.example.demo.CRM.company;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Company {
    Long id;
    String name;
    String addr;
}
