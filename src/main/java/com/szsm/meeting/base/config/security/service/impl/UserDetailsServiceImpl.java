package com.szsm.meeting.base.config.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.szsm.meeting.base.config.security.dto.SecurityUser;
import com.szsm.meeting.base.config.security.dto.SecurityUser;
import com.szsm.meeting.modules.system.mapper.RoleMapper;
import com.szsm.meeting.modules.system.mapper.UserMapper;
import com.szsm.meeting.modules.system.mapper.UserRoleMapper;
import com.szsm.meeting.modules.system.model.entity.Role;
import com.szsm.meeting.modules.system.model.entity.User;
import com.szsm.meeting.modules.system.model.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * <p> 自定义userDetailsService - 认证用户详情 </p>
 *
 * @author : wuzhike
 * @description :
 * @date : 2020/7/30 17:46
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    /***
     * 根据账号获取用户信息
     * @param username:
     * @return: org.springframework.security.core.userdetails.UserDetails
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 从数据库中取出用户信息
//        List<User> userList = userMapper.selectList(new EntityWrapper<User>().eq("username", username));
        List<User> userList = userMapper.selectList(new QueryWrapper<User>().lambda().eq(User::getUsername, username));
        User user;
        // 判断用户是否存在
        if (!CollectionUtils.isEmpty(userList)) {
            user = userList.get(0);
        } else {
            throw new UsernameNotFoundException("用户名不存在！");
        }
        // 返回UserDetails实现类
        return new SecurityUser(user, getUserRoles(user.getId()));
    }

    /***
     * 根据token获取用户权限与基本信息
     *
     * @param token:
     * @return: com.wuzhike.config.security.dto.SecurityUser
     */
    public SecurityUser getUserByToken(String token) {
        User user = null;
//        List<User> loginList = userMapper.selectList(new EntityWrapper<User>().eq("token", token));
        List<User> loginList = userMapper.selectList(new QueryWrapper<User>().lambda().eq(User::getToken, token));
        if (!CollectionUtils.isEmpty(loginList)) {
            user = loginList.get(0);
        }
        return user != null ? new SecurityUser(user, getUserRoles(user.getId())) : null;
    }

    /**
     * 根据用户id获取角色权限信息
     *
     * @param userId
     * @return
     */
    private List<Role> getUserRoles(Long userId) {
//        List<UserRole> userRoles = userRoleMapper.selectList(new EntityWrapper<UserRole>().eq("user_id", userId));
        List<UserRole> userRoles = userRoleMapper.selectList(new QueryWrapper<UserRole>().lambda().eq(UserRole::getUserId,userId));
        List<Role> roleList = new LinkedList<>();
        for (UserRole userRole : userRoles) {
            Role role = roleMapper.selectById(userRole.getRoleId());
            roleList.add(role);
        }
        return roleList;
    }

}
