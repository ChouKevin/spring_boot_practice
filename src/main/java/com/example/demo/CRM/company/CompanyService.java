package com.example.demo.CRM.company;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.demo.CRM.company.client.ClientMapper;
import com.example.demo.common.task.OpResult;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CompanyService {

    @Autowired CompanyMapper companyMapper;
    @Autowired ClientMapper clientMapper;

    public List<OpResult> register(List<Company> companies) {
        List<OpResult> results = new ArrayList<>();
        for (Company company : companies) {
            CompanyPo companyPo = this.convert(company);
            OpResult.OpResultBuilder builder = OpResult.builder();
            builder.name(company.name);
            try {
                if (companyMapper.isCompanyNameExists(company.name)) {
                    builder.succ(false);
                } else {
                    int count = companyMapper.insert(companyPo);
                    builder.id(companyPo.getId());
                    builder.succ(count > 0);
                }
            } catch (Exception e) {
                log.info("register company(%s) fail".formatted(company.name), e);
                builder.succ(false);
            }
            results.add(builder.build());
        }
        return results;
    }

    @Transactional
    public OpResult delete(Long id) {
        OpResult.OpResultBuilder builder = OpResult.builder();
        builder.id(id);
        CompanyPo companyPo = companyMapper.selectByIdForUpdate(id);
        if (companyPo == null || clientMapper.isCompanyIdExists(id)) {
            builder.succ(false);
        } else {
            int count = companyMapper.deleteById(id);
            builder.succ(count > 0);
        }
        return builder.build();
    }

    @Transactional
    public int update(Company company) {
        CompanyPo prev = this.companyMapper.selectById(company.id);
        if (prev == null) return 0;
        CompanyPo newOne = CompanyPo.builder().build();
        newOne.setId(prev.getId());
        if (StringUtils.isNotBlank(company.addr) && !company.addr.equals(prev.address)) {
            newOne.address = company.addr;
        }
        if (StringUtils.isNotBlank(company.name)) {
            if (this.companyMapper.isCompanyNameExists(company.name)) return 0;
            if (!company.name.equals(prev.name)) newOne.name = company.name;
        }

        return companyMapper.updateById(newOne);
    }
    
    private CompanyPo convert(Company company) {
        return CompanyPo.builder()
        .name(company.name)
        .address(company.addr)
        .build();
    }
}
