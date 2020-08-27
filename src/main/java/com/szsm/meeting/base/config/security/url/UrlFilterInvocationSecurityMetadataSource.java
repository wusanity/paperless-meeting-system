package com.szsm.meeting.base.config.security.url;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.szsm.meeting.base.constant.Constants;
import com.szsm.meeting.modules.system.mapper.MenuMapper;
import com.szsm.meeting.modules.system.mapper.RoleMenuMapper;
import com.szsm.meeting.modules.system.mapper.RoleMapper;
import com.szsm.meeting.modules.system.model.entity.Menu;
import com.szsm.meeting.modules.system.model.entity.Role;
import com.szsm.meeting.modules.system.model.entity.RoleMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * <p> 获取访问该url所需要的用户角色权限信息 </p>
 *
 * @author : wuzhike
 * @description : 执行完之后到 `UrlAccessDecisionManager` 中认证权限
 * @date : 2020/7/30 14:36
 */
@Component
public class UrlFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    MenuMapper menuMapper;
    @Autowired
    RoleMenuMapper roleMenuMapper;
    @Autowired
    RoleMapper roleMapper;

    /***
     * 返回该url所需要的用户权限信息
     *
     * @param object: 储存请求url信息
     * @return: null：标识不需要任何权限都可以访问
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        // 获取当前请求url
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        // TODO 忽略菜单url请放在此处进行过滤放行
        if ("/login".equals(requestUrl)
                || requestUrl.contains("/person/person")
                || requestUrl.contains("/push/pushOne")
                || requestUrl.contains("/push/pushAll")
                || requestUrl.contains("/person/getList")
                || requestUrl.contains("/file/info")
                || requestUrl.contains("/meeting")
                || requestUrl.contains("/meetingPerson")
                || requestUrl.contains("/agenda")) {
            return null;
        }

        // 数据库中所有url
        List<Menu> permissionList = menuMapper.selectList(null);
        for (Menu permission : permissionList) {
            // 获取该url所对应的权限
            if (requestUrl.equals(permission.getUrl())) {
//                List<RoleMenu> permissions = roleMenuMapper.selectList(new EntityWrapper<RoleMenu>().eq("menu_id", permission.getId()));
                List<RoleMenu> permissions = roleMenuMapper.selectList(new QueryWrapper<RoleMenu>().lambda().eq(RoleMenu::getMenuId, permission.getId()));
                List<String> roles = new LinkedList<>();
                if (!CollectionUtils.isEmpty(permissions)) {
                    Integer roleId = permissions.get(0).getRoleId();
                    Role role = roleMapper.selectById(roleId);
                    roles.add(role.getCode());
                }

                // 保存该url对应角色权限信息
                return SecurityConfig.createList(roles.toArray(new String[roles.size()]));
            }
        }
        // 如果数据中没有找到相应url资源则为非法访问，要求用户登录再进行操作
        return SecurityConfig.createList(Constants.ROLE_LOGIN);
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
