package com.github.weichun97.nettytest;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
public class NettyClient implements ApplicationListener<ApplicationReadyEvent> {

    @Value("${netty.server.host}")
    private String host;
    @Value("${netty.server.port}")
    private Integer port;
    @Value("${netty.server.reconnectionInterval}")
    private Integer reconnectionInterval;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
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
                channelFuture = bootstrap.connect(host, port).sync();
                channelFuture.channel().closeFuture().sync();
            } catch (Exception e) {
                log.error(String.format("连接服务器失败，将在%s毫秒后尝试重新连接...", reconnectionInterval));
                try {
                    TimeUnit.MILLISECONDS.sleep(reconnectionInterval);
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


}
