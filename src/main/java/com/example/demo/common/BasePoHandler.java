package com.example.demo.common;

import java.time.LocalDateTime;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.example.demo.platform.user.User;

@Component
public class BasePoHandler implements MetaObjectHandler {

    @Autowired
    UserHolder userHolder;

    @Override
    public void insertFill(MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now();
        User user = userHolder.get();
        this.strictInsertFill(metaObject, "createdAt", LocalDateTime.class, now);
        this.strictInsertFill(metaObject, "updatedAt", LocalDateTime.class, now);
        
        this.strictInsertFill(metaObject, "createdBy", Long.class, user.getId());
        this.strictInsertFill(metaObject, "updatedBy", Long.class, user.getId());

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        User user = userHolder.get();
        LocalDateTime now = LocalDateTime.now();
        this.strictInsertFill(metaObject, "updatedAt", LocalDateTime.class, now);
        this.strictInsertFill(metaObject, "updatedBy", Long.class, user.getId());
    }
    
}
