package com.szsm.meeting.base.config;


import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {

        log.info("start insert fill ....");
        this.setFieldValByName("createTime",new Date(),metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
        this.setFieldValByName("deleted", 0, metaObject);
       /* this.setFieldValByName("createBy", 0, metaObject);
        this.setFieldValByName("updateBy", 0, metaObject);*/
    }

    @Override
    public void updateFill(MetaObject metaObject) {

        log.info("start update fill ....");
        this.setFieldValByName("updateTime", new Date(),metaObject);
    }
}
