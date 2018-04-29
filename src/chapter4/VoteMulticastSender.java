package chapter4;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import chapter3.vote.VoteMsg;
import chapter3.vote.coder.VoteMsgCoder;
import chapter3.vote.coder.VoteMsgTextCoder;

public class VoteMulticastSender {
	public static final int CANDIDATEID = 475;
	
	public static void main(String[] args) throws IOException {
		if ((args.length < 2) || (args.length > 3)) {	// Test # of args
			throw new IllegalArgumentException("������ <Multicast Addr> <Port> [<TTL>]");
		}
		
		InetAddress destAddr = InetAddress.getByName(args[0]);	// Destination
		if (!destAddr.isMulticastAddress()) {	// �����Ƿ��Ƕಥ��ַ
			throw new IllegalArgumentException("���Ƕಥ��ַ");
		}
		
		int destPort = Integer.parseInt(args[1]);	// Ŀ��˿�
		int TTL = (args.length == 3) ? Integer.parseInt(args[2]) : 1;	// ����TTL��Time to Live��
		
		MulticastSocket sock = new MulticastSocket();
		sock.setTimeToLive(TTL);
		
		VoteMsgCoder coder = new VoteMsgTextCoder();
		
		VoteMsg vote = new VoteMsg(true, true, CANDIDATEID, 1000001L);
		// �������������ݰ�
		byte[] msg = coder.toWire(vote);
		DatagramPacket message = new DatagramPacket(msg, msg.length, destAddr, destPort);
		System.out.println("���ͻ����ı���������� (" + msg.length + " bytes):");
		System.out.println(vote);
		sock.send(message);
		
		sock.close();
	}
}
