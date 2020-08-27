package com.szsm.meeting.base.config.netty.dto;

import lombok.Data;

/**
 * @Author: huangxin
 * @Date: Created in 下午9:02 2019/1/1
 * @Description: 报文 = 起始位+包长度+协议号+信息内容+信息序列号+错误校验+停止位
 */
@Data
public class ResponseContext {

    /**
     * 回复类型：0回复心跳包，1回复要查询基础数据，2回复要下载文件
     */
    private Integer resType;


}
