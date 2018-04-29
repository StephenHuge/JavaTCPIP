package chapter4;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * WARNING: ��δ����ڷ��ʹ��ļ�ʱ������1W�ֽ����һᷢ��������
 */
public class CompressClient {
	
	public static final int BUFSIZE = 256;	// ��ȡ������buffer�Ĵ�С
	public static void main(String[] args) throws IOException {
		
		if (args.length != 3) {	// ���args�ĳ���
			throw new IllegalArgumentException("������ <Server> <Port> <File>");
		}
		
		String server = args[0];				// ���������ƻ���IP��ַ
		int port = Integer.parseInt(args[1]);	// �������˿�
		
		String filename = args[2];	// Ҫ��ȡ���ļ�
		
		// ��������������� (name.gz)
		FileInputStream fileIn = new FileInputStream(filename);
		FileOutputStream fileOut = new FileOutputStream(filename + ".gz");

		// ����һ��Socket�����ӵ����������ض���port
		Socket sock = new Socket(server, port);
		
		// ����δѹ�����ֽ�����������
		snedBytes(sock, fileIn);
		
		// �ӷ�����������ѹ�����ֽ���
		InputStream sockIn = sock.getInputStream();
		int bytesRead;		// ��ȡ�ֽڵ�����
		byte[] buffer = new byte[BUFSIZE];	// �ֽ�buffer
		while ((bytesRead = sockIn.read(buffer)) != -1) {
			fileOut.write(buffer, 0, bytesRead);
			System.out.print("R");	// ��ȡ������ʾ
		}
		System.out.println();	// ������ʾ
		
		sock.close();		// �ر�socket��������
		fileIn.close();		// �ر��ļ���
		fileOut.close();
	}
	private static void snedBytes(Socket sock, FileInputStream fileIn) throws IOException {
		OutputStream sockOut = sock.getOutputStream();
		int bytesRead;
		byte[] buffer = new byte[BUFSIZE];
		while ((bytesRead = fileIn.read(buffer)) != -1) {
			sockOut.write(buffer, 0, bytesRead);
			System.out.print("W");	// д��������ʾ
		}
		System.out.println();	// �������
		
	}
}
