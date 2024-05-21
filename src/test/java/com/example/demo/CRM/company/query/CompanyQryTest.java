package com.example.demo.CRM.company.query;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.Auth.Permission;
import com.example.demo.CRM.company.register.RegisterCompany;
import com.example.demo.CRM.company.register.RegisterCompanyReq;
import com.example.demo.CRM.company.register.RegisterCompanyReq.CompanyInfo;
import com.example.demo.common.UserHolder;
import com.example.demo.platform.user.User;
import com.example.demo.platform.user.register.RegisterUser;
import com.example.demo.platform.user.register.RegisterUserReq;
import com.example.demo.platform.user.register.RegisterUserReq.UserInfo;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CompanyQryTest {

    @Autowired
    CompanyQry qry;

    @Autowired
    RegisterCompany registerCompany;

    @Autowired
    RegisterUser registerUser;

    @MockBean
    UserHolder userHolder;
    User creater = User.builder().id(1)
                        .name("creater")
                        .password("sdasdasdasdasda")
                        .permission(Permission.SUPER_USER)
                        .build();
    final int MAX_COMPANY_SIZE = 20;
    @BeforeAll
    void before() {
        RegisterUserReq.UserInfo info = new UserInfo();
        info.setName(creater.getName());
        info.setPwd(creater.getPassword());
        info.setPermission(creater.getPermission());
        RegisterUserReq req = new RegisterUserReq(Collections.singletonList(info));
        assertTrue(registerUser.run(req).getResults().get(0).isSucc());
        when(userHolder.get()).thenReturn(creater);
        List<RegisterCompanyReq.CompanyInfo> rcq = new ArrayList<>();

        for (int i = 0; i < MAX_COMPANY_SIZE; i++) {
            RegisterCompanyReq.CompanyInfo cinfo = new CompanyInfo();
            cinfo.setAddr("addr"+i);
            cinfo.setName("company"+i);
            rcq.add(cinfo);
        }
        registerCompany.run(new RegisterCompanyReq(rcq));
    }

    @Test
    void query_without_condition() {
        CompanyQryReq req = new CompanyQryReq();
        List<CompanyQryRes> resList = qry.query(req);
        assertEquals(resList.size(), MAX_COMPANY_SIZE);
    }

    @Test
    void query_with_exists_name() {
        CompanyQryReq req = new CompanyQryReq();
        req.setName("company10");
        assertEquals(qry.query(req).size(), 1);
    }

    @Test
    void query_with_not_exists_name() {
        CompanyQryReq req = new CompanyQryReq();
        req.setName("company100000000000");
        assertEquals(qry.query(req).size(), 0);
    }
    // TODO
}
