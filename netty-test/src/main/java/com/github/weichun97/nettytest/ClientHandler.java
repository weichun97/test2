package com.github.weichun97.nettytest;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

@Slf4j
@ChannelHandler.Sharable
public class ClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
            // 读取从服务器接收到的字节数据
            byte[] bytes = new byte[msg.readableBytes()];
            msg.readBytes(bytes);
            log.info("接收到服务端的消息: " + new String(bytes, StandardCharsets.UTF_8));
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            // 连接服务器成功时的处理逻辑
            log.info("连接到服务端: " + ctx.channel().remoteAddress());
        }

        @Override
        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
            log.error("服务端断开连接: "  + ctx.channel().remoteAddress());
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            // 异常处理，例如连接断开时的处理
            log.error("断开连接", cause);
            ctx.close();
        }
    }