package com.szsm.videomeeting.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.EventLoop;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class ClientHandler extends ChannelInboundHandlerAdapter {

    private NettyClient nettyClient;

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("ClientHandler Active");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("断线了。。。。。。");
        //使用过程中断线重连
        final EventLoop eventLoop = ctx.channel().eventLoop();
        eventLoop.schedule(new Runnable() {
            @Override
            public void run() {
                try {
                    nettyClient.start();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 1, TimeUnit.SECONDS);

        ctx.fireChannelInactive();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

            if (evt instanceof IdleStateEvent) {
                IdleStateEvent event = (IdleStateEvent) evt;
                if (event.state().equals(IdleState.READER_IDLE)) {
                    System.out.println("READER_IDLE");

                } else if (event.state().equals(IdleState.WRITER_IDLE)) {
                    /**发送心跳,保持长连接*/
                    String req = "{\"header\":{\"type\":\"0\"}}"+"^^^";
                    ctx.channel().writeAndFlush(Unpooled.copiedBuffer(req.getBytes()));
                    log.debug("心跳发送成功!");
                    System.out.println("心跳发送成功!");
                } else if (event.state().equals(IdleState.ALL_IDLE)) {
                    System.out.println("ALL_IDLE");
                }
            }
            super.userEventTriggered(ctx, evt);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf buf = (ByteBuf) msg;
        try {
            byte[] req = new byte[buf.readableBytes()];
            buf.readBytes(req);
            String s = new String(req);
            System.out.println(s);
            System.out.println("Client : " + s);
        } finally {
            ReferenceCountUtil.release(buf);
        }
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
