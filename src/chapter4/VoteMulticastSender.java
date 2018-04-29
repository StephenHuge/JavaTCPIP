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
			throw new IllegalArgumentException("参数： <Multicast Addr> <Port> [<TTL>]");
		}
		
		InetAddress destAddr = InetAddress.getByName(args[0]);	// Destination
		if (!destAddr.isMulticastAddress()) {	// 检验是否是多播地址
			throw new IllegalArgumentException("不是多播地址");
		}
		
		int destPort = Integer.parseInt(args[1]);	// 目标端口
		int TTL = (args.length == 3) ? Integer.parseInt(args[2]) : 1;	// 设置TTL（Time to Live）
		
		MulticastSocket sock = new MulticastSocket();
		sock.setTimeToLive(TTL);
		
		VoteMsgCoder coder = new VoteMsgTextCoder();
		
		VoteMsg vote = new VoteMsg(true, true, CANDIDATEID, 1000001L);
		// 创建并发送数据包
		byte[] msg = coder.toWire(vote);
		DatagramPacket message = new DatagramPacket(msg, msg.length, destAddr, destPort);
		System.out.println("发送基于文本编码的请求 (" + msg.length + " bytes):");
		System.out.println(vote);
		sock.send(message);
		
		sock.close();
	}
}
