package com.szsm.videomeeting.base.config.netty.service;

import com.szsm.videomeeting.base.config.netty.dto.DataContext;
import com.szsm.videomeeting.base.config.netty.dto.DataOutside;
import com.szsm.videomeeting.base.config.netty.service.impl.HeartBeatImpl;
import com.szsm.videomeeting.base.config.netty.service.impl.JoinImpl;
import com.szsm.videomeeting.base.config.netty.service.impl.MeetingAddUpdateImpl;
import com.szsm.videomeeting.base.context.ApiResult;
import com.szsm.videomeeting.base.util.SpringUtil;
import io.netty.channel.ChannelHandlerContext;


/**
 * @ClassName: BaseFacade
 * @Description: TODO
 * @Author: Sam.huangxin
 * @Date: Created in 下午4:00 2020/7/20
 * @Version: 1.0
 **/
@FunctionalInterface
public interface  BaseFacade {

    /**
     * 处理器
     *
     * @param ctx         netty上下文
     * @param dataOutside 外部数据
     * @return 通用结果
     */
    ApiResult handle(ChannelHandlerContext ctx, DataOutside dataOutside);

    /**
     * 根据协议号拿到协议处理类
     *
     * @param operateType 数据操作类型
     * @return 处理策略
     */
    static BaseFacade loadFacade(Integer operateType) {
        switch (operateType) {
            case DataContext.HEART_BEAT_PACKAGE:
                return SpringUtil.getBean(HeartBeatImpl.class);
            case DataContext.JOIN_PACKAGE:
                return SpringUtil.getBean(JoinImpl.class);
            case DataContext.ADD_UPDATE_PACKAGE:
                return SpringUtil.getBean(MeetingAddUpdateImpl.class);
            default:
                return null;
        }
    }

}
