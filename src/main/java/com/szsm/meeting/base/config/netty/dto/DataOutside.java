package com.szsm.meeting.base.config.netty.dto;

import lombok.Data;

/**
 * @ClassName: Data
 * @Description: 外部数据
 * @Author: Sam.huangxin
 * @Date: Created in 上午11:58 2020/6/28
 * @Version: 1.0
 **/
@Data
public class DataOutside {
    /**
     * 外部数据头
     */
    private DataHeader header;
    /**
     * 外部数据体
     */
    private BaseDataBody baseDataBody;
}
