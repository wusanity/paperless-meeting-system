package com.szsm.meeting.base.config.netty.core;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Resource(name = "serverHandler")
    private ServerHandler serverHandler;

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        //缓冲区128kb ^^^三连分隔符 配置解码器
        pipeline.addLast(new DelimiterBasedFrameDecoder(128 * 1024, Unpooled.copiedBuffer(new byte[]{'^', '^', '^'})));
        // 该代码实现了心跳检测，每隔40s检测一次是否要读事件，如果超过40s你没有读事件的发生，则执行相应的操作
        pipeline.addLast(new IdleStateHandler(40,0,0, TimeUnit.SECONDS));
        channel.pipeline().addLast(serverHandler);
    }
}
