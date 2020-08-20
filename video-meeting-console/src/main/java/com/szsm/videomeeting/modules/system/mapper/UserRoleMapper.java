package com.szsm.videomeeting.modules.system.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.szsm.videomeeting.model.entity.User;
import com.szsm.videomeeting.model.entity.UserRole;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * <p> 系统管理 - 用户角色关联表  Mapper 接口 </p>
 *
 * @author : wuzhike
 * @date : 2020-07-30
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

    @Select("select * from t_sys_user_role where role_id = #{roleId}")
    @Results({
                    @Result(property = "id",column = "id"),
                    @Result(property = "role_id",column = "roleId"),
                    @Result(property = "user_id",column = "userId"),
                    @Result(property = "create_time",column = "createTime"),
                    @Result(property = "update_time",column = "updateTime"),
                    @Result(property = "create_by",column = "createBy"),
                    @Result(property = "update_by",column = "updateBy"),
                    @Result(property = "deleted",column = "deleted"),
                    @Result(property = "user",column = "user_id",javaType = User.class,one = @One(select = "com.szsm.videomeeting.modules.system.mapper.UserMapper.selectUserById",fetchType= FetchType.EAGER))
            })
    List<UserRole> listByRole(Integer roleId);

}
