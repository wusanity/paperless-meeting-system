package com.szsm.meeting.modules.kk.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.szsm.meeting.modules.kk.model.dto.PersonDTO;
import com.szsm.meeting.modules.kk.model.entity.Person;
import com.szsm.meeting.modules.kk.model.dto.PersonDTO;
import com.szsm.meeting.modules.kk.model.entity.Person;

public interface PersonService extends IService<Person> {

    Page<Person> getList(Page<Person> page, PersonDTO person);
}
