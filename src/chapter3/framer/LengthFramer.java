package chapter3.framer;

import java.io.*;

/**
 * Created by Administrator on 2017/12/31.
 */
public class LengthFramer implements Framer {

    public static final int MAX_MESSAGE_LENGTH = 65535;
    public static final int BYTE_MASK = 0xff;
    public static final int SHORT_MASK = 0xffff;
    public static final int BYTE_SHIFT = 8;

    private DataInputStream in; // wrapper for data I/O

    public LengthFramer(InputStream in) throws IOException {
        this.in = new DataInputStream(in);
    }

    public void frameMsg(byte[] message, OutputStream out) throws IOException {
        if (message.length > MAX_MESSAGE_LENGTH) {
            throw new IOException("message too long");
        }
        // write length prefix
        out.write((message.length >> BYTE_SHIFT) & BYTE_MASK);
        out.write(message.length & BYTE_MASK);
        // write message
        out.write(message);
        out.flush();
    }
    public byte[] nextMsg() throws IOException {
        int length;
        try {
            length = in.readUnsignedShort();    // read 2 bytes
        } catch (EOFException e) {  // no (or 1 byte) message
            return null;
        }
        // 0 <= length <= 65535
        byte[] msg = new byte[length];
        in.readFully(msg);  // if exception, it's a framing error
        return msg;
    }

}
