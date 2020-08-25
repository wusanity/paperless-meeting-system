package com.szsm.videomeeting.modules.kk.service.impl;

import com.szsm.videomeeting.base.context.ApiResult;
import com.szsm.videomeeting.modules.kk.service.PushService;
import org.springframework.stereotype.Service;

@Service
public class PushServiceImpl implements PushService {
    @Override
    public ApiResult pushMsgToOne(Integer userId, String meetingNo) {
        /*ConcurrentHashMap<String, Channel> userChannelMap = NettyConfig.getUserChannelMap();
        Channel channel = userChannelMap.get(userId);
        channel.writeAndFlush(new TextWebSocketFrame(msg));*/
        return ApiResult.SUCCESS;
    }

    @Override
    public ApiResult pushMsgToAll(String meetingNo) {
//        NettyConfig.getChannelGroup().writeAndFlush(new TextWebSocketFrame(msg));
       /* ConcurrentHashMap<Channel, String> userChannelMap = NettyConfig.getUserChannelMap();
        for (Channel channel : userChannelMap.keySet()) {
            ApiResult results = ApiResult.SUCCESS;
            //非空包则回复数据
            if (null != results) {
                String rsp = JSON.toJSONString(results);
                boolean success = false;
                int time = 3;
                while (success == false){
                    // 重试三次
                    ChannelFuture channelFuture = channel.writeAndFlush(Unpooled.copiedBuffer(rsp.getBytes()));
                    if (channelFuture.isSuccess() || time <= 0){
                        success = true;
                    }else {

                        time--;
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } else {
                String rsp = JSON.toJSONString("unknown");
                channel.writeAndFlush(Unpooled.copiedBuffer(rsp.getBytes()));
            }
        }*/
        return ApiResult.SUCCESS;
    }
}
