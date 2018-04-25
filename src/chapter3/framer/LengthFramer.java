package chapter3.framer;

import java.io.*;

/**
 * 基于长度的成帧方法，其中信息长度用2字节表示，在信息的最前面
 * 
 * Created by Administrator on 2017/12/31.
 */
public class LengthFramer implements Framer {

    public static final int MAX_MESSAGE_LENGTH = 65535;
    public static final int BYTE_MASK = 0xff;
    public static final int SHORT_MASK = 0xffff;
    public static final int BYTE_SHIFT = 8;

    private DataInputStream in; // 数据I/O的封装

    public LengthFramer(InputStream in) throws IOException {
        this.in = new DataInputStream(in);
    }

    /**
  	 * 添加成帧信息并将指定消息输出到指定流 
  	 */
    public void frameMsg(byte[] message, OutputStream out) throws IOException {
        if (message.length > MAX_MESSAGE_LENGTH) {
            throw new IOException("信息太长了");
        }
        // 写出 信息长度作为前缀
        out.write((message.length >> BYTE_SHIFT) & BYTE_MASK);	// 高8位
        out.write(message.length & BYTE_MASK);					// 低8位
        // 写出信息
        out.write(message);
        out.flush();
    }
    
    /**
	 * 扫描指定流，从中抽取出下一条信息 
	 */
    public byte[] nextMsg() throws IOException {
        int length;
        try {
            length = in.readUnsignedShort();    // 读取两字节
        } catch (EOFException e) {  // 没有（或者只有1字节）信息
            return null;
        }
        // 0 <= length <= 65535
        byte[] msg = new byte[length];
        in.readFully(msg);  // if exception, it's a framing error
        return msg;
    }

}
