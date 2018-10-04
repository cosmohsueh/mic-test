package com.cosmo.echo;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class EchoClient {

    private DatagramSocket socket;
    private InetAddress address;

    private byte[] buf;

    public EchoClient() {
        try {
            this.socket = new DatagramSocket();
            this.address = InetAddress.getByName("localhost");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String sendEcho(String msg) {
        String received = "";
        try {
            this.buf = msg.getBytes();
            DatagramPacket packet = new DatagramPacket(this.buf, this.buf.length, this.address, 4445);
            this.socket.send(packet);

            packet = new DatagramPacket(this.buf, this.buf.length);
            this.socket.receive(packet);
            received = new String(packet.getData(), 0, packet.getLength());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return received;
    }

    public void close() {
        this.socket.close();
    }

}
