package chapter5.udp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;

public class UDPEchoServerSelector {

    private static final int TIMEOUT = 3000;    // 最长有效时间（毫秒）

    private static final int ECHOMAX = 255;     // 回显数据包大小的最大值

    public static void main(String[] args) throws IOException {

        if (args.length != 1) {
            throw new IllegalArgumentException("参数：<Port>");
        }

        int servPort = Integer.parseInt(args[0]);

        // 新建一个selector，以用作多路客户端连接
        Selector selector = Selector.open();

        DatagramChannel channel = DatagramChannel.open();
        channel.configureBlocking(false);
        channel.socket().bind(new InetSocketAddress(servPort));
        channel.register(selector, SelectionKey.OP_READ, new ClientRecord());

        while (true) {  // 一直运行，接受并回显数据报
            // 等待任务直到有效时间到期
            if (selector.select(TIMEOUT) == 0) {
                System.out.println(".");
                continue;
            }

            // 获取key集合的迭代器和输入输出
            Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();  // key是字节掩码

                // 客户端的Socket Channel还有未处理完的数据？
                if (key.isReadable()) {
                    handleRead(key);
                }

                // 客户端的Socket Channel可写而且key有效
                if (key.isValid() && key.isWritable()) {
                    handleWrite(key);
                }

                keyIterator.remove();
            }
        }

    }

    public static void handleRead(SelectionKey key) throws IOException {
        DatagramChannel channel = (DatagramChannel) key.channel();
        ClientRecord clntRec = (ClientRecord) key.attachment();
        clntRec.buffer.clear();     // 准备接收的buffer
        clntRec.clientAddress = channel.receive(clntRec.buffer);
        if (clntRec.clientAddress != null) {    // 收到数据了吗？
            // 注册：selector的写操作
            key.interestOps(SelectionKey.OP_WRITE);
        }
    }

    public static void handleWrite(SelectionKey key) throws IOException {
        DatagramChannel channel = (DatagramChannel) key.channel();
        ClientRecord clntRec = (ClientRecord) key.attachment();
        clntRec.buffer.flip();  // 准备写操作的buffer
        int bytesSent = channel.send(clntRec.buffer, clntRec.clientAddress);
        if (bytesSent != 0) {   // buffer完成写操作了吗？
            // 不再对写操作感兴趣
            key.interestOps(SelectionKey.OP_READ);
        }

    }
    static class ClientRecord {
        public SocketAddress clientAddress;
        public ByteBuffer buffer = ByteBuffer.allocate(ECHOMAX);
    }
}
