package chapter1.systemClass;

import java.io.IOException;
import java.io.InputStream;

/**
 * 不限制大小显示输入字符，缺点是由于是挨个读取字节，显示汉字会乱码。
 */
public class SystemDemo04 {
    public static void main(String[] args) throws IOException {
        InputStream in = System.in;
        StringBuilder sb = new StringBuilder();
        System.out.print("请输入内容：");
        int temp = 0;
        while ((temp = in.read()) != -1) {
            char c = (char) temp;
            if (c == '\n') {
                break;
            }
            sb.append(c);
        }
        System.out.printf("输入的内容是：%s", new String(sb));
        in.close();
    }
}
