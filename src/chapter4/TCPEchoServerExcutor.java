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
		if (args.length != 1) {	// 检查输入参数是否正确
			throw new IllegalArgumentException("参数 : <Port>");
		}
		
		int echoServPort = Integer.parseInt(args[0]);	// 服务器端口
		
		// 创建一个服务器Socket，接收客户端连接请求
		ServerSocket servSock = new ServerSocket(echoServPort);
		Logger logger = Logger.getLogger("practical");
		
		Executor service = Executors.newCachedThreadPool();	// 分发服务
		
		System.out.println("服务开始..");
		// 一直运行， 接收并为每个连接创建新线程
		while (true) {
			Socket clntSock = servSock.accept();	// 阻塞等待连接
//			service.execute(new EchoProtocol(clntSock, logger));
			service.execute(new CompressProtocol(clntSock, logger));
		}
		/*	NOT REACHED */
	}
}
