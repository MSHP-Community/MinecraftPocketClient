package ru.yarka.mcpeclient;

import ru.yarka.mcpeclient.event.ReceivedPacketEvent;
import ru.yarka.mcpeclient.event.SentPacketEvent;
import ru.yarka.mcpeclient.session.SessionHandler;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class TestStart {

    public static void main(String[] args) throws UnknownHostException {
        Client client = new Client("localhost", 19132, new SessionHandler() {
            @Override
            public void receivingPacket(ReceivedPacketEvent event) {

            }

            @Override
            public void sendingPacket(SentPacketEvent event) {

            }
        });

        //System.out.println(InetAddress.getByName("localhost").getHostAddress());
        client.start();

        client.ping();
        //client.handshakeState1();
    }
}
