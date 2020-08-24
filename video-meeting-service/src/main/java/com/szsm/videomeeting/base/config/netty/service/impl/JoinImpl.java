package com.szsm.videomeeting.base.config.netty.service.impl;

import com.alibaba.fastjson.JSON;
import com.szsm.videomeeting.base.config.netty.core.NettyConfig;
import com.szsm.videomeeting.base.config.netty.dto.BaseDTO;
import com.szsm.videomeeting.base.config.netty.dto.BaseDataBody;
import com.szsm.videomeeting.base.config.netty.dto.DataOutside;
import com.szsm.videomeeting.base.config.netty.dto.ResponseContext;
import com.szsm.videomeeting.base.config.netty.service.BaseFacade;
import com.szsm.videomeeting.base.context.ApiResult;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
            ConcurrentHashMap<Long, String> userMeetingMap = NettyConfig.getUserMeetingMap();
            ResponseContext context = new ResponseContext();


            for (Long userId : userMeetingMap.keySet()) {
                if (userMeetingMap.get(userId).equals(baseDTO.getMeetingNo())) {
//                    pushService.pushMsgToOne(baseDTO.getUserId(),"lalala~~~~~");
                    Channel channel = NettyConfig.getUserChannelMap().get(userId);
                    if (channel.id().equals(ctx.channel().id())){
                        // 如果是当前这个channel
                        context.setFileMessage(true);
                        context.setUrl(new ArrayList<>());
                    }
                    context.setBaseMessage(true);
                    NettyConfig.push(channel,context);
                    try {
                        // 发送文件请求每个间隔延迟1s
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return ApiResult.SUCCESS;
    }


    public void pushBaseMessage(Long userId,String meetingNo){
        if (!meetingNo.equals(NettyConfig.getUserMeetingMap().get(userId))){
            return;
        }
        ResponseContext context = new ResponseContext();
        context.setBaseMessage(true);
        context.setMeetingNo(meetingNo);
        Channel channel = NettyConfig.getUserChannelMap().get(userId);
        ApiResult result = ApiResult.ok(context);


    }

    public void pushFileMessage(Long userId,String meetingNo){
        if (!meetingNo.equals(NettyConfig.getUserMeetingMap().get(userId))){
            return;
        }
        ResponseContext context = new ResponseContext();
        context.setFileMessage(true);
        //todo:根据会议号查询文件
        Channel channel = NettyConfig.getUserChannelMap().get(userId);
    }


}
