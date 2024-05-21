package com.example.demo.platform.user.query;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QueryReq {
    Long id;
    String name;
}
