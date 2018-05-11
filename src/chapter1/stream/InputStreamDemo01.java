package chapter1.stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * InputStream 基本用法，直接读取到数组中
 *  byte[] b = new byte[1024];
 *  in.read(b);
 *
 */
public class InputStreamDemo01 {
    public static void main(String[] args) throws Exception {
        String path = File.separator + "home"
                + File.separator + "hjs"
                + File.separator + "Codes"
                + File.separator + "test.txt";
        File f = new File(path);
        InputStream in = new FileInputStream(f);
        byte[] b = new byte[1024];

        int len = in.read(b);       // 将数据读入数组，返回值是读入数据的长度

        in.close();
        System.out.println("读入数据的长度：" + len);
        System.out.println("内容是：" + new String(b, 0, len)); // 必须指定范围，不然会打印1024个字节
    }
}
