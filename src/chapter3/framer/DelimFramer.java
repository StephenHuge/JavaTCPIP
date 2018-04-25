package chapter3.framer;

import java.io.*;

/**
 * ���ڶ�����ĳ�֡���������ж����Ϊ"\n"���յ����������ʾ����Ϣ�Ѿ���ɴ���
 * 
 * Created by Administrator on 2017/12/30.
 */
public class DelimFramer implements Framer {

    private InputStream in;     // ��������Դ
    private static final byte DELIMER = '\n';   // ��Ϣ�����

    public DelimFramer(InputStream in) {
        this.in = in;
    }
    
    /**
	 * ��ӳ�֡��Ϣ����ָ����Ϣ�����ָ���� 
	 */
    public void frameMsg(byte[] message, OutputStream out) throws IOException {
        // ȷ�ϴ�����Ϣ�в��������
        for (byte b : message) {
            if (b == DELIMER)   throw new IOException("��Ϣ�к��ж��������Ϣ��Ч");
        }
        out.write(message);			// �����д����Ϣ
        out.write(DELIMER);			// �����д�����������ʾ��Ϣд�����
        out.flush();
    }
    
	/**
	 * ɨ��ָ���������г�ȡ����һ����Ϣ 
	 */
    public byte[] nextMsg() throws IOException {
        ByteArrayOutputStream messageBuffer = new ByteArrayOutputStream();
        int nextByte;

        // ��ȡ��Ϣ��֪�����������
        while ((nextByte = in.read()) != DELIMER) {
            if (nextByte == -1) {   // ����ĩβ��
                if (messageBuffer.size() == 0) {    // ���û�пɶ�ȡ���ֽ�
                    return null;
                } else {
                    throw new EOFException("��Ϣ�ǿգ�����û�ж����");
                }
            }
            messageBuffer.write(nextByte);  // ����Ϣд�뵽����
        }
        return messageBuffer.toByteArray();
    }
}