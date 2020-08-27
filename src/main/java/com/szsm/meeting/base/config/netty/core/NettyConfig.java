package com.szsm.meeting.base.config.netty.core;

import com.alibaba.fastjson.JSON;
import com.szsm.meeting.base.config.netty.dto.ResponseContext;
import com.szsm.meeting.base.config.netty.dto.ResponseContext;
import com.szsm.meeting.base.context.ApiResult;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.concurrent.ConcurrentHashMap;

public class NettyConfig {

    /**
     * 定义一个channel组，管理所有的channel
     * GlobalEventExecutor.INSTANCE 是全局的事件执行器，是一个单例
     */
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 存放会议号与用户，Channel的对应信息，用于给指定用户发送消息
     *
     * channel和 会议号
     */
    private static ConcurrentHashMap<Long,Channel> userChannelMap = new ConcurrentHashMap<>();
    /**
     * 用户会议号
     */
    private static ConcurrentHashMap<Long,String> userMeetingMap = new ConcurrentHashMap<>();

    /**
     * channelId，用户
     */
    private static ConcurrentHashMap<ChannelId,Long> channelIdUserMap = new ConcurrentHashMap<>();

    private NettyConfig() {}

    /**
     * 获取channel组
     * @return
     */
    public static ChannelGroup getChannelGroup() {
        return channelGroup;
    }

    /**
     * 获取用户channel map
     * @return
     */
    public static ConcurrentHashMap<Long,Channel> getUserChannelMap(){
        return userChannelMap;
    }

    /**
     * 获取用户channel map
     * @return
     */
    public static ConcurrentHashMap<Long,String> getUserMeetingMap(){
        return userMeetingMap;
    }

    /**
     * 获取用channelId userId map
     * @return
     */
    public static ConcurrentHashMap<ChannelId,Long> getChannelIdserMap(){
        return channelIdUserMap;
    }

    /**
     * 发送信息
     * @param channel
     * @param context
     */
    public static void push(Channel channel,ResponseContext context){
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
