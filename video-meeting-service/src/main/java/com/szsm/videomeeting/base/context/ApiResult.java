package com.szsm.videomeeting.base.context;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.szsm.videomeeting.base.constant.ApiConstant;
import com.szsm.videomeeting.base.enums.ResultCode;
import com.szsm.videomeeting.base.enums.ServiceExceptionEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  <p> API返回参数 </p>
 *
 * @description :
 * @author : wuzhike
 * @date : 2019/7/30 11:09
 */
@ApiModel(value = "API返回参数")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResult {
    /**
     * 消息内容
     */
    @ApiModelProperty(value = "响应消息", required = false)
    private String message;

    /**
     * 成功或有效为1，失败或无效为0，token过期
     */
    @ApiModelProperty(value = "响应码", required = true)
    private Integer code;

    /**
     * 响应中的数据
     */
    @ApiModelProperty(value = "响应数据", required = false)
    private Object data;

    /**
     * 响应中的数据
     */
    @ApiModelProperty(value = "返回条数", required = false)
    private Object count;

    //    // 快速成功返回（不带返回数据）
    public static final ApiResult SUCCESS = ApiResult.builder().code(ApiConstant.Code.SUCCESS_CODE).message(ApiConstant.Msg.SUCCESS_MSG).build();
//    // 服务器异常返回/**/
    public static final ApiResult SERVER_ERROR = ApiResult.builder().code(ApiConstant.Code.SERVER_ERROR_CODE).message(ApiConstant.Msg.SERVER_ERROR_MSG).build();

    public static ApiResult fail(String message) {
        return new ApiResult(ResultCode.FAILURE.getCode(), message, null);
    }

    /***
     * 自定义错误返回码
     *
     * @param code
     * @param message:
     * @return: com.wuzhike.modules.common.dto.output.ApiResult
     */
    public static ApiResult fail(Integer code, String message) {
        return new ApiResult(code, message, null);
    }
    public static ApiResult fail(Integer code, String message,String... msg) {
        for (String s : msg) {
            message = message.replaceFirst("\\{\\}", s);
        }
        return new ApiResult(code, message, null);
    }

    public static ApiResult ok(String message) {
        return new ApiResult(ResultCode.SUCCESS.getCode(), message, null);
    }

    public static ApiResult ok() {
        return new ApiResult(ResultCode.SUCCESS.getCode(), "SUCCESS", null);
    }

    public static ApiResult ok(Object data) {
        return new ApiResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    public static ApiResult ok(String message, Object data) {
        return new ApiResult(ResultCode.SUCCESS.getCode(), message, data);
    }

    public static ApiResult successTable(Page page) {
        return ApiResult.builder().code(ResultCode.SUCCESS.getCode()).message(ResultCode.SUCCESS.getMessage()).data(page.getRecords()).count(String.valueOf(page.getTotal())).build();
    }

    /**
     * 自定义返回码
     */
    public static ApiResult ok(Integer code, String message) {
        return new ApiResult(code, message);
    }

    /**
     * 自定义
     *
     * @param code：验证码
     * @param message：返回消息内容
     * @param data：返回数据
     * @return: com.wuzhike.modules.common.dto.output.ApiResult
     */
    public static ApiResult ok(Integer code, String message, Object data) {
        return new ApiResult(code, message, data);
    }


    public static ApiResult fail(ServiceExceptionEnum serviceExceptionEnum) {
        return ApiResult.builder()
                .code(serviceExceptionEnum.getCode())
                .message(serviceExceptionEnum.getMessage())
                .build();
    }

    public ApiResult(Integer code, String msg, Object data) {
        this.code = code;
        this.message = msg;
        this.data = data;
    }

    public ApiResult(Object data) {
        this.code = ResultCode.SUCCESS.getCode();
        this.message = "OK";
        this.data = data;
    }

    public ApiResult(String message) {
        this(ResultCode.SUCCESS.getCode(), message, null);
    }

    public ApiResult(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public ApiResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
