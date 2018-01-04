package chapter3.framer;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Administrator on 2017/12/30.
 */
public interface Framer {
    void frameMsg(byte[] message, OutputStream out) throws IOException;
    byte[] nextMsg() throws IOException;
}
