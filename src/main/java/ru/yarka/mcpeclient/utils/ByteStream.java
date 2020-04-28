package ru.yarka.mcpeclient.utils;

import ru.yarka.mcpeclient.MCPEConstants;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;

public class ByteStream {

    protected ArrayList<Byte> buffer = new ArrayList<Byte>();

    protected int offset = 0;

    public void setBuffer(byte[] bytes) {
        buffer.clear();

        int len = bytes.length;
        for(int i = 0; i < len; ++i) {
            buffer.add(bytes[i]);
        }
    }

    public void putMagic() {
        for(int i = 0; i < MCPEConstants.MAGIC.length; ++i) {
            putByte(MCPEConstants.MAGIC[i]);
        }
    }

    protected void putByte(byte val) {
        buffer.add(val);
        ++offset;
    }

    protected void putShort(short val) {
        putByte((byte) ((val >>> 8) & 0xFF));
        putByte((byte) (val & 0xFF));
    }

    protected void putInt(int val) {
        putByte((byte) ((val >>> 24) & 0xFF));
        putByte((byte) ((val >>> 16) & 0xFF));
        putByte((byte) ((val >>> 8) & 0xFF));
        putByte((byte) (val & 0xFF));
    }

    protected void putLong(long val) {
        putByte((byte) ((val >>> 56) & 0xFF));
        putByte((byte) ((val >>> 56) & 0xFF));
        putByte((byte) ((val >>> 56) & 0xFF));
        putByte((byte) ((val >>> 56) & 0xFF));
        putByte((byte) ((val >>> 56) & 0xFF));
        putByte((byte) ((val >>> 56) & 0xFF));
        putByte((byte) ((val >>> 56) & 0xFF));
        putByte((byte) (val & 0xFF));
    }

    protected void putFloat(float val) {
        putInt(Float.floatToIntBits(val));
    }

    protected void putTriad(int val) {
        putByte((byte) ((val >>> 16) & 0xFF));
        putByte((byte) ((val >>> 8) & 0xFF));
        putByte((byte) (val & 0xFF));
    }

    protected void putString(String str) {
        short len = (short) str.length();
        byte[] bytes = str.getBytes();

        putShort(len);
        for(int i = 0; i < len; ++i) {
            putByte(bytes[i]);
        }
    }

    public void putInetSocketAddress(InetSocketAddress address) {
        putByte((byte) 4);

        String[] octets = new String[0];

        try {
            octets = InetAddress.getByName(address.getHostName()).getHostAddress().split("\\.");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        for(String octet: octets) {
            putByte((byte) ((~(Integer.parseInt(octet))) & 0xFF));
        }

        putShort((short) address.getPort());
    }

    public InetSocketAddress getInetSocketAddress() {
        getByte(); //Version

        String address = (~(getByte()) & 0xFF) + "\\." + (~(getByte()) & 0xFF) + "\\." + (~(getByte()) & 0xFF) + "\\." + (~(getByte()) & 0xFF);
        int port = getShort();

        return new InetSocketAddress(address, port);
    }

    protected long getLong() {
        return ((long)getByte() << 56) + ((long)(getByte() & 0xFF) << 48) + ((long)(getByte() & 0xFF) << 40) + ((long)(getByte() & 0xFF) << 32) + ((long)(getByte() & 0xFF) << 24) + (getByte() & 0xFF << 16) + (getByte() & 0xFF << 8) + (getByte() & 0xFF);
    }

    protected short getShort() {
        return (short) (((getByte() & 0xFF) << 8) + (getByte() & 0xFF));
    }

    protected int getInt() {
        return (getByte() & 0xFF << 24) + (getByte() & 0xFF << 16) + (getByte() & 0xFF << 8) + (getByte() & 0xFF);
    }

    protected byte getByte() {
        return buffer.get(offset++);
    }

    protected byte[] getByte(int len) {
        byte[] res = new byte[len];

        for(int i = 0; i < len; ++i) {
            res[i] = getByte();
        }

        return res;
    }

    protected String getString() {
        short len = getShort();
        String str = "";

        for(int i = 0; i < len; ++i) {
            str +=(char) getByte();
        }

        return str;
    }

    protected float getFloat(int accuracy) {
        float val = Float.intBitsToFloat(getInt());
        if(accuracy < 0) {
            return (float)Math.round(val);
        } else {
            return val;
        }
    }

    protected int getTriad() {
        return (getByte() & 0xFF << 16) + (getByte() & 0xFF << 8) + (getByte() & 0xFF);
    }

    public void readMagic() {
        offset += 16;
    }

    protected void clear() {
        offset = 0;
        buffer.clear();
    }
}
