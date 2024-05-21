package com.example.demo.common.task;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OpResult {
    Long id;
    String name;
    boolean succ;

    // succ or fail msg
    // String msg;
}
