package com.example.demo;

import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cosmo.util.HexConvert;

public class SendUDP implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(SendUDP.class);

    private static ExecutorService EXECUTOR = Executors.newFixedThreadPool(5);

    private DatagramSocket socket;
    private InetAddress address;

    private byte[] buf;

    public static void main(String[] args) {
        ClassLoader classLoader = SendUDP.class.getClassLoader();
        File file = new File(classLoader.getResource("udpTest.txt").getFile());
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                EXECUTOR.submit(new SendUDP(line));
            }
            scanner.close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public SendUDP(String hexStr) {
        try {
            this.buf = HexConvert.convertHexToString(hexStr.replace(" ", "")).getBytes("ISO8859-1");
            this.socket = new DatagramSocket();
            this.address = InetAddress.getByName("localhost");
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public void run() {
        try {
            DatagramPacket packet = new DatagramPacket(this.buf, this.buf.length, this.address, 5033);
            this.socket.send(packet);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            this.socket.close();
            LOGGER.info("send:" + new String(this.buf));
        }
    }

}
