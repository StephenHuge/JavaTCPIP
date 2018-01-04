package chapter3.framer;

import java.io.*;

/**
 * Created by Administrator on 2017/12/30.
 */
public class DelimFramer implements Framer {

    private InputStream in;     // data source
    private static final byte DELIMER = '\n';   // message delimiter

    public DelimFramer(InputStream in) {
        this.in = in;
    }
    public void frameMsg(byte[] message, OutputStream out) throws IOException {
        // ensure that the message does not contain the delimiter
        for (byte b : message) {
            if (b == DELIMER)   throw new IOException("Message contains delimiter");
        }
        out.write(message);
        out.write(DELIMER);
        out.flush();
    }

    public byte[] nextMsg() throws IOException {
        ByteArrayOutputStream messageBuffer = new ByteArrayOutputStream();
        int nextByte;

        // fetch bytes until find delimiter
        while ((nextByte = in.read()) != DELIMER) {
            if (nextByte == -1) {   // end of stream?
                if (messageBuffer.size() == 0) {    // if no byte read
                    return null;
                } else {
                    throw new EOFException("Non-empty message without delimiter");
                }
            }
            messageBuffer.write(nextByte);  // write byte to buffer
        }
        return messageBuffer.toByteArray();
    }
}