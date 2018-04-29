package chapter4;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

/**
 * 一客户端一线程
 */
public class TCPEchoServerThread {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		if (args.length != 1) {	// 检查输入参数是否正确
			throw new IllegalArgumentException("参数 : <Port>");
		}
		
		int echoServPort = Integer.parseInt(args[0]);	// 服务器端口
		
		// 创建一个服务器Socket，接收客户端连接请求
		ServerSocket servSock = new ServerSocket(echoServPort);
		Logger logger = Logger.getLogger("practical");
		
		// 一直运行，为每个连接创建一个新的线程
		while (true) {
			Socket clntSock = servSock.accept();	// 处于阻塞状态，等待连接			
			//  创建新线程，处理新的连接
//			Thread thread = new Thread(new EchoProtocol(clntSock, logger));
			Thread thread = new Thread(new TimeLimitEchoProtocol(clntSock, logger));
			thread.start();
			logger.info("创建了一个新线程 ：" + thread.getName());
		}
		
		/*	NOT REACHED	*/
	}
}
