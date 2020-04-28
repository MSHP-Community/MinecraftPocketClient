package ru.yarka.mcpeclient.protocol.handshaking;

import ru.yarka.mcpeclient.protocol.ResponsePacket;

public class HandshakeStateResponse1 extends ResponsePacket {

    public static byte PACKET_ID = 0x06;

    public long serverId;
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
        getByte();
        mtu = getShort();
    }

    @Override
    public String getName() {
        return "HandshakeStateResponse1";
    }
}
