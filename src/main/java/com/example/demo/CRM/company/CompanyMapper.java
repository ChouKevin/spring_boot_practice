package com.example.demo.CRM.company;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.github.yulichang.base.MPJBaseMapper;

public interface CompanyMapper extends MPJBaseMapper<CompanyPo> {

    @Select("select exists(select 1 from company where name = #{name})")
    boolean isCompanyNameExists(@Param("name") String name);

    @Select("select * from company where id=${id} for update")
    CompanyPo selectByIdForUpdate(@Param("id") Long id);
}
