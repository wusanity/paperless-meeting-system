package com.szsm.videomeeting.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.szsm.videomeeting.model.entity.Menu;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * <p> 系统管理-权限表  Mapper 接口 </p>
 *
 * @author : wuzhike
 * @date : 2020-07-30
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据角色ID查询用户权限
     *
     * @param roleId:
     * @return: java.util.List<com.wuzhike.modules.system.entity.Menu>
     */
    @Select("SELECT sm.* FROM t_sys_menu sm\n" +
            "\t    LEFT JOIN t_sys_role_menu srm ON sm.id = srm.menu_id\n" +
            "\t    WHERE srm.role_id = #{roleId}")
    List<Menu> selectMenuByRoleId(@Param("roleId") Integer roleId);

}
