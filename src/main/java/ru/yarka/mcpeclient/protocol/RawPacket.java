package ru.yarka.mcpeclient.protocol;

import ru.yarka.mcpeclient.utils.ByteStream;

public abstract class RawPacket extends ByteStream implements Packet {

    public static byte PACKET_ID = -1;

    abstract public void write();

    public byte getPacketId() {
        return PACKET_ID;
    }
}
