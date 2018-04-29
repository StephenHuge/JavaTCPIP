package chapter5;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public class TCPServerSelector {
	
	private static final int BUFSIZE = 256;		// buffer���ֽڳ���
	private static final int TIMEOUT = 3000;	// �ȴ�ʱ�������룩
	
	public static void main(String[] args) throws IOException {
		
		if (args.length < 1) {	// ��������ĸ���
			throw new IllegalArgumentException("������<Port> ...");
		}
		
		// �½�һ��Selector������·����socket������
		Selector selector = Selector.open();
		
		// Ϊselector��ÿ���ӿڴ��������ӿڵ�channel
		for (String arg : args) {
			ServerSocketChannel listenChannel = ServerSocketChannel.open();
			listenChannel.socket().bind(new InetSocketAddress(Integer.parseInt(arg)));
			listenChannel.configureBlocking(false);	// ��������Ϊ���������ܽ���ע��
			// ΪChannelע��Selector�����ص�keyֱ�Ӻ���
			listenChannel.register(selector, SelectionKey.OP_ACCEPT);
		}
		
		// ����һ��ʵ����protocol��handler
	}
	
}
