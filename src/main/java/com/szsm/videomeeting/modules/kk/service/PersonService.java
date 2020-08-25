package com.szsm.videomeeting.modules.kk.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.szsm.videomeeting.modules.kk.model.dto.PersonDTO;
import com.szsm.videomeeting.modules.kk.model.entity.Person;

public interface PersonService extends IService<Person> {

    Page<Person> getList(Page<Person> page, PersonDTO person);
}
