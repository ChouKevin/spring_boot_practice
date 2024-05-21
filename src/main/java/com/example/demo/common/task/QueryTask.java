package com.example.demo.common.task;

import java.util.List;

public interface QueryTask<IN, OUT> {
    List<OUT> query(IN in);
}
