package chapter1.stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * IuputStream基本用法，其中read方法读取单个字节
 * byte[] b = new byte[(int) f.length()];  // 数组由文件大小决定
 * for (int i = 0; i < b.length; i++)
 *    b[i] = (byte) in.read();        // 单个字节读取
 */
public class InputStreamDemo03 {
    public static void main(String[] args) throws Exception {
        String path = File.separator + "home"
                + File.separator + "hjs"
                + File.separator + "Codes"
                + File.separator + "test.txt";
        File f = new File(path);
        InputStream in = new FileInputStream(f);
        byte[] b = new byte[(int) f.length()];  // 数组由文件大小决定

        for (int i = 0; i < b.length; i++) {
            b[i] = (byte) in.read();        // 单个字节读取
        }
        in.close();

        System.out.println("内容是：" + new String(b));
    }
}
