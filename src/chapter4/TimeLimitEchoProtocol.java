package chapter4;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ����Э�飬�䱾����һ��Runnable��������������IO������1��Socket
 */
public class TimeLimitEchoProtocol implements Runnable {

	private static final int BUFSIZE = 32; 				// I/O buffer �ĳ��ȣ��ֽڣ�
	private static final String TIMELIMIT = "10000";	// Ĭ��ʱ����ms��
	private static final String TIMELIMITPROP = "TimeLimit";	// ����

	private static int timelimit;
	private Socket clntSock;			   // ���ӿͻ��˵�Socket
	private Logger logger;				   // ��������־

	public TimeLimitEchoProtocol(Socket clntSock, Logger logger) {
		this.clntSock = clntSock;
		this.logger = logger;
		// ��ϵͳ���û�ȡʱ������
		timelimit = Integer.parseInt(System.getProperty(TIMELIMITPROP, TIMELIMIT));
	}

	public static void handleEchoClient(Socket clntSock, Logger logger) {
		try {
			// ͨ��Socket��ȡ��������
			InputStream in = clntSock.getInputStream();
			OutputStream out = clntSock.getOutputStream();

			int recvMsgSize;			// �յ���Ϣ�ĳ���
			int totalBytesEchoed = 0;	// �ӿͻ��˽��յ����ֽ���
			byte[] echoBuffer = new byte[BUFSIZE];	// ����buffer
			long endTime = System.currentTimeMillis() + timelimit;
			int timeBoundMillis = timelimit;

			clntSock.setSoTimeout(timelimit);

			// һֱ����ֱ���ͻ��˹ر����ӣ�ͨ������-1��ʾ
			while ((timeBoundMillis > 0)	&&
					((recvMsgSize = in.read(echoBuffer)) != -1)) {
				out.write(echoBuffer, 0, recvMsgSize);
				totalBytesEchoed += recvMsgSize;

				// ����ʱ�䲢�������ó�ʱʱ��
				timeBoundMillis = (int) (endTime - System.currentTimeMillis());
				clntSock.setSoTimeout(timeBoundMillis);
			}

			logger.info("�ͻ��� " + clntSock.getRemoteSocketAddress() + ", ������" 
					+ totalBytesEchoed + "�ֽ�");

		} catch (IOException ex) {
			logger.log(Level.WARNING, "����Э�����", ex); 
		} finally {
			try {
				clntSock.close();
			} catch (IOException e) {
			}
		}
	}

	@Override
	public void run() {
		handleEchoClient(clntSock, logger);
	}

}