package com.szsm.videomeeting.modules.kk.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.szsm.videomeeting.model.dto.PersonDTO;
import com.szsm.videomeeting.model.entity.Person;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PersonMapper extends BaseMapper<Person> {

    List<Person> getList(Page page, @Param("filter") PersonDTO personDTO);


}
