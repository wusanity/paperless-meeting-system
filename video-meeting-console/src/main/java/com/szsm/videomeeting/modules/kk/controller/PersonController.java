package com.szsm.videomeeting.modules.kk.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.szsm.videomeeting.base.context.ApiResult;
import com.szsm.videomeeting.model.dto.PersonDTO;
import com.szsm.videomeeting.model.entity.Person;
import com.szsm.videomeeting.modules.kk.enums.PersonErrorEnums;
import com.szsm.videomeeting.modules.kk.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/person")
@Slf4j
public class PersonController {

    @Autowired
    private PersonService personService;


    @RequestMapping("/getList")
    @ResponseBody
    public ApiResult getList(PersonDTO personDTO){
        log.error("111:{},{}","zhangsan","lisi");
        if (personDTO.getName() == null){
            return ApiResult.fail(PersonErrorEnums.PARAM_MISS);
        }
        Page<Person> page = new Page<>(personDTO.getPage(), personDTO.getLimit());
        personService.getList(page,personDTO);
        return ApiResult.successTable(page);
    }

    public static void main(String[] args) {
        String format = String.format("你好：世界：{}，{}",1, 21);
        System.out.println(format);
    }
}
