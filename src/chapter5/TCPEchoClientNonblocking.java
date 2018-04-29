package chapter5;

import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class TCPEchoClientNonblocking {
	public static void main(String[] args) throws Exception {
		
		if ((args.length < 2) || (args.length > 3)) {	// 检测输入参数的个数
			throw new IllegalArgumentException("参数：<Server> <Word> [<Port>]");
		}
		
		String server = args[0];	// 服务器的名称或者IP地址
		// 使用默认字符集将输入的String转换为字节
		byte[] argument = args[1].getBytes();
		
		int servPort = (args.length == 3) ? Integer.parseInt(args[2]) : 7;
		
		// 新建一个Channel，并设置为非阻塞
		SocketChannel clntChan = SocketChannel.open();
		clntChan.configureBlocking(false);
		
		// 初始化到服务器的连接，由于非阻塞可能在未完成连接直接返回null，所以此处重复轮询直到连接完成
		// 这种忙等待十分浪费系统资源，此处只是为了演示
		if (!clntChan.connect(new InetSocketAddress(server, servPort))) {
			while (!clntChan.finishConnect()) {
				System.out.println(".");	// 这里表示如果没有完成连接，则可以执行其它任务
			}
		}
		
		ByteBuffer writeBuf = ByteBuffer.wrap(argument);
		ByteBuffer readBuf = ByteBuffer.allocate(argument.length);
		int totalBytesRcvd = 0;				// 目前为止接收的所有字节数
		int bytesRcvd;						// 上一次读取接收的字符数
		while (totalBytesRcvd < argument.length) {
			if (writeBuf.hasRemaining()) {
				clntChan.write(writeBuf);
			}
			if ((bytesRcvd = clntChan.read(readBuf)) == -1) {
				throw new SocketException("连接已提前关闭");
			}
			totalBytesRcvd += bytesRcvd;
			System.out.println(".");	// 这里表示如果没有读写完毕则可以执行其它任务
		}
		System.out.println("收到：" + 		// 按字符转换为字符串
				new String(readBuf.array(), 0, totalBytesRcvd));
		clntChan.close();
	}
}
