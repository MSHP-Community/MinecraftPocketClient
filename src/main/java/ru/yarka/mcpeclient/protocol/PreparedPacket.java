package ru.yarka.mcpeclient.protocol;

import ru.yarka.mcpeclient.utils.ByteStream;

public abstract class PreparedPacket extends ByteStream implements Packet {

    public static byte PACKET_ID = -1;
    private int shift;

    abstract public void write();

    public byte getPacketId() {
        return PACKET_ID;
    }

    public byte[] getPreparedBuffer() {
        shift = 0;
        byte[] raw = new byte[buffer.size()];

        buffer.forEach((b) -> {
            raw[shift] = b;
            ++shift;
        });

        return raw;
    }
}
