package ru.yarka.mcpeclient.protocol.handshaking;

import ru.yarka.mcpeclient.protocol.PreparedPacket;
import ru.yarka.mcpeclient.protocol.RawPacket;

public class HandshakeStateRequestFinal extends RawPacket {

    public static byte PACKET_ID = 0x09;

    public long clientId;
    public long sendPingTime;
    public boolean useSecurity = false;

    @Override
    public byte getPacketId() {
        return PACKET_ID;
    }

    @Override
    public void write() {
        clear();

        putByte(PACKET_ID);
        putLong(clientId);
        putLong(sendPingTime);
        putByte((byte) (useSecurity ? 1 : 0));
    }

    @Override
    public String getName() {
        return null;
    }
}
