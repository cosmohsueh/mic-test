package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cosmo.echo.EchoClient;
import com.cosmo.echo.EchoServer;

public class UDPTest {

    private EchoClient client;

    @Before
    public void setup() {
        new EchoServer().start();
        this.client = new EchoClient();
    }

    @Test
    public void whenCanSendAndReceivePacket_thenCorrect() {
        String echo = this.client.sendEcho("hello server");
        assertEquals("hello server", echo);
        echo = client.sendEcho("server is working");
        assertFalse(echo.equals("hello server"));
    }

    @After
    public void tearDown() {
        this.client.sendEcho("end");
        this.client.close();
    }
}
