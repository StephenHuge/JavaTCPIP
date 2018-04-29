package chapter5;

import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class TCPEchoClientNonblocking {
	public static void main(String[] args) throws Exception {
		
		if ((args.length < 2) || (args.length > 3)) {	// �����������ĸ���
			throw new IllegalArgumentException("������<Server> <Word> [<Port>]");
		}
		
		String server = args[0];	// �����������ƻ���IP��ַ
		// ʹ��Ĭ���ַ����������Stringת��Ϊ�ֽ�
		byte[] argument = args[1].getBytes();
		
		int servPort = (args.length == 3) ? Integer.parseInt(args[2]) : 7;
		
		// �½�һ��Channel��������Ϊ������
		SocketChannel clntChan = SocketChannel.open();
		clntChan.configureBlocking(false);
		
		// ��ʼ���������������ӣ����ڷ�����������δ�������ֱ�ӷ���null�����Դ˴��ظ���ѯֱ���������
		// ����æ�ȴ�ʮ���˷�ϵͳ��Դ���˴�ֻ��Ϊ����ʾ
		if (!clntChan.connect(new InetSocketAddress(server, servPort))) {
			while (!clntChan.finishConnect()) {
				System.out.println(".");	// �����ʾ���û��������ӣ������ִ����������
			}
		}
		
		ByteBuffer writeBuf = ByteBuffer.wrap(argument);
		ByteBuffer readBuf = ByteBuffer.allocate(argument.length);
		int totalBytesRcvd = 0;				// ĿǰΪֹ���յ������ֽ���
		int bytesRcvd;						// ��һ�ζ�ȡ���յ��ַ���
		while (totalBytesRcvd < argument.length) {
			if (writeBuf.hasRemaining()) {
				clntChan.write(writeBuf);
			}
			if ((bytesRcvd = clntChan.read(readBuf)) == -1) {
				throw new SocketException("��������ǰ�ر�");
			}
			totalBytesRcvd += bytesRcvd;
			System.out.println(".");	// �����ʾ���û�ж�д��������ִ����������
		}
		System.out.println("�յ���" + 		// ���ַ�ת��Ϊ�ַ���
				new String(readBuf.array(), 0, totalBytesRcvd));
		clntChan.close();
	}
}
