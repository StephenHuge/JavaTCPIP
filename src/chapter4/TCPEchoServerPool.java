package chapter4;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TCPEchoServerPool {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		if (args.length != 2) {	// �����������Ƿ���ȷ
			throw new IllegalArgumentException("���� : <Port>	<Threads>");
		}
		
		int echoServPort = Integer.parseInt(args[0]);	// �������˿�
		int threadPoolSize = Integer.parseInt(args[1]);	
		
		// ����һ��������Socket�����տͻ�����������
		final ServerSocket servSock = new ServerSocket(echoServPort);
		final Logger logger = Logger.getLogger("practical");
		
		// �����̶��������̣߳���������ͻ���
		for (int i = 0; i < threadPoolSize; i++) {
			Thread thread = new Thread("thread" + i) {
				@Override
				public void run() {
					while (true) {
						try {
							System.out.println(this.getName() + "׼����������");
							Socket clntSock = servSock.accept();				// �ȴ�����
							EchoProtocol.handleEchoClient(clntSock, logger);	// ��������
							System.out.println(this.getName() + "�����������");
						} catch (IOException ex) {
							logger.log(Level.WARNING, "�ͻ��˽���ʧ��", ex);
						}	
					}
				}
			};
			thread.setName("thread - " + i);
			thread.start();
			logger.info("�½��������߳� = " + thread.getName());
		}
	}
}
