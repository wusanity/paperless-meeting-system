package com.szsm.videomeeting.modules.kk.enums;


import com.szsm.videomeeting.base.enums.ServiceExceptionEnum;

/**
 *  <p> 响应码枚举 - 可参考HTTP状态码的语义 </p>
 *
 * @description :
 * @author : wuzhike
 * @date : 2020/7/30 11:09
 */
public enum PersonErrorEnums implements ServiceExceptionEnum{

    /**
     * 参数为空
     */

    PARAM_MISS( 100001, "参数为空" );

    private int code;
    private String message;

    PersonErrorEnums(int code, String message) {
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
