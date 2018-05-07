package chapter1.stream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * 输出流的两种基本用法。
 * 第一种是直接把数组作为参数写出到文件中
 *  String str = "hello";
 *  byte[] bytes = str.getBytes();
 *  out.write(bytes);
 *
 * 第二种是单个字节写入文件中
 *  String str = "hello";
 *  byte[] bytes = str.getBytes();
 *  for (int i = 0; i < bytes.length; i++) {
 *    out.write(bytes[i]);
 *  }
 *
 */
public class OutputStreamDemo01 {
    public static void main(String[] args) throws Exception {
        String path = File.separator + "home"
                + File.separator + "hjs"
                + File.separator + "Codes"
                + File.separator + "test.txt";
        File f = new File(path);
        OutputStream out = new FileOutputStream(f);

        String str = "hello world!";
        byte[] b = str.getBytes();

        out.write(b);   // 将内容输出到文件

        for (int i = 0; i < b.length; i++) {
            out.write(b[i]);        // 和上面的write方法最终结果一致
        }
        out.close();
    }
}
