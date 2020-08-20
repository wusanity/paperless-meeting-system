package com.szsm.videomeeting.modules.system.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.szsm.videomeeting.model.entity.User;
import org.apache.ibatis.annotations.Select;

/**
 * <p> 系统管理-用户基础信息表 Mapper 接口 </p>
 *
 * @author: wuzhike
 * @date: 2020-07-30
 */
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from t_sys_user where id = #{id}")
    User selectUserById(Integer id);

}
