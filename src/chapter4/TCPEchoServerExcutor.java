package chapter4;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class TCPEchoServerExcutor {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		if (args.length != 1) {	// �����������Ƿ���ȷ
			throw new IllegalArgumentException("���� : <Port>");
		}
		
		int echoServPort = Integer.parseInt(args[0]);	// �������˿�
		
		// ����һ��������Socket�����տͻ�����������
		ServerSocket servSock = new ServerSocket(echoServPort);
		Logger logger = Logger.getLogger("practical");
		
		Executor service = Executors.newCachedThreadPool();	// �ַ�����
		
		System.out.println("����ʼ..");
		// һֱ���У� ���ղ�Ϊÿ�����Ӵ������߳�
		while (true) {
			Socket clntSock = servSock.accept();	// �����ȴ�����
//			service.execute(new EchoProtocol(clntSock, logger));
			service.execute(new CompressProtocol(clntSock, logger));
		}
		/*	NOT REACHED */
	}
}
