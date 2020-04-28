package ru.yarka.mcpeclient.protocol.handshaking;

import ru.yarka.mcpeclient.MCPEConstants;
import ru.yarka.mcpeclient.protocol.PreparedPacket;

public class HandshakeStateRequest1 extends PreparedPacket {

    public static byte PACKET_ID = 0x05;

    public final byte protocol = MCPEConstants.RAKNET_PROTOCOL;

    @Override
    public byte getPacketId() {
        return PACKET_ID;
    }

    @Override
    public void write() {
        clear();

        putByte(PACKET_ID);
        putMagic();
        putByte(protocol);

        for(int i = 0; i < MCPEConstants.MTU; ++i) {
            putByte((byte) 0x00);
        }
    }

    @Override
    public String getName() {
        return "HandshakeStateRequest1";
    }
}
