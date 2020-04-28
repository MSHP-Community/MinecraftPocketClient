package ru.yarka.mcpeclient.event;

import ru.yarka.mcpeclient.protocol.Packet;

public class PacketEvent {

    protected Packet packet;

    public Packet getPacket() {
        return packet;
    }
}
