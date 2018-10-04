package com.cosmo.handler;

import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cosmo.init.StartupEvent;
import com.cosmo.repository.redis.RedisRepository;
import com.cosmo.util.HexConvert;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;

public class UdpServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UdpServerHandler.class);

    // 計算server收到多少UDP
    private static AtomicInteger count = new AtomicInteger();

    private byte[] buf = new byte[49];

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
        LOGGER.info("count:" + count.incrementAndGet());

        msg.content().getBytes(0, buf);
        String hexString = HexConvert.BinaryToHexString(buf);
        LOGGER.info("Received UDP HEX:" + hexString);

        RedisRepository redisRepository = (RedisRepository) StartupEvent.getBean(RedisRepository.class);
        redisRepository.lpush("udp:msg", hexString);
        redisRepository.setKey("UDPMsgNumber", String.valueOf(count));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        LOGGER.error(cause.getMessage(), cause);
    }

}
