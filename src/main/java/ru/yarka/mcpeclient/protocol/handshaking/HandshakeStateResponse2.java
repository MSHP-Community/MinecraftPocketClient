package ru.yarka.mcpeclient.protocol.handshaking;

import ru.yarka.mcpeclient.protocol.ResponsePacket;

import java.net.InetSocketAddress;

public class HandshakeStateResponse2 extends ResponsePacket {

    public static byte PACKET_ID = 0x08;

    public long serverId;
    public InetSocketAddress clientAddress;
    public short mtu;

    @Override
    public byte getPacketId() {
        return PACKET_ID;
    }

    @Override
    public void read() {
        offset = 0;

        getByte(); //Packet ID
        readMagic();
        serverId = getLong();
        //clientAddress = getInetSocketAddress();
        //mtu = getShort();
    }

    @Override
    public String getName() {
        return "HandshakeStateResponse2";
    }
}
