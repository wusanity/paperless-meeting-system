package com.szsm.videomeeting.base.config.netty.core;

import io.netty.channel.Channel;
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
    private static ConcurrentHashMap<Integer,Channel> userChannelMap = new ConcurrentHashMap<>();
    /**
     * 用户会议号
     */
    private static ConcurrentHashMap<Integer,String> userMeetingMap = new ConcurrentHashMap<>();

    /**
     * channelId，用户
     */
    private static ConcurrentHashMap<ChannelId,Integer> channelIdserMap = new ConcurrentHashMap<>();

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
    public static ConcurrentHashMap<Integer,Channel> getUserChannelMap(){
        return userChannelMap;
    }

    /**
     * 获取用户channel map
     * @return
     */
    public static ConcurrentHashMap<Integer,String> getUserMeetingMap(){
        return userMeetingMap;
    }

    /**
     * 获取用channelId userId map
     * @return
     */
    public static ConcurrentHashMap<ChannelId,Integer> getChannelIdserMap(){
        return channelIdserMap;
    }

   /* public static void put(String  meetingNo,Integer userId,Channel channel){
        ConcurrentHashMap<String ,Channel> map = new ConcurrentHashMap<>();
        map.put(meetingNo,channel);
        userChannelMap.put(userId,map);
    }*/
}
