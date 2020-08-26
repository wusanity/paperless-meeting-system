package com.szsm.videomeeting.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

@Slf4j
public class NettyClient {

    static final String HOST = System.getProperty("host", "127.0.0.1");
    static final int PORT = Integer.parseInt(System.getProperty("port", "7000"));
    static final int SIZE = Integer.parseInt(System.getProperty("size", "256"));
    private static Channel channel;

    public static void main(String[] args) throws Exception {
//        sendMessage("11");
//        sendMessage();
        start();
    }
    public static void start() throws InterruptedException{
        // Configure the client.
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            p.addLast(new ClientHandler());
                        }
                    });

            Channel channel = b.connect(HOST, PORT).sync().channel();
            ChannelFuture future = b.connect(HOST, PORT);
            future.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if (!channelFuture.isSuccess()) {
                        final EventLoop loop = channelFuture.channel().eventLoop();
                        loop.schedule(new Runnable() {
                            @Override
                            public void run() {
                                log.error("服务端链接不上，开始重连操作...");
                                System.err.println("服务端链接不上，开始重连操作...");
                                try {
                                    start();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, 1L, TimeUnit.SECONDS);
                    } else {
//                        channel = channelFuture.channel();
                        log.info("服务端链接成功...");
                        System.err.println("服务端链接成功...");
                    }

                }
            });

            String req = "{\"header\":{\"type\":\"1\"},\"body\":{\"userId\":\"11\",\"meetingNo\":\"123456\"}}"+"^^^";
            channel.writeAndFlush(Unpooled.copiedBuffer(req.getBytes()));
            channel.closeFuture().sync();
        } catch (Exception e){
            e.printStackTrace();
        } finally{
//            group.shutdownGracefully();
        }
    }

    public static void sendMessage() throws InterruptedException{
        // Configure the client.
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            p.addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
                            p.addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));
                            p.addLast(new ClientHandler());
                        }
                    });
            ChannelFuture future = b.connect(HOST, PORT).sync();
            Scanner sca=new Scanner(System.in);
            System.out.println("请输入你的昵称：");
            String name=sca.nextLine();
            while (true){
                String str=sca.nextLine();//输入的内容
                if (str.equals("exit"))
                    break;//如果是exit则退出
                future.channel().writeAndFlush(name+" : "+str);//将名字和信息内容一起发过去
            }
            future.channel().closeFuture().sync();

        } finally {
            group.shutdownGracefully();
        }
    }
}
