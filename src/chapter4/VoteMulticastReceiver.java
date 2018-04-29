package chapter4;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Arrays;

import chapter3.vote.VoteMsg;
import chapter3.vote.coder.VoteMsgTextCoder;
/**
 * java 224.0.0.1 4545
 * 
 * A multicast group is specified by a class D IP address and by a standard 
 * UDP port number. Class D IP addresses are in the range 224.0.0.0 to 
 * 239.255.255.255, inclusive. The address 224.0.0.0 is reserved and should 
 * not be used.
 * 
 * @See {@link MulticastSocket}
 */
public class VoteMulticastReceiver {
	public static void main(String[] args) throws IOException {
		if (args.length != 2) {	// Test correct # of args
			throw new IllegalArgumentException("参数：<Multicast Addr> <Port>");
		}
		
		InetAddress address = InetAddress.getByName(args[0]);	// 多播地址
		if (!address.isMulticastAddress()) {	// 测试是否是多播地址
			throw new IllegalArgumentException("不是一个有效的多播地址");
		}
		
		int port = Integer.parseInt(args[1]);	// 多播端口
		MulticastSocket sock = new MulticastSocket(port);	// 接收用Socket
		sock.joinGroup(address); 	// 加入多播组
		
		VoteMsgTextCoder coder = new VoteMsgTextCoder();
		
		// 接收一个数据报
		DatagramPacket packet = new DatagramPacket(new byte[VoteMsgTextCoder.MAX_WIRE_LENGTH],
					VoteMsgTextCoder.MAX_WIRE_LENGTH);
		sock.receive(packet);
		
		VoteMsg vote = coder.fromWire(Arrays.copyOfRange(packet.getData(), 0, packet.getLength()));
		
		System.out.println("接收基于文本编码的请求 (" + packet.getLength()+ " bytes):");
		System.out.println(vote);
		
		sock.close();
	}
}
