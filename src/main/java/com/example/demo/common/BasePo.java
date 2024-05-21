package com.example.demo.common;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;

@Data
public class BasePo {
    @TableId(type = IdType.AUTO)
    Long id;
    @TableField(fill = FieldFill.INSERT)
    Long createdBy;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    Long updatedBy;
    
    @TableField(fill = FieldFill.INSERT)
    LocalDateTime createdAt;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    LocalDateTime updatedAt;
}
