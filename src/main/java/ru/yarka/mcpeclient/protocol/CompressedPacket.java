package ru.yarka.mcpeclient.protocol;

public class CompressedPacket extends PreparedPacket {

    private RawPacket packet;

    public CompressedPacket(RawPacket pk) {
        packet = pk;
    }

    @Override
    public void write() {


    }

    @Override
    public String getName() {
        return "CompressedPacket";
    }

    public CompressedPacket compressToDatagram() {


        return this;
    }

    public CompressedPacket compressToEncapsulated() {


        return this;
    }
}
