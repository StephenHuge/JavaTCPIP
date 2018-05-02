package chapter5.tcp;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class EchoSelectorProtocol implements TCPProtocol {

    private int bufSize;    // I/O buffer的大小

    public EchoSelectorProtocol(int bufSize) {
        this.bufSize = bufSize;
    }
    @Override
    public void handleAccept(SelectionKey key) throws IOException {
        SocketChannel clntChan = ((ServerSocketChannel) key.channel()).accept();
        clntChan.configureBlocking(false);      // 必须设置为非阻塞模式以进行注册
        // 将Channel注册到Selector，以便于读取并连接Buffer
        clntChan.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(bufSize));
    }
    @Override
    public void handleRead(SelectionKey key) throws IOException {
        // 客户端Sokcet的Channel正在处理数据
        SocketChannel clntChan = (SocketChannel) key.channel();
        ByteBuffer buf = (ByteBuffer) key.attachment();
        long bytesRead = clntChan.read(buf);
        if (bytesRead == -1) {  // 另一端关闭了吗？
            clntChan.close();
        } else if (bytesRead > 0) {
            // 通过key设置表示现在对于读写均感兴趣
            key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        }
    }
    /**
     * Channel 必须可以进行写操作，而且key必须是有效的（例如客户端channel未关闭）。
     */
    @Override
    public void handleWrite(SelectionKey key) throws IOException {
        // 取回之前读取到的数据
        ByteBuffer buf = (ByteBuffer) key.attachment();
        buf.flip();     // buffer准备进行写操作
        SocketChannel clntChan = (SocketChannel) key.channel();
        clntChan.write(buf);
        if (!buf.hasRemaining()) {  // buffer全部写完了？
            // 没有剩下的数据，所以不再对写操作感兴趣
            key.interestOps(SelectionKey.OP_READ);
        }
        buf.compact();      // 为读取腾出更多空间
    }
}
