package com.example.demo.CRM.company.delete;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.CRM.company.CompanyService;
import com.example.demo.common.task.OpResult;
import com.example.demo.common.task.Task;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DelCompany implements Task<CompanyDelReq, CompanyDelRes> {

    @Autowired
    CompanyService companyService;

    @Override
    public CompanyDelRes run(CompanyDelReq in) {
        List<OpResult> opResults = new ArrayList<>();
        for (long id : in.ids) {
            OpResult res;
            try {
                res = companyService.delete(id);
                opResults.add(res);
            } catch (Exception e) {
                log.error("delete company id(%s) fail".formatted(id), e);
                res = OpResult.builder().id(id).succ(false).build();
            }
            opResults.add(res);
        }
        return new CompanyDelRes(opResults);
    }
}
