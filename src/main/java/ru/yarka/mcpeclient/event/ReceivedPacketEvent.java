package ru.yarka.mcpeclient.event;

import ru.yarka.mcpeclient.protocol.ResponsePacket;

public class ReceivedPacketEvent extends PacketEvent {

    public ReceivedPacketEvent(ResponsePacket pk) {
        packet = pk;
    }
}
