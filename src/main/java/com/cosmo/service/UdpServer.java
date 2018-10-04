package com.cosmo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.cosmo.handler.UdpServerHandler;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

@Component
public class UdpServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(UdpServer.class);

    @Async("myTaskAsyncPool")
    public void run(int udpReceivePort) {
        EventLoopGroup group = new NioEventLoopGroup();
        LOGGER.info("Server start!  Udp Receive msg Port:" + udpReceivePort);

        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                .channel(NioDatagramChannel.class)
                .option(ChannelOption.SO_BROADCAST, true)
                .handler(new UdpServerHandler());

            b.bind(udpReceivePort).sync().channel().closeFuture().await();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            group.shutdownGracefully();
        }
    }
}
