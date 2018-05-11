package chapter1.stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * InputStream基本用法，也是最常用的用法。
 * 常用code template:
 *
 * InputStream in;
 * int a;
 *
 * // read()返回值是读取的字节值（类型是int），如果返回-1，则表示读取完毕
 * while ((a = in.read()) != -1) {
 *  // do sth
 * }
 *
 */
public class InputStreamDemo04 {
    public static void main(String[] args) throws Exception {
        String path = File.separator + "home"
                + File.separator + "hjs"
                + File.separator + "Codes"
                + File.separator + "test.txt";
        File f = new File(path);
        InputStream in = new FileInputStream(f);

        int len = 0;
        byte[] b = new byte[1024];
        int temp;

        while ((temp = in.read()) != -1) {  // 将每次读取的数赋给temp，读取完毕则返回-1
            b[len] = (byte) temp;
            len++;
        }
        in.close();

        System.out.println("内容是：" + new String(b, 0, len));
    }
}
