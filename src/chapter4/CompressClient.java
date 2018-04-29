package chapter4;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * WARNING: 这段代码在发送大文件时（多余1W字节左右会发生死锁）
 */
public class CompressClient {
	
	public static final int BUFSIZE = 256;	// 读取数据用buffer的大小
	public static void main(String[] args) throws IOException {
		
		if (args.length != 3) {	// 检测args的长度
			throw new IllegalArgumentException("参数： <Server> <Port> <File>");
		}
		
		String server = args[0];				// 服务器名称或者IP地址
		int port = Integer.parseInt(args[1]);	// 服务器端口
		
		String filename = args[2];	// 要读取的文件
		
		// 打开输入流和输出流 (name.gz)
		FileInputStream fileIn = new FileInputStream(filename);
		FileOutputStream fileOut = new FileOutputStream(filename + ".gz");

		// 创建一个Socket，连接到服务器和特定的port
		Socket sock = new Socket(server, port);
		
		// 发送未压缩的字节流到服务器
		snedBytes(sock, fileIn);
		
		// 从服务器接收已压缩的字节流
		InputStream sockIn = sock.getInputStream();
		int bytesRead;		// 读取字节的数量
		byte[] buffer = new byte[BUFSIZE];	// 字节buffer
		while ((bytesRead = sockIn.read(buffer)) != -1) {
			fileOut.write(buffer, 0, bytesRead);
			System.out.print("R");	// 读取进度提示
		}
		System.out.println();	// 结束提示
		
		sock.close();		// 关闭socket和它的流
		fileIn.close();		// 关闭文件流
		fileOut.close();
	}
	private static void snedBytes(Socket sock, FileInputStream fileIn) throws IOException {
		OutputStream sockOut = sock.getOutputStream();
		int bytesRead;
		byte[] buffer = new byte[BUFSIZE];
		while ((bytesRead = fileIn.read(buffer)) != -1) {
			sockOut.write(buffer, 0, bytesRead);
			System.out.print("W");	// 写出进度提示
		}
		System.out.println();	// 发送完毕
		
	}
}
