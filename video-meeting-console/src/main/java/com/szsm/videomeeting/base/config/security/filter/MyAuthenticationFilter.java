package com.szsm.videomeeting.base.config.security.filter;


import com.szsm.videomeeting.base.constant.Constants;
import com.szsm.videomeeting.base.config.security.dto.SecurityUser;
import com.szsm.videomeeting.base.config.security.login.AdminAuthenticationEntryPoint;
import com.szsm.videomeeting.base.config.security.service.impl.UserDetailsServiceImpl;
import com.szsm.videomeeting.base.enums.ResultCode;
import com.szsm.videomeeting.base.exception.MyException;
import com.szsm.videomeeting.base.util.MultiReadHttpServletRequest;
import com.szsm.videomeeting.base.util.MultiReadHttpServletResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 *  <p> 访问鉴权 - 每次访问接口都会经过此 </p>
 *
 * @description :
 * @author : wuzhike
 * @date : 2020/7/30 16:17
 */
@Slf4j
@Component
public class MyAuthenticationFilter extends OncePerRequestFilter {

    private final UserDetailsServiceImpl userDetailsService;

    @Autowired
    AdminAuthenticationEntryPoint authenticationEntryPoint;

    protected MyAuthenticationFilter(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("请求头类型： " + request.getContentType());
        if ((request.getContentType() == null && request.getContentLength() > 0) || (request.getContentType() != null && !request.getContentType().contains(Constants.REQUEST_HEADERS_CONTENT_TYPE))) {
            filterChain.doFilter(request, response);
            return;
        }

        MultiReadHttpServletRequest wrappedRequest = new MultiReadHttpServletRequest(request);
        MultiReadHttpServletResponse wrappedResponse = new MultiReadHttpServletResponse(response);
        StopWatch stopWatch = new StopWatch();
        try {
            stopWatch.start();
            // 记录请求的消息体
            String url = logRequestBody(wrappedRequest);
            //todo:配置登录不需要验证的路径
            if (url.contains("/person/getList")) {
                filterChain.doFilter(wrappedRequest, wrappedResponse);
                return ;
            }

//            SecurityContext context = SecurityContextHolder.getContext();
//            if (context.getAuthentication() != null && context.getAuthentication().isAuthenticated()) {
//                filterChain.doFilter(wrappedRequest, wrappedResponse);
//                return;
//            }


//            String token = "123";
            // 前后端分离情况下，前端登录后将token储存在cookie中，每次访问接口时通过token去拿用户权限
            String token = wrappedRequest.getHeader(Constants.REQUEST_HEADER);
            log.debug("后台检查令牌:{}", token);
            if (StringUtils.isNotBlank(token)) {
                // 检查token
                // 获取jwt中的信息
                Claims claims = Jwts.parser().setSigningKey(Constants.SALT).parseClaimsJws(token.replace("Bearer", "")).getBody();
                // 获取当前登录用户名
                System.out.println("获取当前登录用户名: " + claims.getSubject());
                SecurityUser securityUser = userDetailsService.getUserByToken(token);
                if (securityUser == null || securityUser.getCurrentUserInfo() == null) {
                    throw new AccessDeniedException("TOKEN已过期，请重新登录！");
                }
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(securityUser, null, securityUser.getAuthorities());
                // 全局注入角色权限信息和登录用户基本信息
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }else{
                throw new MyException(ResultCode.TOKEN_NO_FOUND);
            }
            filterChain.doFilter(wrappedRequest, wrappedResponse);
        } catch (ExpiredJwtException e) {
            // jwt令牌过期
            SecurityContextHolder.clearContext();
            this.authenticationEntryPoint.commence(wrappedRequest, response, null);
        } catch (AuthenticationException e) {
            SecurityContextHolder.clearContext();
            this.authenticationEntryPoint.commence(wrappedRequest, response, e);
        } catch (MyException e){
            SecurityContextHolder.clearContext();
            this.authenticationEntryPoint.commence(wrappedRequest, response, null);
        } finally {
            stopWatch.stop();
            long usedTimes = stopWatch.getTotalTimeMillis();
            // 记录响应的消息体
            logResponseBody(wrappedRequest, wrappedResponse, usedTimes);
        }

    }

    private String logRequestBody(MultiReadHttpServletRequest request) {
        MultiReadHttpServletRequest wrapper = request;
        if (wrapper != null) {
            try {
                String bodyJson = wrapper.getBodyJsonStrByJson(request);
                String url = wrapper.getRequestURI().replace("//", "/");
                System.out.println("-------------------------------- 请求url: " + url + " --------------------------------");
                Constants.URL_MAPPING_MAP.put(url, url);
                log.info("`{}` 接收到的参数: {}",url , bodyJson);
                return url;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private void logResponseBody(MultiReadHttpServletRequest request, MultiReadHttpServletResponse response, long useTime) {
        MultiReadHttpServletResponse wrapper = response;
        if (wrapper != null) {
            byte[] buf = wrapper.getBody();
            if (buf.length > 0) {
                String payload;
                try {
                    payload = new String(buf, 0, buf.length, wrapper.getCharacterEncoding());
                } catch (UnsupportedEncodingException ex) {
                    payload = "[unknown]";
                }
                log.info("`{}`  耗时:{}ms  返回的参数: {}", Constants.URL_MAPPING_MAP.get(request.getRequestURI()), useTime, payload);
            }
        }
    }

}
