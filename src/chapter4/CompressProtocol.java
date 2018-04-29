package chapter4;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPOutputStream;

public class CompressProtocol implements Runnable {

	public static final int BUFSIZE = 1024; 	// ����buffer�ĳ���
	private Socket clntSock;
	private Logger logger;
	
	public CompressProtocol(Socket clntSock, Logger logger) {
		this.clntSock = clntSock;
		this.logger = logger;
	}
	public static void handleCompressClient(Socket clntSock, Logger logger) {
		try {
			// ��socket��ȡ���������
			InputStream in = clntSock.getInputStream();
			GZIPOutputStream out = new GZIPOutputStream(clntSock.getOutputStream());
			
			byte[] buffer = new byte[BUFSIZE];		// װ���/дbuffer
			int bytesRead;							// ��ȡ���ֽ���
			// һֱ���գ�֪���ͻ��˹ر����ӣ����ͨ������-1��֪
			while ((bytesRead = in.read(buffer)) != -1) {
				out.write(buffer, 0, bytesRead);
				out.flush(); 		// �������ˢ���ֽ�
			}
		} catch (IOException ex) {
			logger.log(Level.WARNING, "����Э�����", ex);
		}
		
		try {
			clntSock.close();	// �ر�socket 
		} catch (IOException e) {
			logger.info("Exception = " + e.getMessage());
		}
	}
	@Override
	public void run() {
		handleCompressClient(clntSock, logger);
	}

}
