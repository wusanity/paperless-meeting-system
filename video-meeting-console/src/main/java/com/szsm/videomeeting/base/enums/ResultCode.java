package com.szsm.videomeeting.base.enums;


/**
 *  <p> 响应码枚举 - 可参考HTTP状态码的语义 </p>
 *
 * @description :
 * @author : wuzhike
 * @date : 2020/7/30 11:09
 */
public enum ResultCode implements ServiceExceptionEnum{
    //成功
    SUCCESS( 200, "SUCCESS" ),
    //失败
    FAILURE( 999, "服务器异常" ),
    TOKEN_NO_FOUND( 10001, "没找到token" ),
    REGISTER_ERROR( 10002, "未登录！！！" ),
    ;

    private int code;
    private String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
