package com.cosmo.handler;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cosmo.model.No;
import com.cosmo.model.NoCo2;
import com.cosmo.model.NoDi;
import com.cosmo.model.NoDo;
import com.cosmo.model.NoTH;
import com.cosmo.model.TYPE;
import com.cosmo.util.HexConvert;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;

public class UdpServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UdpServerHandler.class);

    // 計算server收到多少UDP
    private static AtomicInteger count = new AtomicInteger();
    public static LinkedList<No> LIST_NO = new LinkedList<>();

    private byte[] buf = new byte[49];

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
        LOGGER.info("count:" + count.incrementAndGet());

        msg.content().getBytes(0, buf);
        String hexString = HexConvert.BinaryToHexString(buf);
        String[] hexAry = hexString.split(" ");
        if (verifyCrc(hexAry)) {
            // RedisRepository redisRepository = (RedisRepository) StartupEvent.getBean(RedisRepository.class);
            // redisRepository.lpush("udp:msg", hexString);
            // redisRepository.setKey("UDPMsgNumber", String.valueOf(count));

            // 檢測設備號碼
            int typeId = Integer.parseInt(hexAry[9], 16);
            LOGGER.info("device type id:" + typeId);
            No no = null;
            switch (TYPE.fromInt(typeId)) {
            case NO_TH:
                no = new NoTH(hexAry);
                break;
            case NO_CO2:
                no = new NoCo2(hexAry);
                break;
            case NO_DI:
                no = new NoDi(hexAry);
                break;
            case NO_DO:
                no = new NoDo(hexAry);
                break;
            default:
                break;
            }
            if (no != null) {
                LOGGER.info(ToStringBuilder.reflectionToString(no));
                LIST_NO.add(no);
            }

        } else {
            LOGGER.warn("Received ERROR UDP HEX:" + hexString);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        LOGGER.error(cause.getMessage(), cause);
    }

    private boolean verifyCrc(String[] hexAry) {
        int crc = 55;
        for (int i = 1; i < 18; i++) {
            crc = crc ^ Integer.parseInt(hexAry[i], 16);
        }
        return crc == Integer.parseInt(hexAry[hexAry.length - 1], 16);
    }

}
