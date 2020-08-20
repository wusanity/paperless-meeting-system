package com.szsm.videomeeting.base.exception;

import com.szsm.videomeeting.base.enums.ResultCode;
import com.szsm.videomeeting.base.enums.ServiceExceptionEnum;

/**
 *  <p> 自定义异常类 </p>
 *
 * @description :
 * @author : wuzhike
 * @date : 2020/7/30 15:11
 */
public class MyException extends RuntimeException {

    /**
     * 异常状态码
     */
    private Integer code;

    public MyException(Throwable cause) {
        super(cause);
    }

    public MyException(String message) {
        super(message);
    }

    public MyException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public MyException(ServiceExceptionEnum serviceExceptionEnum) {
        super(serviceExceptionEnum.getMessage());
        this.code = serviceExceptionEnum.getCode();
    }

    public MyException(ResultCode resultCode){
        super(resultCode.getMessage());
        this.code = resultCode.getCode();
    }

    public MyException(String message, Throwable cause) {
        super(message, cause);
    }

    public Integer getCode() {
        return code;
    }

}
