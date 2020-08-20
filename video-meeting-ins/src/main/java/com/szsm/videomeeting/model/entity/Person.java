package com.szsm.videomeeting.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.szsm.videomeeting.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("person")
public class Person extends BaseEntity implements Serializable{


    private static final long serialVersionUID = -6320009684551881187L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    private Integer age;

    private String email;
    @Version
    private Integer version;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT)
    private Date updateTime;

    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private Long createBy;

    @TableField(value = "deleted")
    @TableLogic
    private Integer deleted;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
