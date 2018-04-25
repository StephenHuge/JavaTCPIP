package chapter3.framer;

import java.io.*;

/**
 * ���ڳ��ȵĳ�֡������������Ϣ������2�ֽڱ�ʾ������Ϣ����ǰ��
 * 
 * Created by Administrator on 2017/12/31.
 */
public class LengthFramer implements Framer {

    public static final int MAX_MESSAGE_LENGTH = 65535;
    public static final int BYTE_MASK = 0xff;
    public static final int SHORT_MASK = 0xffff;
    public static final int BYTE_SHIFT = 8;

    private DataInputStream in; // ����I/O�ķ�װ

    public LengthFramer(InputStream in) throws IOException {
        this.in = new DataInputStream(in);
    }

    /**
  	 * ��ӳ�֡��Ϣ����ָ����Ϣ�����ָ���� 
  	 */
    public void frameMsg(byte[] message, OutputStream out) throws IOException {
        if (message.length > MAX_MESSAGE_LENGTH) {
            throw new IOException("��Ϣ̫����");
        }
        // д�� ��Ϣ������Ϊǰ׺
        out.write((message.length >> BYTE_SHIFT) & BYTE_MASK);	// ��8λ
        out.write(message.length & BYTE_MASK);					// ��8λ
        // д����Ϣ
        out.write(message);
        out.flush();
    }
    
    /**
	 * ɨ��ָ���������г�ȡ����һ����Ϣ 
	 */
    public byte[] nextMsg() throws IOException {
        int length;
        try {
            length = in.readUnsignedShort();    // ��ȡ���ֽ�
        } catch (EOFException e) {  // û�У�����ֻ��1�ֽڣ���Ϣ
            return null;
        }
        // 0 <= length <= 65535
        byte[] msg = new byte[length];
        in.readFully(msg);  // if exception, it's a framing error
        return msg;
    }

}
