package ru.yarka.mcpeclient.protocol;

import ru.yarka.mcpeclient.protocol.handshaking.HandshakeStateResponse1;
import ru.yarka.mcpeclient.protocol.handshaking.HandshakeStateResponse2;
import ru.yarka.mcpeclient.protocol.handshaking.HandshakeStateResponseFinal;
import ru.yarka.mcpeclient.protocol.status.StatusPacketPong;

import java.util.HashMap;

public class Pool {

    private static HashMap<Byte, ResponsePacket> pool = new HashMap<Byte, ResponsePacket>();

    public static void register() {
        pool.put(StatusPacketPong.PACKET_ID, new StatusPacketPong());
        pool.put(HandshakeStateResponse1.PACKET_ID, new HandshakeStateResponse1());
        pool.put(HandshakeStateResponse2.PACKET_ID, new HandshakeStateResponse2());
        pool.put(HandshakeStateResponseFinal.PACKET_ID, new HandshakeStateResponseFinal());
    }

    public static boolean exists(byte id) {
        return pool.containsKey(id);
    }

    public static ResponsePacket get(byte id) {
        return pool.get(id);
    }
}
