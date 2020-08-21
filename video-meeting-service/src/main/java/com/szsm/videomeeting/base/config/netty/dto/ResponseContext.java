package com.szsm.videomeeting.base.config.netty.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @Author: huangxin
 * @Date: Created in 下午9:02 2019/1/1
 * @Description: 报文 = 起始位+包长度+协议号+信息内容+信息序列号+错误校验+停止位
 */
@Data
public class ResponseContext {

    /**
     * 回复心跳包
     */
    Boolean heartBeat;
    /**
     * 查询基础信息
     */
    Boolean baseMessage;

    /**
     * 下载文件信息
     */
    Boolean fileMessage;

    /**
     * 文件路径
     */
    List<String> fileName;

}
