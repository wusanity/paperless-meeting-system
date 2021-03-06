package com.szsm.meeting.base.constant;

import java.util.HashMap;
import java.util.Map;

/**
 *  <p> 全局常用变量 </p>
 *
 * @description :
 * @author : wuzhike
 * @date : 2020/7/30 14:47
 */
public class Constants {

    /**
     * 接口url
     */
    public static Map<String,String> URL_MAPPING_MAP = new HashMap<>();

    /**
     *  获取项目根目录
     */
    public static String PROJECT_ROOT_DIRECTORY = System.getProperty("user.dir");

    /**
     * 密码加密相关
     */
    public static String SALT = "wuzhike";
    public static final int HASH_ITERATIONS = 1;

    /**
     * 请求头 - token
     */
    public static final String REQUEST_HEADER = "access_token";

    /**
     * 请求头类型：
     * application/x-www-form-urlencoded ： form表单格式
     * application/json ： json格式
     */
    public static final String REQUEST_HEADERS_CONTENT_TYPE = "application/json";

    /**
     * 未登录者角色
     */
    public static final String ROLE_LOGIN = "role_login";

    /**
     * 会议状态：0未开始，1启动，2结束
     */
    public static final Integer OFF = 2;

    /**
     * 会议状态：0未删除，1已删除
     */
    public static final Integer DELETED = 1;

}
