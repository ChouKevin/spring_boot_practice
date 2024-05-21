package com.example.demo.platform.user.query;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.demo.Auth.Permission;
import com.example.demo.common.task.QueryTask;
import com.example.demo.platform.user.UserMapper;
import com.example.demo.platform.user.UserPo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserQuery implements QueryTask<QueryReq, QueryRes> {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<QueryRes> query(QueryReq in) {
        log.info("rec %s".formatted(in.toString()));
        LambdaQueryWrapper<UserPo> query = new LambdaQueryWrapper<>();
        query.eq(in.getId() != null, UserPo::getId, in.id)
            .eq(StringUtils.isNotBlank(in.name), UserPo::getName, in.name);
        List<UserPo> userPos = userMapper.selectList(query);
        return userPos.stream().map(this::convert).toList();
    }

    private QueryRes convert(UserPo user) {
        return QueryRes.builder()
            .id(user.getId())
            .name(user.getName())
            .permission(Permission.fromCode(user.getPermission()).name())
            .build();
    }
}
