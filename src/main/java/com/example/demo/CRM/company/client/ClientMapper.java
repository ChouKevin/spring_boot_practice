package com.example.demo.CRM.company.client;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.github.yulichang.base.MPJBaseMapper;

public interface ClientMapper extends MPJBaseMapper<ClientPO> {

    @Select("select exists(select 1 from client where companyId=#{id})")
    boolean isCompanyIdExists(@Param("id") Long id);

    @Select("select exists(select 1 from client where company_id=#{companyId} and name=#{name})")
    boolean isClientExists(@Param("companyId") Long companyId, @Param("name") String name);
}
