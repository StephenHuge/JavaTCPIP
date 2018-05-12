package chapter1.systemClass;

import java.io.IOException;
import java.io.InputStream;

/**
 * 使用System.in读取输入
 * 问题：如果数组长度不够，且其长度为奇数的话，可能造成汉字显示失败。因为汉字是按每个汉字两个字节
 * 的形式存储的。
 */
public class SystemDemo03 {
    public static void main(String[] args) throws IOException {
        InputStream in = System.in;     // 从键盘读取输入

        byte[] bytes = new byte[1024];
        System.out.print("请输入内容：");
        int len = in.read(bytes);
        System.out.printf("输出内容是：%s", new String(bytes, 0, len));
        in.close();
    }
}
