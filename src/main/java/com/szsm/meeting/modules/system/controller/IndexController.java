package com.szsm.meeting.modules.system.controller;

import com.szsm.meeting.base.context.ApiResult;
import com.szsm.meeting.base.context.ApiResult;
import com.szsm.meeting.base.util.ResponseUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  <p> Spring Security - 权限测试api </p>
 *
 * @author：  wuzhike <br/>
 * @date：  2020/7/30 9:37 <br/>
 * @version：  <br/>
 */
@Slf4j
@RestController
public class IndexController {

    @GetMapping(value = "/login", produces = "application/json;charset=utf-8")
    @ApiOperation(value = "登录系统", httpMethod = "GET", response = ApiResult.class)
    public ApiResult login() {
        return ApiResult.SUCCESS;
    }

    @GetMapping(value ="/admin")
    // 访问路径`/admin` 具有`ADMIN`角色权限   【这种是写死方式】
//    @PreAuthorize("hasPermission('/admin','ADMIN')")
    public String admin() {
        return "Hello~ 管理员";
    }

    @GetMapping("/test")
    public String test() {
        return "Hello~ 测试权限访问接口";
    }


}

