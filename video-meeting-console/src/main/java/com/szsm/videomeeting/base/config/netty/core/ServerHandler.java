package com.szsm.videomeeting.base.config.netty.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.szsm.videomeeting.base.config.netty.router.RouterContext;
import com.szsm.videomeeting.base.context.ApiResult;
import com.szsm.videomeeting.base.config.netty.dto.BaseDTO;
import com.szsm.videomeeting.modules.kk.service.PushService;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
@ChannelHandler.Sharable
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Autowired
    private PushService pushService;

    /**
     * 心跳丢失次数
     */
    private int counter = 0;


    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception{
        log.info(ctx.channel().id()+" come into the chattingroom,"+"Online: "+ NettyConfig.getChannelGroup().size());
        NettyConfig.getChannelGroup().add(ctx.channel());//加入ChannelGroup
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext context){
        log.info(context.channel().id()+" left the chattingroom,"+"Online: "+NettyConfig.getChannelGroup().size());
        // 删除通道
        NettyConfig.getChannelGroup().remove(context.channel());
        removeUserId(context);
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("channelActive----->");
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        /*System.out.println("server channelRead......");
        System.out.println(ctx.channel().remoteAddress()+"----->Server :"+ msg.toString());
        //将客户端的信息直接返回写入ctx
        ctx.write("server say :"+msg);
        //刷新缓存区
        ctx.flush();*/
        //打印消息然后群发
        log.info(msg.toString());
        //重置心跳丢失次数
        counter = 0;

       /* // 获取用户ID,关联channel
        JSONObject jsonObject = JSONUtil.parseObj(msg);
        String uid = jsonObject.getStr("uid");
        NettyConfig.getUserChannelMap().put(uid,ctx.channel());

        // 将用户ID作为自定义属性加入到channel中，方便随时channel中获取用户ID
        AttributeKey<String> key = AttributeKey.valueOf("userId");
        ctx.channel().attr(key).setIfAbsent(uid);
        // 回复消息
        ctx.channel().writeAndFlush(new TextWebSocketFrame("服务器连接成功！"))*/

        ByteBuf buf = (ByteBuf) msg;
        try {
            byte[] req = new byte[buf.readableBytes()];
            buf.readBytes(req);
            String reqStr = new String(req);
            JSONObject jsonObject = JSONObject.parseObject(reqStr);
            Object header = jsonObject.get("header");
            if (header.equals(0)){
                // 发的是心跳包
                String rsp = JSON.toJSONString("known");
                ctx.channel().writeAndFlush(Unpooled.copiedBuffer(rsp.getBytes()));
                return;
            }
            Object body = jsonObject.get("body");
            BaseDTO baseDTO = JSONObject.parseObject(JSON.toJSONString(body), BaseDTO.class);
            NettyConfig.getUserMeetingMap().put(baseDTO.getUserId(),baseDTO.getMeetingNo());
            NettyConfig.getUserChannelMap().put(baseDTO.getUserId(),ctx.channel());

            // 将用户ID作为自定义属性加入到channel中，方便随时channel中获取用户ID
//            AttributeKey<Integer> key = AttributeKey.valueOf("userId");
//            ctx.channel().attr(key).setIfAbsent(baseDTO.getUserId());
            //todo:根据会议编号查询哪些channel要收到信息,此处可以考虑多线程进行写数据
            /*Collection<ConcurrentHashMap<String, Channel>> values1 = NettyConfig.getUserChannelMap().values();
            for (ConcurrentHashMap<String, Channel> map : values1) {
                Iterator<Map.Entry<String, Channel>> iterator = map.entrySet().iterator();

            }*/

            ConcurrentHashMap<Integer,String> userMeetingMap = NettyConfig.getUserMeetingMap();
            for (Integer userId : userMeetingMap.keySet()) {
                if (userMeetingMap.get(userId).equals(baseDTO.getMeetingNo())){
//                    pushService.pushMsgToOne(baseDTO.getUserId(),"lalala~~~~~");
                    Channel channel = NettyConfig.getUserChannelMap().get(userId);
                    ApiResult results = RouterContext.routeAndHandle(ctx, reqStr);
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
                                Thread.sleep(100);
                            }
                        }
                    } else {
                        String rsp = JSON.toJSONString("unknown");
                        channel.writeAndFlush(Unpooled.copiedBuffer(rsp.getBytes()));
                    }
                }
            }


        }finally {
            ReferenceCountUtil.release(buf);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(ctx.channel().id()+" occurred into error,"+"Online: "+NettyConfig.getChannelGroup().size());
        // 删除通道
        NettyConfig.getChannelGroup().remove(ctx.channel());
        removeUserId(ctx);
        ctx.close();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state().equals(IdleState.READER_IDLE)){
                // 空闲40s之后触发 (心跳包丢失)
                if (counter >= 3) {
                    // 连续丢失3个心跳包 (断开连接)
                    NettyConfig.getChannelGroup().remove(ctx.channel());
                    removeUserId(ctx);
                    ctx.channel().close().sync();
                    log.error("已与"+ctx.channel().remoteAddress()+"断开连接");
                    System.out.println("已与"+ctx.channel().remoteAddress()+"断开连接");

                } else {
                    counter++;
                    log.debug(ctx.channel().remoteAddress() + "丢失了第 " + counter + " 个心跳包");
                    System.out.println("丢失了第 " + counter + " 个心跳包");
                }
            }

        }

    }

    /**
     * 删除用户与channel的对应关系
     * @param ctx
     */
    private void removeUserId(ChannelHandlerContext ctx){
        /*AttributeKey<String> key = AttributeKey.valueOf("userId");
        String userId = ctx.channel().attr(key).get();
        NettyConfig.getUserChannelMap().remove(userId);*/
        Integer userId = NettyConfig.getChannelIdserMap().get(ctx.channel().id());
        NettyConfig.getChannelIdserMap().remove(ctx.channel().id());
        NettyConfig.getUserChannelMap().remove(userId);
        NettyConfig.getUserMeetingMap().remove(userId);
    }
}
