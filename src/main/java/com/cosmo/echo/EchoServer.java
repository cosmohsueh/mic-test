package com.cosmo.echo;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import com.cosmo.util.HexConvert;

public class EchoServer extends Thread {

    private DatagramSocket socket;
    private boolean running = true;
    private byte[] buf = new byte[49];

    public EchoServer() {
        try {
            this.socket = new DatagramSocket(4445);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (running) {
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            try {
                socket.receive(packet);
                buf = packet.getData();

                // byte轉為16進位
                String hexString = HexConvert.BinaryToHexString(buf);
                System.out.println("hexString:" + hexString);
                hexString = hexString.replace(" ", "");
                // 轉為ASCII：*00007VERSION\n1$
                String asc = HexConvert.convertHexToString(hexString);
                System.out.println("asc:" + asc);

                String received = new String(packet.getData(), 0, packet.getLength());

                if (received.equals("end")) {
                    running = false;
                    continue;
                }
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(buf, buf.length, address, port);
                socket.send(packet);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.socket.close();
    }

    public static void main(String[] args) {
        new EchoServer().start();
    }
}
