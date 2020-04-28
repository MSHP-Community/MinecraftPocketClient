package ru.yarka.mcpeclient;

import org.apache.commons.lang3.ArrayUtils;
import ru.yarka.mcpeclient.protocol.CompressedPacket;
import ru.yarka.mcpeclient.protocol.Pool;
import ru.yarka.mcpeclient.protocol.ResponsePacket;
import ru.yarka.mcpeclient.protocol.handshaking.*;
import ru.yarka.mcpeclient.protocol.status.StatusPacketPing;
import ru.yarka.mcpeclient.protocol.status.StatusPacketPong;
import ru.yarka.mcpeclient.server.ServerSocket;
import ru.yarka.mcpeclient.session.SessionHandler;
import ru.yarka.mcpeclient.utils.ThreadPool;
import ru.yarka.mcpeclient.utils.async.ClientThread;
import java.net.InetSocketAddress;
import java.net.SocketTimeoutException;
import java.util.Random;

public class Client extends ServerSocket {

    private SessionHandler handler;
    private boolean stop = false;
    private long clientId;

    private ThreadPool threadPool;

    public Client(String host, int port, SessionHandler handler) {
        super(new InetSocketAddress(host, port));

        this.handler = handler;
        Pool.register();
        threadPool = new ThreadPool();
    }

    public void clientTick() {
        byte[] bytes = null;

        try {
            bytes = read();
        } catch (SocketTimeoutException e) {
        }

        if(ArrayUtils.isEmpty(bytes)) {
                return;
        }


        if(Pool.exists(bytes[0])) {
            ResponsePacket pk = Pool.get(bytes[0]);
            pk.setBuffer(bytes);

            handleResponsePacket(pk);
        }
    }

    public void handleResponsePacket(ResponsePacket pk) {
        pk.read();

        if(pk instanceof StatusPacketPong) {
            System.out.println("PID >> " + pk.getPacketId());
            System.out.println("Info >> " + ((StatusPacketPong) pk).serverInfo);
        } else if(pk instanceof HandshakeStateResponse1) {
            handshakeState2();
        } else if(pk instanceof HandshakeStateResponse2) {
            handshakeStateFinal();
        } else if(pk instanceof HandshakeStateResponseFinal) {
            sessionCreateRequest();
            login();
        }
    }

    public void ping() {
        System.out.println(">> ping");
        StatusPacketPing pk = new StatusPacketPing();
        pk.pingId = (new Random()).nextLong();

        send(pk);
    }

    public void login() {
        System.out.println(">> Login");
    }

    public void handshakeState1() {
        System.out.println(">> HandshakeState 1");
        send(new HandshakeStateRequest1());
    }

    public void handshakeState2() {
        System.out.println(">> HandshakeState 2");
        HandshakeStateRequest2 pk = new HandshakeStateRequest2();
        pk.serverAddress = dest;
        pk.clientId = clientId;

        send(pk);
    }

    public void handshakeStateFinal() {
        System.out.println(">> HandshakeState final");
        HandshakeStateRequestFinal pk = new HandshakeStateRequestFinal();
        pk.clientId = clientId;
        pk.sendPingTime = System.currentTimeMillis();

        CompressedPacket packet2send = new CompressedPacket(pk);
        packet2send.compressToEncapsulated().compressToDatagram();

        send(packet2send);
    }

    public void sessionCreateRequest() {
        System.out.println(">> Session create");
    }

    public void start() {
        threadPool.push(new ClientThread() {

            @Override
            public void run() {
                while(!stop) {
                    clientTick();
                }
            }
        });

        clientId = (new Random()).nextLong();
    }

    public void disconnect() {

    }
}
