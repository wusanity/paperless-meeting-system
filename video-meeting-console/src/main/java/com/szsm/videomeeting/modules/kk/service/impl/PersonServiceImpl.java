package com.szsm.videomeeting.modules.kk.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.szsm.videomeeting.base.exception.MyException;
import com.szsm.videomeeting.model.dto.PersonDTO;
import com.szsm.videomeeting.model.entity.Person;
import com.szsm.videomeeting.modules.kk.enums.PersonErrorEnums;
import com.szsm.videomeeting.modules.kk.mapper.PersonMapper;
import com.szsm.videomeeting.modules.kk.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements PersonService {

    @Autowired
    private PersonMapper userMapper;

    @Override
    public Page<Person> getList(Page<Person> page, PersonDTO person) {
//        throw new MyException(PersonErrorEnums.PASSWORD_ERROR);

        return page.setRecords(userMapper.getList(page,person));
    }
}
