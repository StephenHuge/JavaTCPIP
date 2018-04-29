package chapter5;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public class TCPServerSelector {
	
	private static final int BUFSIZE = 256;		// buffer的字节长度
	private static final int TIMEOUT = 3000;	// 等待时长（毫秒）
	
	public static void main(String[] args) throws IOException {
		
		if (args.length < 1) {	// 检验参数的个数
			throw new IllegalArgumentException("参数：<Port> ...");
		}
		
		// 新建一个Selector用来多路监听socket和连接
		Selector selector = Selector.open();
		
		// 为selector和每个接口创建监听接口的channel
		for (String arg : args) {
			ServerSocketChannel listenChannel = ServerSocketChannel.open();
			listenChannel.socket().bind(new InetSocketAddress(Integer.parseInt(arg)));
			listenChannel.configureBlocking(false);	// 必须设置为非阻塞才能进行注册
			// 为Channel注册Selector，返回的key直接忽略
			listenChannel.register(selector, SelectionKey.OP_ACCEPT);
		}
		
		// 创建一个实现了protocol的handler
	}
	
}
