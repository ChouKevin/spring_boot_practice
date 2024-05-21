package com.example.demo.CRM.company.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.CRM.company.CompanyMapper;
import com.example.demo.common.task.OpResult;


@Service
public class ClientService {

    @Autowired
    ClientMapper clientMapper;
    @Autowired
    CompanyMapper companyMapper;

    public OpResult register(Client client) {
        OpResult.OpResultBuilder builder = OpResult.builder();
        if (clientMapper.isClientExists(client.getCompanyId(), client.getName())
        || companyMapper.selectByIdForUpdate(client.companyId) == null
        ) {
            return builder.succ(false).build();
        }
        ClientPO clientPO = new ClientPO();
        clientPO.setCompanyId(client.companyId);
        clientPO.setName(client.name);
        clientPO.setPhone(client.phone);
        clientPO.setEmail(client.email);
        
        int count = clientMapper.insert(clientPO);
        return builder
                .id(clientPO.getId())
                .name(client.name)
                .succ(count > 0)
                .build();
    }

    private Client convert(ClientPO clientPO) {
        return Client.builder()
                .id(clientPO.getId())
                .companyId(clientPO.companyId)
                .name(clientPO.name)
                .email(clientPO.email)
                .phone(clientPO.phone)
                .build();
    }
}
