package ru.yarka.mcpeclient.protocol.session;

import ru.yarka.mcpeclient.protocol.Packet;
import ru.yarka.mcpeclient.utils.ByteStream;

public class SessionPacketDatagram extends ByteStream implements Packet {
    @Override
    public String getName() {
        return null;
    }
}
