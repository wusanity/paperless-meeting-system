package com.szsm.videomeeting.base.config.netty.service.impl;

import com.alibaba.fastjson.JSON;
import com.szsm.videomeeting.base.config.netty.core.NettyConfig;
import com.szsm.videomeeting.base.config.netty.dto.BaseDTO;
import com.szsm.videomeeting.base.config.netty.dto.BaseDataBody;
import com.szsm.videomeeting.base.config.netty.dto.DataOutside;
import com.szsm.videomeeting.base.config.netty.dto.ResponseContext;
import com.szsm.videomeeting.base.config.netty.router.RouterContext;
import com.szsm.videomeeting.base.config.netty.service.BaseFacade;
import com.szsm.videomeeting.base.context.ApiResult;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service("joinImpl")
public class JoinImpl implements BaseFacade {
    @Override
    public ApiResult handle(ChannelHandlerContext ctx, DataOutside dataOutside) {
        BaseDataBody baseDataBody = dataOutside.getBaseDataBody();
        if (baseDataBody instanceof BaseDTO) {
            BaseDTO baseDTO = (BaseDTO) baseDataBody;
            NettyConfig.getUserMeetingMap().put(baseDTO.getUserId(), baseDTO.getMeetingNo());
            NettyConfig.getUserChannelMap().put(baseDTO.getUserId(), ctx.channel());
            ConcurrentHashMap<Integer, String> userMeetingMap = NettyConfig.getUserMeetingMap();
            ResponseContext context = new ResponseContext();


            for (Integer userId : userMeetingMap.keySet()) {
                if (userMeetingMap.get(userId).equals(baseDTO.getMeetingNo())) {
//                    pushService.pushMsgToOne(baseDTO.getUserId(),"lalala~~~~~");
                    Channel channel = NettyConfig.getUserChannelMap().get(userId);
                    if (channel.id().equals(ctx.channel().id())){
                        // 如果是当前这个channel
                        context.setFileMessage(true);
                        context.setFileName(new ArrayList<>());
                    }
                    context.setBaseMessage(true);
                    ApiResult result = ApiResult.ok(context);
                    String rsp = JSON.toJSONString(result);
                    boolean success = false;
                    int time = 3;
                    while (!success) {
                        // 重试三次
                        ChannelFuture channelFuture = channel.writeAndFlush(Unpooled.copiedBuffer(rsp.getBytes()));
                        if (channelFuture.isSuccess() || time <= 0) {
                            success = true;
                        } else {
                            time--;
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
        return ApiResult.SUCCESS;
    }
}
