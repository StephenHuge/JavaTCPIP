package chapter4;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TCPEchoServerPool {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		if (args.length != 2) {	// 检查输入参数是否正确
			throw new IllegalArgumentException("参数 : <Port>	<Threads>");
		}
		
		int echoServPort = Integer.parseInt(args[0]);	// 服务器端口
		int threadPoolSize = Integer.parseInt(args[1]);	
		
		// 创建一个服务器Socket，接收客户端连接请求
		final ServerSocket servSock = new ServerSocket(echoServPort);
		final Logger logger = Logger.getLogger("practical");
		
		// 创建固定数量的线程，用来服务客户端
		for (int i = 0; i < threadPoolSize; i++) {
			Thread thread = new Thread("thread" + i) {
				@Override
				public void run() {
					while (true) {
						try {
							System.out.println(this.getName() + "准备处理请求");
							Socket clntSock = servSock.accept();				// 等待连接
							EchoProtocol.handleEchoClient(clntSock, logger);	// 处理请求
							System.out.println(this.getName() + "处理请求完毕");
						} catch (IOException ex) {
							logger.log(Level.WARNING, "客户端接收失败", ex);
						}	
					}
				}
			};
			thread.setName("thread - " + i);
			thread.start();
			logger.info("新建并启动线程 = " + thread.getName());
		}
	}
}
