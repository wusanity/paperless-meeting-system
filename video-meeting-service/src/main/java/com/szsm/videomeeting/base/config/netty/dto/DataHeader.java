package com.szsm.videomeeting.base.config.netty.dto;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName: DataHeader
 * @Description: 数据头
 * @Author: Sam.huangxin
 * @Date: Created in 上午11:34 2020/6/28
 * @Version: 1.0
 **/
@Data
public class DataHeader {


    /**
     * 心跳包发送0 /安卓连接11 /车辆实时监控数据12 /车载设备更新请求13 /车载功能数据下载:14 /车辆报修:15/气瓶监控数据21 /...
     */
    private Integer opType;

}
