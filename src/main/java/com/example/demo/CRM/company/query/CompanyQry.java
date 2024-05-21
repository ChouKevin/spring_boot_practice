package com.example.demo.CRM.company.query;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.demo.CRM.company.CompanyMapper;
import com.example.demo.CRM.company.CompanyPo;
import com.example.demo.common.task.QueryTask;
import com.example.demo.platform.user.UserPo;
import com.github.yulichang.wrapper.MPJLambdaWrapper;

@Component
public class CompanyQry implements QueryTask<CompanyQryReq, CompanyQryRes> {

    @Autowired CompanyMapper companyMapper;

    @Override
    public List<CompanyQryRes> query(CompanyQryReq in) {
        MPJLambdaWrapper<CompanyPo> query = new MPJLambdaWrapper<CompanyPo>();
        query.selectAsClass(CompanyPo.class, CompanyQryRes.class)
            .selectAs("u1", UserPo::getName, CompanyQryRes::getCreatedBy)
            .selectAs("u2", UserPo::getName, CompanyQryRes::getUpdatedBy)
            .leftJoin(UserPo.class, "u1", UserPo::getId, CompanyPo::getCreatedBy)
            .leftJoin(UserPo.class, "u2", UserPo::getId, CompanyPo::getUpdatedBy)
            .eq(in.id != null, CompanyPo::getId, in.id)
            .eq(StringUtils.isNotBlank(in.name), CompanyPo::getName, in.name)
            .like(StringUtils.isNotBlank(in.addr), CompanyPo::getAddress, in.addr)
            
            .checkSqlInjection()
            ;
        return companyMapper.selectJoinList(CompanyQryRes.class, query);
    }
    
}
