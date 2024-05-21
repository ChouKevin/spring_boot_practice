package com.example.demo.platform.user.query;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QueryRes {
    Long id;
    String name;
    String permission;
}
