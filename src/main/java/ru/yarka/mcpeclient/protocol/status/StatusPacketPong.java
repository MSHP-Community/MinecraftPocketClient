package ru.yarka.mcpeclient.protocol.status;

import ru.yarka.mcpeclient.protocol.ResponsePacket;

public class StatusPacketPong extends ResponsePacket {

    public static byte PACKET_ID = 0x1c;

    public long pongId;
    public long serverId;
    public String serverInfo;

    public void read() {
        offset = 0;

        getByte(); // packet ID
        pongId = getLong();
        serverId = getLong();
        readMagic();
        serverInfo = getString();
    }

    @Override
    public byte getPacketId() {
        return PACKET_ID;
    }

    @Override
    public String getName() {
        return "StatusPacketPong";
    }
}
