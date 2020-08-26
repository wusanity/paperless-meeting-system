package com.szsm.videomeeting.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        ChannelPipeline p = channel.pipeline();
        /*p.addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
        p.addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));*/
        //心跳检测
        p.addLast(new IdleStateHandler(0,30,0, TimeUnit.SECONDS));
        p.addLast(new ClientHandler());
    }
}
