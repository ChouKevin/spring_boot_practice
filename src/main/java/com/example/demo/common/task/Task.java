package com.example.demo.common.task;

public interface Task<IN, OUT> {
    OUT run(IN in); 
}
