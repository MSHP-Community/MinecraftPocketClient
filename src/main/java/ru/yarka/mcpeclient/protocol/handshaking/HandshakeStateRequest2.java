package ru.yarka.mcpeclient.protocol.handshaking;

import ru.yarka.mcpeclient.MCPEConstants;
import ru.yarka.mcpeclient.protocol.PreparedPacket;

import java.net.InetAddress;
import java.net.InetSocketAddress;

public class HandshakeStateRequest2 extends PreparedPacket {

    public static byte PACKET_ID = 0x07;

    public long clientId;
    public InetSocketAddress serverAddress;
    public short mtu = MCPEConstants.MTU;

    @Override
    public byte getPacketId() {
        return PACKET_ID;
    }

    @Override
    public void write() {
        clear();

        putByte(PACKET_ID);
        putMagic();
        putInetSocketAddress(serverAddress);
        putShort(mtu);
        putLong(clientId);
    }

    @Override
    public String getName() {
        return "HandshakeStateRequest2";
    }
}
