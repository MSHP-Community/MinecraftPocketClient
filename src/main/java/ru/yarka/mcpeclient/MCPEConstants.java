package ru.yarka.mcpeclient;

public class MCPEConstants {

    public static final int MTU = 1468;

    public static final byte[] MAGIC = {0x00, (byte) 0xff, (byte) 0xff, 0x00, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfd, (byte) 0xfd, (byte) 0xfd, (byte) 0xfd, 0x12, 0x34, 0x56, 0x78};

    public static final byte RAKNET_PROTOCOL = 8;
}
