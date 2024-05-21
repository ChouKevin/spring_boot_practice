package com.example.demo.CRM.company.client.register;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.CRM.company.client.Client;
import com.example.demo.CRM.company.client.ClientService;
import com.example.demo.CRM.company.client.register.RegisterClientReq.ClientInfo;
import com.example.demo.common.task.OpResult;
import com.example.demo.common.task.Task;

@Component
public class RegisterClient implements Task<RegisterClientReq, RegisterClientRes> {

    @Autowired ClientService clientService;

    @Override
    public RegisterClientRes run(RegisterClientReq in) {
        List<OpResult> resList = new ArrayList<>();
        Long companyId = in.companyId;
        for (ClientInfo info : in.clientInfos) {
            Client client = this.convert(companyId, info);
            OpResult res = clientService.register(client);
            resList.add(res);
        }
        return new RegisterClientRes(resList);
    }
    private Client convert(Long companyId, RegisterClientReq.ClientInfo info) {
        return Client.builder()
        .companyId(companyId)
        .email(info.getEmail())
        .name(info.name)
        .phone(info.phone)
        .build();
    }

}
