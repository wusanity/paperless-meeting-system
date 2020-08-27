package com.szsm.meeting.base.constant;

/**
 * @Description: 统一返回结果常量类
 * @Author: wuzhike
 * @Date: Created in 2020/07/30 12:08
 */
public class ApiConstant {

    /**
     * @Description: 返回code常量
     * @Author: wuzhike
     * @Date: 2020/07/30 12:09
     */
    public static class Code {
        // 成功返回码
        public static final Integer SUCCESS_CODE = 200;

        // 参数缺失返回码
        public static final Integer PARAMS_LACK_CODE = 105;

        // 新增失败返回码
        public static final Integer INSERT_ERROR_CODE = 106;

        // 系统异常返回码
        public static final Integer SERVER_ERROR_CODE = 999;

        // 用户不存在返回码
        public static final Integer USER_NULL_CODE = 104;

        // 验证码错误返回码
        public static final Integer VALIDCODE_ERROR_CODE = 102;

        // 验证码失效返回码
        public static final Integer VALIDCODE_TIMEOUT_CODE = 103;

        // 密码错误返回码
        public static final Integer PASSWORD_ERROR_CODE = 101;

        // 微信用户token认证失败
        public static final Integer USER_TOKEN_ERROR = 301;

        public static final Integer PARAM_ERROR = 303;

        /**
         * 更新失败返回码
         */
        public static final Integer UPDATE_ERROR_CODE = 500;

        public static final Integer DEPT_INFO_NULL = 107;

        public static final Integer AUTH_DENIED = 108;

        public static final Integer STATION_NULL = 109;

        public static final Integer UPDATE_PAPERNUM_FAIL = 201;

        public static final Integer MACHINE_NOT_SUPPORT = 202;

    }

    /**
     * @Description: 返回信息说明常量
     * @Author: wuzhike
     * @Date: 2020/07/30 12:09
     */
    public static class Msg {

        public static final String UPDATE_PAPERNUM_FAIL = "更新纸巾数量失败";

        public static final String MACHINE_NOT_SUPPORT = "该设备暂不支持加减纸操作";

        public static final String PARAM_ERROR = "参数错误";

        // 成功返回信息
        public static final String SUCCESS_MSG = "SUCCESS";

        // 参数缺失信息
        public static final String PARAMS_LACK_MSG = "参数缺失";

        public static final String STATION_NULL = "该用户无对应站点信息";

        // 新增失败信息
        public static final String INSERT_ERROR_MSG = "新增失败";

        // 系统异常返回信息
        public static final String SERVER_ERROR_MSG = "服务器异常";

        // 用户不存在返回信息
        public static final String USER_NULL_MSG = "手机号未注册";

        // 验证码错误返回信息
        public static final String VALIDCODE_ERROR_MSG = "验证码错误";

        public static final String DEPT_INFO_NULL = "用户无部门信息,暂时无法查看";

        // 验证码失效返回信息
        public static final String VALIDCODE_TIMEOUT_CODE = "验证码失效";

        // 密码错误返回信息
        public static final String PASSWORD_ERROR_MSG = "密码错误";

        // 微信用户token认证失败
        public static final String USER_TOKEN_ERROR_MSG = "缺少微信用户认证信息";

        // 修改失败
        public static final String UPDATE_ERROR_MSG = "修改失败";

        public static final String AUTH_DENIED = "该用户无权限查看设备维护信息";

        public static final String NOT_DELETED = "该数据无法删除";

    }

}
