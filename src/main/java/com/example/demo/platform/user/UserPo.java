package com.example.demo.platform.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@TableName("platform_user")
public class UserPo {
    @TableId(value = "id", type = IdType.AUTO)
    Long id;
    String name;
    String pwd;
    int permission;
}
