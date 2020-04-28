package ru.yarka.mcpeclient.protocol.handshaking;

import ru.yarka.mcpeclient.protocol.ResponsePacket;

public class HandshakeStateResponseFinal extends ResponsePacket {

    public static byte PACKET_ID = 0x10;

    @Override
    public byte getPacketId() {
        return PACKET_ID;
    }

    @Override
    public void read() {

    }

    @Override
    public String getName() {
        return null;
    }
}

