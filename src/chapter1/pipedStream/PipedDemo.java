package chapter1.pipedStream;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * 管道流，主要作用是可以进行两个线程间的通信，要实现通信，必须先将输出流连到输入流上，
 * 例如：
 * pipedOut.connect(pipedIn);
 * 两个流分属不同的线程，并发运行时即可进行通信。
 */
public class PipedDemo {
    public static void main(String[] args) {
        Sender sender = new Sender();
        Receiver receiver = new Receiver();

        try {
            sender.getPos().connect(receiver.getPis());
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(sender).start();
        new Thread(receiver).start();
    }
}

class Sender implements Runnable {
    private PipedOutputStream pos = null;   // 管道输出流

    public Sender() {
        this.pos = new PipedOutputStream();   // 实例化
    }
    @Override
    public void run() {
        String str = "I'm a sender";
        System.out.println("--------- 我是发送者 ----------");
        try {
            this.pos.write(str.getBytes());   // 输出信息
            System.out.println("发送完毕");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            this.pos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public PipedOutputStream getPos() {   // 通过线程类得到输出流
        return pos;
    }
}

class Receiver implements Runnable {
    private PipedInputStream pis = null;

    public Receiver() {
        this.pis = new PipedInputStream();  // 实例化输入流
    }

    @Override
    public void run() {
        byte[] bytes = new byte[1024];
        int len = 0;
        System.out.println("--------- 我是接收者 ----------");
        try {
            len = this.pis.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            this.pis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("接收内容是：" + new String(bytes, 0, len));
    }
    public PipedInputStream getPis() {
        return pis;
    }
}
