package chapter2.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * java 7070
 */
public class UDPEchpServer {

	private static final int ECHOMAX = 255;  // Maximum size of echo datagram
	
	public static void main(String[] args) throws IOException {
		
		if (args.length != 1) {		// Test for correct argument list
			throw new java.lang.IllegalArgumentException("Parameter(s) : <Port>");
		}
		
		int servPort = Integer.parseInt(args[0]);
		
		DatagramSocket socket = new DatagramSocket(servPort);
		DatagramPacket packet = new DatagramPacket(new byte[ECHOMAX], ECHOMAX);
		System.out.println("本地服务器地址 : " + socket.getLocalSocketAddress());
		
		while (true) {		// Run forever, receiving and echoing datagrams
			socket.receive(packet);	// Receive packet from client
			System.out.println("Handling client at " + packet.getAddress().getHostAddress()
													 + " on port " + packet.getPort());
			socket.send(packet);	// Send the same packet bacck to client
			packet.setLength(ECHOMAX);	// Reset length to avoid shrinking buffer
		}
		/*	NOT REACHED*/
	}
}
