package chapter3.framer;

import java.io.IOException;
import java.io.OutputStream;

/**
 * ��֡�ͽ�����
 * 
 * Created by Administrator on 2017/12/30.
 */
public interface Framer {
	
	/**
	 * ��ӳ�֡��Ϣ����ָ����Ϣ�����ָ���� 
	 */
	void frameMsg(byte[] message, OutputStream out) throws IOException;
	
	/**
	 * ɨ��ָ���������г�ȡ����һ����Ϣ 
	 */
	byte[] nextMsg() throws IOException;
}
