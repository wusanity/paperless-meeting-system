package com.szsm.videomeeting.base.config.netty.router;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.szsm.videomeeting.base.config.netty.dto.BaseDataBody;
import com.szsm.videomeeting.base.config.netty.service.BaseFacade;
import com.szsm.videomeeting.base.config.netty.dto.DataHeader;
import com.szsm.videomeeting.base.config.netty.dto.DataOutside;
import com.szsm.videomeeting.base.context.ApiResult;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: wuzhike
 * @Date: Created in 上午11:51 2018/12/26
 * @Description: 报文路由器
 */
@Slf4j
public final class RouterContext {
    /**
     * 报文头
     */
    private static final String HEADER = "header";
    /**
     * 报文体
     */
    private static final String BODY = "body";

    /**
     * 消息全局上下文路由处理器
     *
     * @param ctx netty 上下文
     * @param msg json 消息
     * @return 应答消息(终端暂无支持)
     */
    public static ApiResult routeAndHandle(ChannelHandlerContext ctx, String msg) {
        DataOutside dataOutside = new DataOutside();
        try {
            JSONObject jsonObject = JSONObject.parseObject(msg);

            Object header = jsonObject.get(HEADER);
            DataHeader dataHeader = JSON.parseObject(JSON.toJSONString(header), DataHeader.class);
            BaseDataBody baseDataBody = BaseDataBody.loadBodyInfo(dataHeader.getOpType());
            if (null == baseDataBody) {
                log.error("数据体获取失败:{}", JSONObject.toJSONString(msg));
                return null;
            }
            BaseDataBody dataBody =null;
            if (!baseDataBody.isFlag()){
                Object body = jsonObject.get(BODY);
                dataBody = JSONObject.parseObject(JSON.toJSONString(body), baseDataBody.getClass());
            }
            dataOutside.setHeader(dataHeader);
            dataOutside.setBaseDataBody(dataBody);
        } catch (Exception e) {
            log.error("数据转换失败：{}", JSON.toJSONString(msg));
            throw new RuntimeException(e.getMessage());
        }
        BaseFacade baseFacade = BaseFacade.loadFacade(dataOutside.getHeader().getOpType());
        if (null == baseFacade) {
            log.error("策略获取失败，原始数据对象：{}", JSON.toJSONString(dataOutside));
            return null;
        }
        return baseFacade.handle(ctx, dataOutside);
    }

    /*public static ApiResult routeAndHandle(ChannelHandlerContext ctx, String msg) {
       *//* JSONObject jsonObject = JSONObject.parseObject(msg);
        Object body = jsonObject.get(BODY);
        BaseDTO baseDTO = JSONObject.parseObject(JSON.toJSONString(body), BaseDTO.class);
        PushService pushService = SpringUtil.getBean(PushService.class);
        pushService.pushMsgToOne(baseDTO.getUserId()+"","aaaa");*//*
        return ApiResult.SUCCESS;
    }*/


}
