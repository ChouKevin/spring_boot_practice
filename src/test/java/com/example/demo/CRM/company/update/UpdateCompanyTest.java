package com.example.demo.CRM.company.update;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.CRM.company.query.CompanyQry;
import com.example.demo.CRM.company.register.RegisterCompany;
import com.example.demo.CRM.company.register.RegisterCompanyReq;
import com.example.demo.CRM.company.register.RegisterCompanyRes;
import com.example.demo.CRM.company.register.RegisterCompanyReq.CompanyInfo;
import com.example.demo.common.UserHolder;
import com.example.demo.common.task.OpResult;
import com.example.demo.platform.user.UserService;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UpdateCompanyTest {

    @Autowired
    UpdateCompany updateCompany;
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
    public void update_prev_name_to_exists_name() {
        OpResult src = registeredCompanies.getResList().get(0);
        OpResult target = registeredCompanies.getResList().get(1);
        CompanyUpdateReq req = new CompanyUpdateReq();
        req.setId(src.getId());
        req.setName(target.getName());
        assertFalse(updateCompany.run(req).isSucc());
    }

    @Test
    public void update_addr() {
        OpResult src = registeredCompanies.getResList().get(0);
        CompanyUpdateReq req = new CompanyUpdateReq();
        req.setId(src.getId());
        req.setAddr("dasdasdasdasdasdas");
        assertTrue(updateCompany.run(req).isSucc());
    }

    @Test
    public void update_without_any_val() {
        OpResult src = registeredCompanies.getResList().get(0);
        CompanyUpdateReq req = new CompanyUpdateReq();
        req.setId(src.getId());
        assertTrue(updateCompany.run(req).isSucc());
    }
}
