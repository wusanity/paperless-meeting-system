package com.szsm.meeting.modules.system.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.szsm.meeting.modules.system.model.entity.Role;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p> 系统管理-角色表  Mapper 接口 </p>
 *
 * @author : wuzhike
 * @date : 2020-07-30
 */
public interface RoleMapper extends BaseMapper<Role> {


    @Select("select * from t_sys_role")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "code",column = "code"),
            @Result(property = "name",column = "name"),
            @Result(property = "remarks",column = "remarks"),
            @Result(property = "create_time",column = "createTime"),
            @Result(property = "update_time",column = "updateTime"),
            @Result(property = "create_by",column = "createBy"),
            @Result(property = "update_by",column = "updateBy"),
            @Result(property = "deleted",column = "deleted"),
            @Result(property = "userRoleList",column = "id",javaType = List.class,many = @Many(select = "com.szsm.meeting.modules.system.mapper.UserRoleMapper.listByRole"))
    })
    public List<Role> list();

}
