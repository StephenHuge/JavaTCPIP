package chapter3.framer;

import java.io.*;

/**
 * 基于定界符的成帧方法，其中定界符为"\n"，收到定界符即表示此消息已经完成传送
 * 
 * Created by Administrator on 2017/12/30.
 */
public class DelimFramer implements Framer {

    private InputStream in;     // 数据输入源
    private static final byte DELIMER = '\n';   // 信息定界符

    public DelimFramer(InputStream in) {
        this.in = in;
    }
    
    /**
	 * 添加成帧信息并将指定消息输出到指定流 
	 */
    public void frameMsg(byte[] message, OutputStream out) throws IOException {
        // 确认传送信息中不含定界符
        for (byte b : message) {
            if (b == DELIMER)   throw new IOException("信息中含有定界符，信息无效");
        }
        out.write(message);			// 输出流写出信息
        out.write(DELIMER);			// 输出流写出定界符，表示信息写入完毕
        out.flush();
    }
    
	/**
	 * 扫描指定流，从中抽取出下一条信息 
	 */
    public byte[] nextMsg() throws IOException {
        ByteArrayOutputStream messageBuffer = new ByteArrayOutputStream();
        int nextByte;

        // 读取信息，知道读到定界符
        while ((nextByte = in.read()) != DELIMER) {
            if (nextByte == -1) {   // 流的末尾？
                if (messageBuffer.size() == 0) {    // 如果没有可读取的字节
                    return null;
                } else {
                    throw new EOFException("信息非空，但是没有定界符");
                }
            }
            messageBuffer.write(nextByte);  // 将信息写入到流中
        }
        return messageBuffer.toByteArray();
    }
}