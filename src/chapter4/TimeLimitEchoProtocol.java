package chapter4;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 连接协议，其本质是一个Runnable任务，其中添加了IO处理和1个Socket
 */
public class TimeLimitEchoProtocol implements Runnable {

	private static final int BUFSIZE = 32; 				// I/O buffer 的长度（字节）
	private static final String TIMELIMIT = "10000";	// 默认时长（ms）
	private static final String TIMELIMITPROP = "TimeLimit";	// 变量

	private static int timelimit;
	private Socket clntSock;			   // 连接客户端的Socket
	private Logger logger;				   // 服务器日志

	public TimeLimitEchoProtocol(Socket clntSock, Logger logger) {
		this.clntSock = clntSock;
		this.logger = logger;
		// 从系统设置获取时间限制
		timelimit = Integer.parseInt(System.getProperty(TIMELIMITPROP, TIMELIMIT));
	}

	public static void handleEchoClient(Socket clntSock, Logger logger) {
		try {
			// 通过Socket获取输出输出流
			InputStream in = clntSock.getInputStream();
			OutputStream out = clntSock.getOutputStream();

			int recvMsgSize;			// 收到信息的长度
			int totalBytesEchoed = 0;	// 从客户端接收到的字节数
			byte[] echoBuffer = new byte[BUFSIZE];	// 接收buffer
			long endTime = System.currentTimeMillis() + timelimit;
			int timeBoundMillis = timelimit;

			clntSock.setSoTimeout(timelimit);

			// 一直接收直到客户端关闭连接，通过返回-1提示
			while ((timeBoundMillis > 0)	&&
					((recvMsgSize = in.read(echoBuffer)) != -1)) {
				out.write(echoBuffer, 0, recvMsgSize);
				totalBytesEchoed += recvMsgSize;

				// 计算时间并重新设置超时时间
				timeBoundMillis = (int) (endTime - System.currentTimeMillis());
				clntSock.setSoTimeout(timeBoundMillis);
			}

			logger.info("客户端 " + clntSock.getRemoteSocketAddress() + ", 接收了" 
					+ totalBytesEchoed + "字节");

		} catch (IOException ex) {
			logger.log(Level.WARNING, "连接协议出错", ex); 
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
