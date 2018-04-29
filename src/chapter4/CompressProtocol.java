package chapter4;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPOutputStream;

public class CompressProtocol implements Runnable {

	public static final int BUFSIZE = 1024; 	// 接收buffer的长度
	private Socket clntSock;
	private Logger logger;
	
	public CompressProtocol(Socket clntSock, Logger logger) {
		this.clntSock = clntSock;
		this.logger = logger;
	}
	public static void handleCompressClient(Socket clntSock, Logger logger) {
		try {
			// 从socket获取输入输出流
			InputStream in = clntSock.getInputStream();
			GZIPOutputStream out = new GZIPOutputStream(clntSock.getOutputStream());
			
			byte[] buffer = new byte[BUFSIZE];		// 装配读/写buffer
			int bytesRead;							// 读取的字节数
			// 一直接收，知道客户端关闭连接，这会通过返回-1告知
			while ((bytesRead = in.read(buffer)) != -1) {
				out.write(buffer, 0, bytesRead);
				out.flush(); 		// 从输出流刷新字节
			}
		} catch (IOException ex) {
			logger.log(Level.WARNING, "回显协议出错", ex);
		}
		
		try {
			clntSock.close();	// 关闭socket 
		} catch (IOException e) {
			logger.info("Exception = " + e.getMessage());
		}
	}
	@Override
	public void run() {
		handleCompressClient(clntSock, logger);
	}

}
