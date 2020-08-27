package com.szsm.meeting.base.config.netty.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: huangxin
 * @Date: Created in 下午9:02 2019/1/1
 * @Description: 报文 = 起始位+包长度+协议号+信息内容+信息序列号+错误校验+停止位
 */
@Slf4j
public class DataContext {

    /**
     * 心跳包数据
     */
    public static final int HEART_BEAT_PACKAGE = 0;
    /**
     * 新连接
     */
    public static final int JOIN_PACKAGE = 11;
    /**
     * 新增编辑会议
     */
    public static final int ADD_UPDATE_PACKAGE = 21;

    /**
     * 回复查询基础信息
     */
    public static final int REPLY_BASE_MESSAGE = 1;
    /**
     * 回复下载文件
     */
    public static final int REPLY_FILE = 2;

    /**
     * 可变不定长信息内容，消息体
     */
    @Getter
    @Setter
    private BaseDataBody baseDataBody;

}
