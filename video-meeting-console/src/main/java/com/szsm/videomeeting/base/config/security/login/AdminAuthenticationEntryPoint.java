package com.szsm.videomeeting.base.config.security.login;

import com.szsm.videomeeting.base.context.ApiResult;
import com.szsm.videomeeting.base.enums.ResultCode;
import com.szsm.videomeeting.base.exception.MyException;
import com.szsm.videomeeting.base.util.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  <p> 认证权限入口 - 未登录的情况下访问所有接口都会拦截到此 </p>
 *
 * @description : 前后端分离情况下返回json格式数据
 * @author : wuzhike
 * @date : 2020/7/30 17:32
 */
@Slf4j
@Component
public class AdminAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) {
        log.error(e.getMessage());
        ResponseUtils.out(response, ApiResult.fail(ResultCode.REGISTER_ERROR));

    }

}
