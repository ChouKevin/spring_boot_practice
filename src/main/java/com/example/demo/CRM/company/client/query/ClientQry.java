package com.example.demo.CRM.company.client.query;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.demo.CRM.company.client.Client;
import com.example.demo.CRM.company.client.ClientMapper;
import com.example.demo.CRM.company.client.ClientPO;
import com.example.demo.CRM.company.client.ClientService;
import com.example.demo.common.task.QueryTask;

@Component
public class ClientQry implements QueryTask<ClientQryReq, ClientQryRes> {

    @Autowired ClientMapper clientMapper;

    @Override
    public List<ClientQryRes> query(ClientQryReq in) {
        throw new RuntimeException();
    }

    private ClientQryRes convert(Client client) {
        return ClientQryRes.builder()
        .id(client.getId())
        .companyId(client.getCompanyId())
        .name(client.getName())
        .build();
    }
    
}
