package com.szsm.videomeeting.modules.meeting.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.szsm.videomeeting.model.dto.PersonDTO;
import com.szsm.videomeeting.model.entity.Person;
import javafx.scene.control.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PersonMapper extends BaseMapper<Person> {

    List<Person> getList(Page page, @Param("filter") PersonDTO personDTO);


}
