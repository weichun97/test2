package com.github.weichun97.nettytest;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

public class ClientDemo {

    private static final String HOST = "127.0.0.1"; // 连接的服务器主机
    private static final int PORT = 8080; // 连接的服务器端口

    public static void main(String[] args) {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap()
                .group(workerGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new ClientHandler());
                    }
                });

        ChannelFuture channelFuture = null;
        while (true) {
            try {
                channelFuture = bootstrap.connect(HOST, PORT).sync();
                channelFuture.channel().closeFuture().sync();
            } catch (Exception e) {
                System.err.println("连接服务器失败，将在5秒后尝试重新连接...");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            } finally {
                if (channelFuture != null && channelFuture.channel().isOpen()) {
                    channelFuture.channel().close();
                }
            }
        }
    }

    static class ClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
            // 读取从服务器接收到的字节数据
            byte[] bytes = new byte[msg.readableBytes()];
            msg.readBytes(bytes);
            System.out.println("Received from server: " + new String(bytes, CharsetUtil.UTF_8));
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            // 异常处理，例如连接断开时的处理
            cause.printStackTrace();
            ctx.close();
        }
    }
}

