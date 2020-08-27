package com.szsm.meeting.base.config.security.url;

import com.szsm.meeting.base.util.ResponseUtils;
import com.szsm.meeting.base.context.ApiResult;
import com.szsm.meeting.base.util.ResponseUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 *  <p> 认证url权限 - 登录后访问接口无权限 - 自定义403无权限响应内容 </p>
 *
 * @description : 登录过后的权限处理 【注：要和未登录时的权限处理区分开哦~】
 * @author : wuzhike
 * @date : 2020/7/30 18:52
 */
@Component
public class UrlAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        ResponseUtils.out(response, ApiResult.fail(403, e.getMessage()));
    }
}
