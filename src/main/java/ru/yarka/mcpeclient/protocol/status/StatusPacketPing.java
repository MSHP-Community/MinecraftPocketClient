package ru.yarka.mcpeclient.protocol.status;

import ru.yarka.mcpeclient.protocol.PreparedPacket;

public class StatusPacketPing extends PreparedPacket {

    public static byte PACKET_ID = 0x01;

    public long pingId;

    public void write() {
        clear();

        putByte(PACKET_ID);
        putLong(pingId);
        putMagic();
    }

    @Override
    public byte getPacketId() {
        return PACKET_ID;
    }

    @Override
    public String getName() {
        return "StatusPacketPing";
    }
}
