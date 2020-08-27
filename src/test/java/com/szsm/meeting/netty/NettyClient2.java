package com.szsm.meeting.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import java.util.Scanner;

public class NettyClient2 {

    static final String HOST = System.getProperty("host", "127.0.0.1");
    static final int PORT = Integer.parseInt(System.getProperty("port", "7000"));
    static final int SIZE = Integer.parseInt(System.getProperty("size", "256"));

    public static void main(String[] args) throws Exception {
        sendMessage("11");
//        sendMessage();
    }
    public static void sendMessage(String content) throws InterruptedException{
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
            String req = "{\"body\":{\"userId\":\"22\",\"meetingNo\":\"123456\"}}"+"^^^";
            channel.writeAndFlush(Unpooled.copiedBuffer(req.getBytes()));
            channel.closeFuture().sync();
        } catch (Exception e){
            e.printStackTrace();
        } finally{
            group.shutdownGracefully();
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
