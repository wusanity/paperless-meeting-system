package com.szsm.meeting.base.config.security.login;

import com.szsm.meeting.base.config.security.dto.SecurityUser;
import com.szsm.meeting.base.context.ApiResult;
import com.szsm.meeting.base.util.ResponseUtils;
import com.szsm.meeting.base.config.security.dto.SecurityUser;
import com.szsm.meeting.base.context.ApiResult;
import com.szsm.meeting.base.util.ResponseUtils;
import com.szsm.meeting.modules.system.model.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  <p> 认证成功处理 </p>
 *
 * @description :
 * @author : wuzhike
 * @date : 2020/7/30 15:31
 */
@Component
public class AdminAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse response, Authentication auth) throws IOException, ServletException {
        User user = new User();
        SecurityUser securityUser = ((SecurityUser) auth.getPrincipal());
        user.setToken(securityUser.getCurrentUserInfo().getToken());
        ResponseUtils.out(response, ApiResult.ok(user));
    }
}
