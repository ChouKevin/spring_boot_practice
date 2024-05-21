package com.example.demo.CRM.company.client.register;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.CRM.company.client.register.RegisterClientReq.ClientInfo;
import com.example.demo.CRM.company.query.CompanyQry;
import com.example.demo.CRM.company.register.RegisterCompany;
import com.example.demo.CRM.company.register.RegisterCompanyReq;
import com.example.demo.CRM.company.register.RegisterCompanyReq.CompanyInfo;
import com.example.demo.CRM.company.register.RegisterCompanyRes;
import com.example.demo.common.UserHolder;
import com.example.demo.common.task.OpResult;
import com.example.demo.platform.user.UserService;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class RegisterClientTest {

    @Autowired
    RegisterClient registerClient;
    @Autowired
    RegisterCompany registerCompany;
    @Autowired
    CompanyQry companyQry;
    @MockBean
    UserHolder userHolder;
    RegisterCompanyRes registeredCompanies;

    @BeforeAll
    void beforeAll() {
        when(userHolder.get()).thenReturn(UserService.getROOT());
        List<CompanyInfo> companyInfos = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            CompanyInfo info = new CompanyInfo();
            info.setAddr("addr"+i);
            info.setName("company"+i);
            companyInfos.add(info);
        }
        registeredCompanies = registerCompany.run(new RegisterCompanyReq(companyInfos));
        assertTrue(registeredCompanies.getResList().stream().allMatch(OpResult::isSucc));
    }

    @Test
    public void register_client_with_not_exists_company() {
        List<ClientInfo> clientInfos = new ArrayList<>();
        ClientInfo clientInfo = new ClientInfo();
        clientInfo.setName("hhhhhhhhhhh");
        clientInfo.setEmail("asdasdads@dsasd.asd");
        clientInfos.add(clientInfo);

        RegisterClientReq req = RegisterClientReq.builder()
        .companyId(-9999L)
        .clientInfos(clientInfos)
        .build();

        RegisterClientRes res = registerClient.run(req);
        assertFalse(res.getResults().get(0).isSucc());
    }

    @Test
    public void register_client_with_exists_company() {
        List<ClientInfo> clientInfos = new ArrayList<>();
        ClientInfo clientInfo = new ClientInfo();
        clientInfo.setName("hhhhhhhhhhh");
        clientInfo.setEmail("asdasdads@dsasd.asd");
        clientInfos.add(clientInfo);

        long validCompanyId = registeredCompanies.getResList().get(0).getId();
        RegisterClientReq req = RegisterClientReq.builder()
        .companyId(validCompanyId)
        .clientInfos(clientInfos)
        .build();

        RegisterClientRes res = registerClient.run(req);
        assertTrue(res.getResults().get(0).isSucc());
    }
    
}
