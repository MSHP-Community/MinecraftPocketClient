package ru.yarka.mcpeclient.server;

import ru.yarka.mcpeclient.MCPEConstants;
import ru.yarka.mcpeclient.protocol.PreparedPacket;
import ru.yarka.mcpeclient.protocol.ResponsePacket;

import java.io.IOException;
import java.net.*;

public class ServerSocket {

    protected InetSocketAddress dest;
    private DatagramSocket socket;

    public ServerSocket(InetSocketAddress dest) {
        try {
            socket = new DatagramSocket();
          //  socket.setSoTimeout(5000);
            this.dest = dest;
        } catch (SocketException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void send(PreparedPacket packet) {
        packet.write();

        byte[] bytes = packet.getPreparedBuffer();
        DatagramPacket pk = new DatagramPacket(bytes, bytes.length);

        pk.setAddress(dest.getAddress());
        pk.setPort(dest.getPort());

        try {
            socket.send(pk);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected byte[] read() throws SocketTimeoutException {
        byte[] bytes = new byte[MCPEConstants.MTU];
        DatagramPacket pk = new DatagramPacket(bytes, bytes.length);
        pk.setAddress(dest.getAddress());
        pk.setPort(dest.getPort());

        try {
            socket.receive(pk);
            if(!pk.getAddress().getHostName().equals(dest.getHostString()) || pk.getPort() != dest.getPort()) {
                return null;
            }

            bytes = pk.getData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bytes;
    }
}
