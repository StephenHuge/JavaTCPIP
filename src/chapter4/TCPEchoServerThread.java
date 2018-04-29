package chapter4;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

/**
 * һ�ͻ���һ�߳�
 */
public class TCPEchoServerThread {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		if (args.length != 1) {	// �����������Ƿ���ȷ
			throw new IllegalArgumentException("���� : <Port>");
		}
		
		int echoServPort = Integer.parseInt(args[0]);	// �������˿�
		
		// ����һ��������Socket�����տͻ�����������
		ServerSocket servSock = new ServerSocket(echoServPort);
		Logger logger = Logger.getLogger("practical");
		
		// һֱ���У�Ϊÿ�����Ӵ���һ���µ��߳�
		while (true) {
			Socket clntSock = servSock.accept();	// ��������״̬���ȴ�����			
			//  �������̣߳������µ�����
//			Thread thread = new Thread(new EchoProtocol(clntSock, logger));
			Thread thread = new Thread(new TimeLimitEchoProtocol(clntSock, logger));
			thread.start();
			logger.info("������һ�����߳� ��" + thread.getName());
		}
		
		/*	NOT REACHED	*/
	}
}
